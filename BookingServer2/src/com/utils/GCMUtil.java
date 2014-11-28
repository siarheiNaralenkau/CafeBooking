package com.utils;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class GCMUtil {
	public static void sendMessage() {
		String apiKey = "";
		String registrationId = "";
		int numOfRetries = 10;
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
