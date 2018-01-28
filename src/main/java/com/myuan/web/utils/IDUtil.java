package com.myuan.web.utils;

import java.util.Random;

public class IDUtil {
	public static String genImageId() {
		
		long millis = System.currentTimeMillis();
		Random random = new Random();
		int end3 = random.nextInt(999);
		String str = millis + String.format("%03d", end3);
		
		return str;
	}
}
