package com.example.TestApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends Activity {

    private static final String VENUE_ID = "venueId";
    private static final String VISITOR_NAME = "visitorName";
    private static final String VISITOR_PHONE = "visitorPhone";
    private static final String PLACES = "places";
    private static final String NOTES = "notes";
    private static final String BOOKING_TIME = "bookingTime";
    private static final String TABLE_NUMBERS = "tableNumbers";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button sendRequestBtn = (Button)findViewById(R.id.btnSendRequest);
        sendRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://http://10.9.202.142:8080/BookingServer2/book_place");
                try {
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair(VENUE_ID, "1"));
                    params.add(new BasicNameValuePair(VISITOR_NAME, "Сергей Нараленков"));
                    params.add(new BasicNameValuePair(VISITOR_PHONE, "1234567"));
                    params.add(new BasicNameValuePair(PLACES, "2"));
                    params.add(new BasicNameValuePair(NOTES, "Какие-то пожелания"));
                    params.add(new BasicNameValuePair(BOOKING_TIME, "15-10-2014 21:00"));
                    params.add(new BasicNameValuePair(TABLE_NUMBERS, "1"));
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                    HttpResponse response = httpClient.execute(httpPost);
                    System.out.println(response.toString());
                } catch (ClientProtocolException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
