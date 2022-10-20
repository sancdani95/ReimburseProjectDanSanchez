package com.revature.repository;

import jakarta.servlet.http.Cookie;

public class CookieRepository {

	public static Cookie aCookie() {
		Cookie aCookie = new Cookie("auth","true");
		aCookie.setHttpOnly(true);
		return aCookie;
	}
	
	public static Cookie eCookie() {
		Cookie eCookie = new Cookie("role","employee");
		eCookie.setHttpOnly(true);
		return eCookie;
	}
	
	public static Cookie mCookie() {
		Cookie mCookie = new Cookie ("role", "manager");
		mCookie.setHttpOnly(true);
		return mCookie;
	}
	
	public static Cookie laCookie() {
		Cookie laCookie = new Cookie("auth", "false");
		laCookie.setHttpOnly(true);
		return laCookie;
	}
	
	public static Cookie lrCookie() {
		Cookie lrCookie = new Cookie ("role","none");
		lrCookie.setHttpOnly(true);
		return lrCookie;
	}
	
	
	//public static boolean checkAuth() {
		
		//return boolean;
	//}
}
