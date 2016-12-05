package com.zrsf.forclient.util;

public class Validater {
	public static final String swglmRegex="^3205\\d{11}$";
	public static final String dateRegex="(^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0?[13578]|1[02])-(0?[1-9]|[12][0-9]|3[01]))|((0?[469]|11)-(0?[1-9]|[12][0-9]|30))|(0?2-(0[1-9]|1[0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|(([02468][048])|([13579][26])00))-0?2-29)$)";

	/**
	 * 验证传入的税务管理码参数是否合法：3205开头的15位数字
	 * @param swglm
	 * @return
	 */
	public static  boolean validateSwglm(String swglm){
		return swglm.matches(swglmRegex);
	}
	
	/**
	 * 验证传入的日期格式是否合法：满足yyyy-MM-dd格式
	 * @param date
	 * @return
	 */
	public static  boolean validateDate(String date){
		return date.matches(dateRegex);
	}	
	
}
