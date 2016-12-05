package com.zrsf.forclient.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PagingUtil {
	/**
	 * 添加分页参数
	 * 
	 * @param pagecode
	 * @param pagesize
	 * @return
	 */
	public static Map<String, Object> addPageParameter(int pagecode, int pagesize) {
		Map<String, Object> map = new HashMap<String, Object>();
		int startNum = 0;
		int endNum = 0;
		if (pagecode < 1) {
			pagecode = 1;
		}
		startNum = pagecode * pagesize - pagesize;
		endNum = pagecode * pagesize + 1;
		map.put("endNum", endNum);
		map.put("startNum", startNum);
		return map;
	}

	public static Map<String, Object> addPageParameter(int pagecode) {
		return addPageParameter(pagecode, 20);
	}
	
public static void main(String[] args) {
	UUID uuid=UUID.randomUUID();
	System.out.println(uuid);
}
}
