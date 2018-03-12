package com.drest.app.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.drest.app.exceptions.AppException;
import com.drest.app.exceptions.AppException.ERROR_CODE;

public class EncryptionUtil {

	private static char[] digits =
			{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'y', 'X', 'd', 'N', 'T' };

	public static String encrypt(String text, String saltKey) throws AppException {

		String toEncrypt = saltKey + text;
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(toEncrypt.getBytes());

			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			throw new AppException(ERROR_CODE.CRYPT_MD5_001, "Could not encrpt string", e);
		}

		return hash.toString();
	}

}
