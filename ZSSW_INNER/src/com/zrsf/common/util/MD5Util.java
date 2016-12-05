package com.zrsf.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {

	public static String md5(String str){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] b1 = md.digest(str.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(b1);
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(MD5Util.md5("123456"));;
	}
	
	
}
