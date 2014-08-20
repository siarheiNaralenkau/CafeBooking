#!/usr/bin/env python
# -*- coding: utf-8 -*-

import csv
import pymysql

TABLE_ADD = u"INSERT INTO tables(venue_id, x_pos, y_pos, places, number, position_notes, photo_url) " \
            u"VALUES({0}, {1}, {2}, {3}, {4}, {5}, '{6}')"

conn = pymysql.connect(host='localhost', port=3306, user='admin', passwd='qwerty12Q', db='bronimesto', charset="utf8")
cursor = conn.cursor()

with open('venue_tables.csv', 'rb') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=';')
    index = 0
    for row in csv_reader:
        print row
        if index > 0:
            venue_name = row[0]
            xPos = row[1]
            yPos = row[2]
            places = row[3]
            number = row[4]
            posNotes = row[5]
            photoUrl = row[6]

            sel_query = "SELECT id FROM venues WHERE name = '{0}'".format(venue_name)
            cursor.execute(sel_query)
            v_row = cursor.fetchone()
            venue_id = v_row[0]
            ins_query = TABLE_ADD.format(venue_id, xPos, yPos, places, number, posNotes, photoUrl)
            cursor.execute(ins_query)
            conn.commit()

        index += 1

