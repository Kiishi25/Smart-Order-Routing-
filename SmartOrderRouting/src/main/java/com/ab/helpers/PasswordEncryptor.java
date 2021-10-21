package com.ab.helpers;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncryptor {
	public static final String key = "DB99A2A8EB6904F492E9DF0595ED683C";
	public static final String AES = "AES";
	
	public static String encrypt(String password) {
		String encryptedPassword = null;
		try {
			
			byte[] bytekey = hexStringToByteArray(key);
			SecretKeySpec sks = new SecretKeySpec(bytekey, PasswordEncryptor.AES);
			Cipher cipher = Cipher.getInstance(PasswordEncryptor.AES);
	        cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
	        byte[] encrypted = cipher.doFinal(password.getBytes());
	        encryptedPassword = byteArrayToHexString(encrypted);
		}catch(Exception e) {
			//add later
		}
		return encryptedPassword;
	}
	
	public static String decrypt(String encryptedPassword) {
		String password = null;
		try {
			byte[] bytekey = hexStringToByteArray(key);
	        SecretKeySpec sks = new SecretKeySpec(bytekey, PasswordEncryptor.AES);
	        Cipher cipher = Cipher.getInstance(PasswordEncryptor.AES);
	        cipher.init(Cipher.DECRYPT_MODE, sks);
	        byte[] decrypted = cipher.doFinal(hexStringToByteArray(encryptedPassword));
	        password = new String(decrypted);
		}catch(Exception e) {
			//add later
		}
		return password;
	}
	
	private static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }
	
	private static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }
	  
}
