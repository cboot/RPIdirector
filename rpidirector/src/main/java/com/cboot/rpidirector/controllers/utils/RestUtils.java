package com.cboot.rpidirector.controllers.utils;

import java.util.Base64;

public final class RestUtils {

	private RestUtils() {}
	
	public static String getUserIdFromBearerAuthorizationHeader(String authorizationHeader) {
		return new String(Base64.getDecoder().decode(authorizationHeader.substring("Bearer ".length()))).split(":")[0];
	}
}
