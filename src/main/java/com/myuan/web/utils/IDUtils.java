package com.myuan.web.utils;

import java.util.Random;

public class IDUtils {
	public static String genImageName() {
		
		long millis = System.currentTimeMillis();
		Random random = new Random();
		int end3 = random.nextInt(999);
		String str = millis + String.format("%03d", end3);
		
		return str;
	}
	
	public static long getUserId() {
		long millis = System.currentTimeMillis();
		
		Random random = new Random();
		int end2 = random.nextInt(99);
		String str = millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}
	public static long getPostId() {
		long millis = System.currentTimeMillis();
		
		Random random = new Random();
		int end2 = random.nextInt(99);
		int start=random.nextInt(8)+1;
		String str =start+ millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}
	public static long getAnswerId() {
		long millis = System.currentTimeMillis();
		
		Random random = new Random();
		int end2 = random.nextInt(99);
		int start=random.nextInt(8)+1;
		int start2=random.nextInt(8)+1;
		String str =start+start2+ millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}
	
}
