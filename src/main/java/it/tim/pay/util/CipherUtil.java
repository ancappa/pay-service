package it.tim.pay.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CipherUtil {

	
	public static String encrypt(String key, String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		
			Cipher cipher = null;
			if (value.length() < 16)
				cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			else
				cipher = Cipher.getInstance("AES/CBC/NOPADDING");
			
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			return Hex.encodeHexString(encrypted);
		} catch (Exception ex) {
			log.error("Exception during encryption operation: "+ ex.getMessage());
		}

		return value;
	}

	public static String decrypt(String key, String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = null;
			if (encrypted.length() < 16)
				cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			else
				cipher = Cipher.getInstance("AES/CBC/NOPADDING");

			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] b = new byte[encrypted.length() / 2];
			for (int i = 0; i < b.length; i++) {
				int index = i * 2;
				int v = Integer.parseInt(encrypted.substring(index, index + 2), 16);
				b[i] = (byte) v;
			}

			byte[] original = cipher.doFinal(b);

			return new String(original).trim();
		} catch (Exception ex) {
			log.error("Exception during decryption operation: "+ ex.getMessage());
		}
		return encrypted;
	}
}