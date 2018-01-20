package com.myuan.web.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class SessionUtil {
	
	public static void setSession(HttpServletRequest request,String name,String value) {
		
		HttpSession session=request.getSession();
		session.setAttribute(name, value);
	}
	public static void setSessionPojo(HttpServletRequest request,String name,Object user) {
		
		HttpSession session=request.getSession();
		session.setAttribute(name, user);
	}
	public  static  String  getSession(HttpServletRequest request,String name) {
		
		HttpSession session=request.getSession();
		String value=(String) session.getAttribute(name);
		return value;
	}
	public static boolean checkEmpty(HttpServletRequest request,String name) {
		
		HttpSession session=request.getSession();
		String value=(String) session.getAttribute(name);
		if(value==null) {
			return false;
		}
		return true;
	}
	
	
}
