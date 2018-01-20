package com.myuan.web.utils;

public class TotalUtil {
	
	public static final int gePagetTotal(long total,int size) {
		
		double index=(double)total/(double)size;
		if(index==(int)index) {
			return (int)index;
		} else {
			return (int)index+1;
		}
	}
}
