package com.revature.repository;

import jakarta.servlet.http.Cookie;

public class CookieRepository {

	//created cookie for authentication
	public static Cookie aCookie() {
		Cookie aCookie = new Cookie("auth","true");
		aCookie.setHttpOnly(true);
		return aCookie;
	}
	
	//Cookie to save User name
	public static Cookie uCookie(String sentUser) {
		Cookie uCookie = new Cookie("user", sentUser);
		uCookie.setHttpOnly(true);
		return uCookie;
	}
	
	//Cookie to assign employee role
	public static Cookie eCookie() {
		Cookie eCookie = new Cookie("role","employee");
		eCookie.setHttpOnly(true);
		return eCookie;
	}
	
	
	//Cookie to assign manager role
	public static Cookie mCookie() {
		Cookie mCookie = new Cookie ("role", "manager");
		mCookie.setHttpOnly(true);
		return mCookie;
	}
	
	//Cookie to force close authentication cookie
	public static Cookie laCookie() {
		Cookie laCookie = new Cookie("auth", "false");
		laCookie.setHttpOnly(true);
		return laCookie;
	}
	
	//Cookie to force close user cookie
	public static Cookie luCookie() {
		Cookie luCookie = new Cookie("user", "none");
		luCookie.setHttpOnly(true);
		return luCookie;
	}
	
	//Cookie to force close role cookie
	public static Cookie lrCookie() {
		Cookie lrCookie = new Cookie ("role","none");
		lrCookie.setHttpOnly(true);
		return lrCookie;
	}
	
	
	public static boolean checkAuth(Cookie[] rCookies) {
		
		if(rCookies == null) {
			return false;
			
		} else {
			for (Cookie cookie : rCookies) {
				
				if (cookie.getName().equals("auth") && cookie.getValue().equals("true")) {    
				return true;
				}  
			}
			return false;
		}
	}
	
	
 public static Boolean getRole(Cookie[] rCookies) {
		
	 
		if(rCookies == null) {
			return false;
			
		} else {
			for (Cookie cookie : rCookies) {
				
				if (cookie.getName().equals("role") && cookie.getValue().equals("manager")) { 
					return true;
				}  
			}
			return false;
		}
	}
	
 
 public static String getUser (Cookie[] rCookies) {
		
	 String user = null;
	 
		if(rCookies == null) {
			return user;
			
		} else {
			for (Cookie cookie : rCookies) {
				
				if (cookie.getName().equals("user")) {    
					user = cookie.getValue();
				return user;
				}  
			}
			return user;
		}
	}
	
}
