package com.revature.repository;

import jakarta.servlet.http.Cookie;

public class CookieRepository {

	public static Cookie aCookie() {
		//create cookies for session handling
		Cookie aCookie = new Cookie("authenticated","true");
		aCookie.setHttpOnly(true);
	
	return aCookie;
	}
	
	public static Cookie eCookie() {
		Cookie eCookie = new Cookie("boss","false");
		eCookie.setHttpOnly(true);
		
		return eCookie;
	}
	
	public static Cookie mCookie() {
		Cookie mCookie = new Cookie("boss", "true");
		mCookie.setHttpOnly(true);
	
		return mCookie;
	}
	
}
