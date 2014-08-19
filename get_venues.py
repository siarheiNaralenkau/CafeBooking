#!/usr/bin/env python
# -*- coding: utf-8 -*-
import requests
import pymysql


CLIENT_ID = 'KZ35Q53SM015ONRRFNKXZTCU1FZKKJZJ0R5F4QKW3MP3FAFH'
CLIENT_SECRET = 'NUENJ01TYMIN0XFD0RD5MAY4FZFIGMJLGKU34FWJG1FOOGYM'
VERSION = '20140805'
GOMEL_CENTER = '52.424921,31.011979'
RADIUS = '10000'
SECTIONS = ['food', 'drinks', 'coffee']
LIMIT = '50'
DEFAULT_OFFSET = 0
VENUE_INSERT = u"INSERT INTO venues(unique_id, name, phone, address, city, country," \
               "latitude, longitude, category, has_free_seats) VALUES('{0}', '{1}'," \
               "'{2}', '{3}', '{4}', '{5}', {6}, {7}, '{8}', {9})"
DELETE_VENUES = 'DELETE FROM venues'

explore_url = 'https://api.foursquare.com/v2/venues/explore?ll=' + GOMEL_CENTER + \
    '&radius=' + RADIUS + \
    '&section={0}' + \
    '&limit=' + LIMIT + \
    '&venuePhotos=1' + \
    '&offset={1}' + \
    '&client_id=' + CLIENT_ID + \
    '&client_secret=' + CLIENT_SECRET + \
    '&v=' + VERSION


def query_venues(v_type, offset):
    url = explore_url.format(v_type, offset)
    req = requests.get(url)
    json_result = req.json()
    t_venues = [item["venue"] for item in json_result["response"]["groups"][0]["items"]]
    return t_venues


def get_venues_by_type(v_type, offset):
    v_list = []
    cur_offset = offset
    t_venues = query_venues(v_type, offset)
    v_list = v_list + t_venues
    result_length = len(t_venues)
    print "Venues list size: {0}".format(result_length)
    if result_length == 50:
        print "Not all venues received!"
        while result_length == 50:
            cur_offset += result_length
            t_venues = query_venues(v_type, cur_offset)
            v_list = v_list + t_venues
            result_length = len(t_venues)
    print "Result list size: {0}".format(len(v_list))
    return v_list


def get_phone(venue):
    if "formattedPhone" in venue["contact"]:
        phone = venue["contact"]["formattedPhone"]
    elif "phone" in venue["contact"]:
        phone = venue["contact"]["phone"]
    else:
        phone = ''
    return phone


def get_address(venue):
    if "address" in venue["location"]:
        address = venue["location"]["address"]
    elif "crossStreet" in venue["location"]:
        address = venue["location"]["crossStreet"]
    else:
        address = ''
    return address


def get_city(venue):
    if "city" in venue["location"]:
        city = venue["location"]["city"]
    else:
        city = ''
    return city


def get_country(venue):
    if "country" in venue["location"]:
        country = venue["location"]["country"]
    else:
        country = ''
    return country


def get_venue_photos(venue_id):
    result = []
    photos_url = 'https://api.foursquare.com/v2/venues/' + venue_id + '/photos?limit=10' + '&client_id=' + CLIENT_ID + \
                 '&client_secret=' + CLIENT_SECRET + '&v=' + VERSION
    req = requests.get(photos_url)
    json_result = req.json()
    photos = json_result["response"]["photos"]["items"]
    if len(photos) > 0:
        for photo in photos:
            photo_url = photo["prefix"] + str(photo["width"]) + "x" + str(photo["height"]) + photo["suffix"]
            photo_uid = photo["id"]
            result.append({"uid": photo_uid, "venue_id": venue_id, "url": photo_url})
    return result

# Connect to mysql and add a venue there
conn = pymysql.connect(host='localhost', port=3306, user='admin', passwd='qwerty12Q', db='bronimesto', charset="utf8")
cursor = conn.cursor()

# Clear venues list
cursor.execute(DELETE_VENUES)
conn.commit()

# Get a list of queries for adding venues
to_insert = []
photos_to_insert = []
v_ids = []
for s in SECTIONS:
    venues = get_venues_by_type(s, DEFAULT_OFFSET)
    for venue in venues:
        phone = get_phone(venue)
        address = get_address(venue)
        city = get_city(venue)
        country = get_country(venue)

        if venue["id"] not in v_ids:
            query = VENUE_INSERT.format(venue["id"], venue["name"], phone, address, city, country,
                                        venue["location"]["lat"], venue["location"]["lng"],
                                        venue["categories"][0]["name"], 1)
            venue_photos = get_venue_photos(venue["id"])
            photos_to_insert += venue_photos

            to_insert.append(query)
            v_ids.append(venue["id"])

# Add venues to the database
for query in to_insert:
    cursor.execute(query)
    conn.commit()

# Save venue's photos
print("Amount of photos to insert: {0}".format(len(photos_to_insert)))
for photo in photos_to_insert:
    p_query = "INSERT INTO venue_photos(uid, venue_id, url) VALUES('" + photo["uid"] + "','" + photo["venue_id"] + "','" + photo["url"] + "')"
    cursor.execute(p_query)
    conn.commit()
print("Completed!")

conn.close()


