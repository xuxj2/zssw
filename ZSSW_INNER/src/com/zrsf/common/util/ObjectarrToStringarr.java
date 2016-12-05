package com.zrsf.common.util;

public class ObjectarrToStringarr {
	
	public String[] toStringarr(Object[] o){
		String[] stringarr=new String[o.length];
		for(int i=0;i<o.length;i++){
			stringarr[i]=o[i].toString();
		}
		return stringarr;
	}
}
