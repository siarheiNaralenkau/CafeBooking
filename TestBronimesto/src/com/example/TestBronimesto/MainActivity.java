package com.example.TestBronimesto;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    public static final String BOOKING_SERVER_REMOTE = "http://bronimesto.by:8080/BookingServer2/";
    public static final String BOOKING_SERVER_LOCAL = "http://10.9.202.142:8080/BookingServer2/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SendRequestListener clickListener = new SendRequestListener("/create_user");
        Button sendButton = (Button)findViewById(R.id.sendRequest);
        sendButton.setOnClickListener(clickListener);
    }

    public void changePassword(View v) {
        String login = ((EditText)findViewById(R.id.eMail)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();
        final HttpPost httpPost = new HttpPost(MainActivity.BOOKING_SERVER_LOCAL + "change_password");
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("email", login));
        params.add(new BasicNameValuePair("newPassword", password));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpClient httpClient = new DefaultHttpClient();
                    try {
                        HttpResponse response = httpClient.execute(httpPost);
                        StatusLine statusLine = response.getStatusLine();
                        int statusCode = statusLine.getStatusCode();
                        Log.d("Result code: ", String.valueOf(statusCode));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void restorePassword(View v) {
        String login = ((EditText)findViewById(R.id.eMail)).getText().toString();
        final HttpPost httpPost = new HttpPost(MainActivity.BOOKING_SERVER_LOCAL + "restore_password");
        List<NameValuePair> params = new ArrayList<NameValuePair>(1);
        params.add(new BasicNameValuePair("email", login));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpClient httpClient = new DefaultHttpClient();
                    try {
                        HttpResponse response = httpClient.execute(httpPost);
                        StatusLine statusLine = response.getStatusLine();
                        int statusCode = statusLine.getStatusCode();
                        Log.d("Result code: ", String.valueOf(statusCode));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void login(View v) {
        String username = ((EditText)findViewById(R.id.eMail)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();
        final HttpPost httpPost = new HttpPost(MainActivity.BOOKING_SERVER_LOCAL + "client_login");
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpClient httpClient = new DefaultHttpClient();
                    try {
                        HttpResponse response = httpClient.execute(httpPost);
                        StatusLine statusLine = response.getStatusLine();
                        int statusCode = statusLine.getStatusCode();
                        Log.d("Result code: ", String.valueOf(statusCode));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    class SendRequestListener implements View.OnClickListener {
        private String servletUrl;

        public SendRequestListener(String servletUrl) {
            this.servletUrl = servletUrl;
        }

        @Override
        public void onClick(View v) {
            final HttpPost httpPost = new HttpPost(MainActivity.BOOKING_SERVER_LOCAL + servletUrl);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String userName = ((EditText)findViewById(R.id.userName)).getText().toString();
            params.add(new BasicNameValuePair("name", userName));
            String userSurname = ((EditText)findViewById(R.id.userSurname)).getText().toString();
            params.add(new BasicNameValuePair("surname", userSurname));
            String email = ((EditText)findViewById(R.id.eMail)).getText().toString();
            params.add(new BasicNameValuePair("email", email));
            String phone = ((EditText)findViewById(R.id.phone)).getText().toString();
            params.add(new BasicNameValuePair("phone", phone));
            String password = ((EditText)findViewById(R.id.password)).getText().toString();
            params.add(new BasicNameValuePair("password", password));

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpClient httpClient = new DefaultHttpClient();
                        try {
                            HttpResponse response = httpClient.execute(httpPost);
                            StatusLine statusLine = response.getStatusLine();
                            int statusCode = statusLine.getStatusCode();
                            Log.d("Result code: ", String.valueOf(statusCode));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

}

