package com.utils;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class GCMUtil {
	public static void sendMessage() {
		final String apiKey = "AIzaSyBQ5MR92uJ1qejeO4zJsRH-bSvIOKH2aq0";
		final String registrationId = "APA91bFG2s1uB6KTv-vLwXbEEs9gzZvc8CY0CSAiLsy0raWmpa4-WrjnYNnKp0Nw_cs3Ay82oHC5yp2L5S10P82UxzyBvnUF6qjc8YYiBN_A9kPwwSY5I-0SMzuaok5qAC8CtnpbKff3mxJrPE09Uua04Xttk98-LQ";
		int numOfRetries = 3;
		Sender sender = new Sender(apiKey);
		Message message = new Message.Builder().addData("message", "Test message")
				.addData("other-parameter", "some value").build();
		try {
			Result result = sender.send(message, registrationId, numOfRetries);			
			System.out.println(result.getMessageId());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
