package com.constants;

import java.util.HashMap;
import java.util.Map;

public class Consts {
	public static final String ADMIN = "admin";
	public static final Integer STATUS_ALL = -1;
	public static final Long TWENTY_MINUTES_MS = 1200000L;
	public static final Double DEFAULT_LAT = 52.42426;
	public static final Double DEFAULT_LNG = 31.01457;

	public static final String ADMIN_APP_KEY = "AIzaSyCj6I4a8D7grQzJttdmxr_V2uHOfQuj_s4";
	public static final String CLIENT_APP_KEY = "AIzaSyCj6I4a8D7grQzJttdmxr_V2uHOfQuj_s4";
	public static final int NUMBER_OF_RETRIES = 3;

	public static Map<Integer, String> POSITION_NOTES = new HashMap<Integer, String>();
	public static Map<Integer, String> STATUS_BY_CODE = new HashMap<Integer, String>();
	public static Map<String, Integer> CODE_BY_STATUS = new HashMap<String, Integer>();

	static {
		POSITION_NOTES.put(1, "Common");
		POSITION_NOTES.put(2, "Near Window");
		POSITION_NOTES.put(3, "Outdoors");

		STATUS_BY_CODE.put(1, "PENDING");
		STATUS_BY_CODE.put(2, "APPROVED");
		STATUS_BY_CODE.put(3, "CANCELLED");
		STATUS_BY_CODE.put(4, "REJECTED");
		STATUS_BY_CODE.put(5, "CLOSED");
		STATUS_BY_CODE.put(6, "EXPIRED");
		
		CODE_BY_STATUS.put("PENDING", 1);
		CODE_BY_STATUS.put("APPROVED", 2);
		CODE_BY_STATUS.put("CANCELLED", 3);
		CODE_BY_STATUS.put("REJECTED", 4);
		CODE_BY_STATUS.put("CLOSED", 5);
		CODE_BY_STATUS.put("EXPIRED", 6);
	}

}
