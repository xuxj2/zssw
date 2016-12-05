package test;

import java.util.Iterator;

import com.zrsf.common.util.FileUtil;

public class FileUtileTest {
	public static void main(String[] args) {	
		System.out.println(System.currentTimeMillis());
		FileUtil.initResource();
		Iterator<String> it=FileUtil.htmlCodeMap.keySet().iterator();
		while(it.hasNext()){
			System.out.print(FileUtil.htmlCodeMap.get(it.next())+" ");
		}
		System.out.println();
		it=FileUtil.dailiMap.keySet().iterator();
		while(it.hasNext()){
			System.out.println(FileUtil.dailiMap.get(it.next())+" ");
		}
		it=FileUtil.webMap.keySet().iterator();
		while(it.hasNext()){
			System.out.println(FileUtil.webMap.get(it.next())+" ");
		}
		
	}

}
