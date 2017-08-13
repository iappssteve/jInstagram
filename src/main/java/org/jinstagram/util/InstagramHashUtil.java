
package org.jinstagram.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.jinstagram.InstagramConstants;

/**
 * Utilities related to hash
 * 
 * @author
 *
 */
public class InstagramHashUtil {
	/**
	 * The characters from a hex-string
	 */
	public static final String XLATE = "0123456789abcdef";

	/**
	 * Digest a string using the given codec and input
	 * 
	 * @param codec
	 *            Codec to use
	 * @param source
	 *            Source to use
	 * @return
	 */
	protected static String digest(String codec, String source) {
		try {
			MessageDigest digest = MessageDigest.getInstance(codec);
			byte[] digestBytes = digest.digest(source.getBytes(Charset.forName("UTF-8")));
			return hexlate(digestBytes, digestBytes.length);
		} catch (NoSuchAlgorithmException nsae) {
			throw new RuntimeException(codec + " codec not available");
		}
	}

	/**
	 * Get the MD5 (in hexadecimal presentation) for the given source
	 * 
	 * @param source
	 *            The string to hash
	 * @return MD5 hex presentation
	 */
	public static String md5hex(String source) {
		return digest("MD5", source);
	}

	/**
	 * Convert the byte array to a hexadecimal presentation (String)
	 * 
	 * @param bytes
	 *            byte array
	 * @param initialCount
	 *            count (length) of the input
	 * @return
	 */
	protected static String hexlate(byte[] bytes, int initialCount) {
		if (bytes == null) {
			return "";
		}

		int count = Math.min(initialCount, bytes.length);
		char[] chars = new char[count * 2];

		for (int i = 0; i < count; ++i) {
			int val = bytes[i];
			if (val < 0) {
				val += 256;
			}
			chars[(2 * i)] = XLATE.charAt(val / 16);
			chars[(2 * i + 1)] = XLATE.charAt(val % 16);
		}

		return new String(chars);
	}

	/**
	 * Generates Instagram Device ID
	 * 
	 * @param username
	 *            Username to generate
	 * @param password
	 *            Password to generate
	 * @return device id
	 */
	public static String generateDeviceId(String username, String password) {
		String seed = md5hex(username + password);
		String volatileSeed = "12345";

		return "android-" + md5hex(seed + volatileSeed).substring(0, 16);
	}

	/**
	 * Generate a Hmac SHA-256 hash
	 * 
	 * @param key
	 *            key
	 * @param string
	 *            value
	 * @return hashed
	 */
	public static String generateHash(String key, String string) {
		SecretKeySpec object = new SecretKeySpec(key.getBytes(), "HmacSHA256");
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(object);
			byte[] byteArray = mac.doFinal(string.getBytes("UTF-8"));
			return new String(new Hex().encode(byteArray), "ISO-8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Generate signed payload
	 * 
	 * @param payload
	 *            Payload
	 * @return Signed string
	 */

	public static String generateSignature(String payload) {
		try {
			String parsedData = URLEncoder.encode(payload, "UTF-8");

			String signedBody = generateHash(InstagramConstants.API_KEY, payload);

			return "ig_sig_key_version=" + InstagramConstants.API_KEY_VERSION + "&signed_body=" + signedBody + '.'
					+ parsedData;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}
}
