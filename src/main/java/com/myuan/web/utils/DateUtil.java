package com.myuan.web.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public final static String getDate(Date startTime) {
		
		   long min=(new Date().getTime()-startTime.getTime())/1000;//除以1000转换成秒
		   if(min<60) {
			   return "刚刚";
		   } else if(min>=60 && min<60*60) {
			   long num=min/60;
			   return num+"分钟前";
		   } else if(min>=60*60 && min< 60*60*24) {
			   long num=min/3600;
			   return num+"小时前";
		   } else if(min>=60*60*24 && min< 60*60*24*4){
			   long num=min/86400;
			   return num+"天前";
		   } else {
			   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			   return sdf.format(startTime);
		   }
		   
	}
}
