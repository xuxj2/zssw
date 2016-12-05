package com.zrsf.backup.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStream2String {
	
	public static String inputStream2String(InputStream is, String charsetName) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[2048];
		int count = -1;
		String reString = null;
		try {
			while ((count = is.read(data, 0, 2048)) != -1) {
				outStream.write(data, 0, count);
			}
			data = null;
			reString = new String(outStream.toByteArray(), charsetName);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				outStream.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		return reString;
	}

	public static String inputStream2String(InputStream is) throws IOException {
		return inputStream2String(is, "UTF-8");
	}

}
