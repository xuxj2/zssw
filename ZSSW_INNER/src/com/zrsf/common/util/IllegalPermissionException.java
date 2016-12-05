package com.zrsf.common.util;
/**
 * 权限不合法异常，自定义异常
 * @author Terry
 *
 */
public class IllegalPermissionException extends Exception{
	private static final long serialVersionUID = 4048956474776414742L;

	public IllegalPermissionException() {
		super();
	}

	public IllegalPermissionException(String message) {
		super(message);
	}
}
