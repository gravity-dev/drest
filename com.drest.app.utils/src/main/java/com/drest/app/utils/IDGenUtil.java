package com.drest.app.utils;

import java.util.UUID;

/**
 * Random Id generator class.
 * 
 * @author arvind
 *
 */
public class IDGenUtil {

	/**
	 * Return the unique id generated for the string like url
	 * 
	 * @param idString
	 * @return
	 */
	public static String getStringUUID(String idString) {
		final String uuid = UUID.nameUUIDFromBytes(idString.getBytes()).toString();
		return uuid;
	}

	/**
	 * Compare if a idSting matches corresponding hash uuid
	 * 
	 * @param idString
	 * @param uuid
	 * @return
	 */
	public static boolean matchesUUID(String idString, String uuid) {
		final String genid = UUID.nameUUIDFromBytes(idString.getBytes()).toString();
		return genid.equals(uuid);
	}
}
