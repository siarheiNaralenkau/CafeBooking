package com.constants;

import java.util.HashMap;
import java.util.Map;

public class Consts {
	public static final String ADMIN = "admin";
	public static final Integer STATUS_ALL = -1;
	public static final Long TWENTY_MINUTES_MS = 1200000L;
	public static final Double DEFAULT_LAT = 52.42426;
	public static final Double DEFAULT_LNG = 31.01457;
	
	public static final String ADMIN_APP_KEY = "AIzaSyBQ5MR92uJ1qejeO4zJsRH-bSvIOKH2aq0";
	public static final int NUMBER_OF_RETRIES = 3;
	
	public static Map<Integer, String> POSITION_NOTES = new HashMap<Integer, String>();
	static {
		POSITION_NOTES.put(1, "Common");
		POSITION_NOTES.put(2, "Near Window");
		POSITION_NOTES.put(3, "Outdoors");
	}
}
