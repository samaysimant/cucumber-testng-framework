package com.automationframeworks.Utility;

import java.util.HashMap;
import java.util.Map;

public class DataHandler {

	public static Map<String,String> getData= new HashMap<>();
	
	public static String getDataFromMap(String key) {
		return getData.get(key);
	}
}
