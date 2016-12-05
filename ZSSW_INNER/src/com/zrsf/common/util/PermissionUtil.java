package com.zrsf.common.util;

public class PermissionUtil {
	/**
	 * 公共权限，连接上都可使用
	 */
	public final static int PUBLIC = 0; // 公共权限，连接上都可使用
	/**
	 * 登录
	 */
	public final static int LOGIN = 1; // 登录
	/**
	 * 系统管理员
	 */
	public final static int MANAGER = 2; // 系统管理员
	/**
	 * 系统超级管理员
	 */
	public final static int OWNER = 4; // 系统超级管理员
	/**
	 * 寒山闻钟权限值
	 */
	public final static int HSWZ = 8; // 寒山闻钟权限值
	/**
	 * 局长信箱权限值
	 */
	public final static int JZXX = 16; // 局长信箱权限值
	/**
	 * 征管报表权限值
	 */
	public final static int ZGBB = 32; // 征管报表权限值
	/**
	 * 规财报表权限值
	 */
	public final static int GCBB = 64; // 规财报表权限值

	/**
	 * 判断权限值是否包含某种权限
	 * 
	 * @param limitValue
	 *            权限值，自然数
	 * @param limit
	 *            权限：2的幂
	 * @return
	 * @throws InvalideLimitException
	 */
	public static boolean hasPermission(int value, int permission)
			throws IllegalPermissionException {
		if (!isPermission(permission)) {
			throw new IllegalPermissionException(permission
					+ "is not a legal permission");
		}
		// if (value < permission) {
		// return false;
		// }
		int p = value & permission;
		return p == permission;
	}

	/**
	 * 判断两权限值有无0以外的交叉项
	 * @param value
	 * @param permissions
	 * @return
	 * @throws IllegalPermissionException
	 */
	public static boolean hasOneOFPermission(int value, int permissions)
			throws IllegalPermissionException {
		if (value<0||permissions < 0) {
			throw new IllegalPermissionException(permissions
					+ "can not little than 0");
		}		
		int p = value & permissions;
		return p >0;
	}

	/**
	 * 判断权限值是否包含某些权限
	 * 
	 * @param limitValue
	 *            权限值，自然数
	 * @param limit
	 *            权限：多个2的幂 的和
	 * @return
	 * @throws InvalideLimitException
	 */
	public static boolean hasPermissions(int value, int permissions)
			throws IllegalPermissionException {
		if (permissions < 0) {
			throw new IllegalPermissionException(permissions
					+ "can not little than 0");
		}
		// if (value < permissions) {
		// return false;
		// }
		int p = value & permissions;
		return p == permissions;
	}

	/**
	 * 向权限值中添加单一权限
	 * 
	 * @param value
	 * @param permission
	 * @return
	 * @throws IllegalPermissionException
	 */
	public static int addPermission(int value, int permission)
			throws IllegalPermissionException {
		if (!isPermission(permission)) {
			throw new IllegalPermissionException(permission
					+ "is not a legal permission");
		}
		// if (!hasPermission(value, permission)) {
		// value = value + permission;
		// }
		value = value | permission;
		return value;
	}

	/**
	 * 向权限值中添加一些权限
	 * 
	 * @param value
	 * @param permission
	 * @return
	 * @throws IllegalPermissionException
	 */
	public static int addPermissions(int value, int permissions)
			throws IllegalPermissionException {
		// int [] pers=getPermissions(permissions);
		// for(int permission:pers){
		// if (!hasPermission(value, permission)) {
		// value = value + permission;
		// }
		// }
		if (permissions < 0) {
			throw new IllegalPermissionException(permissions
					+ "can not little than 0");
		}
		value = value | permissions;
		return value;
	}

	/**
	 * 从权限值中移除权限
	 * 
	 * @param value
	 * @param permission
	 * @return
	 * @throws IllegalPermissionException
	 */
	public static int removePermission(int value, int permission)
			throws IllegalPermissionException {
		if (!isPermission(permission)) {
			throw new IllegalPermissionException(permission
					+ "is not a legal permission");
		}
		// if (hasPermission(value, permission)) {
		// value = value - permission;
		// }
		value = (value | permission) ^ permission;
		return value;
	}

	/**
	 * 从权限值中移除一些权限
	 * 
	 * @param value
	 * @param permission
	 * @return
	 * @throws IllegalPermissionException
	 */
	public static int removePermissions(int value, int permissions)
			throws IllegalPermissionException {
		if (permissions < 0) {
			throw new IllegalPermissionException(permissions
					+ "can not little than 0");
		}
		value = (value | permissions) ^ permissions;
		return value;
	}

	/**
	 * 返回该权限值的全部权限
	 * 
	 * @param value
	 * @return
	 * @throws IllegalPermissionException
	 */
	public static int[] getPermissions(int value)
			throws IllegalPermissionException {
		if (value < 0) {
			throw new IllegalPermissionException("权限值" + value + "不能小于0");
		}
		int[] permissions = { 0 };
		int k = 1;
		int[] temp = null;
		do {
			if ((value & 1) == 1) {
				temp = new int[permissions.length + 1];
				System.arraycopy(permissions, 0, temp, 0, permissions.length);
				temp[temp.length - 1] = k;
				permissions = temp;
			}
			value >>>= 1;
			k = k * 2;
		} while (value != 0);

		return permissions;
	}

	/**
	 * 判断是否合法的权限：2^n或0,（n为自然数）
	 * 
	 * @param limit
	 *            要判断的权限
	 * @return
	 */
	public static boolean isPermission(int limit) {
		return (limit & (limit - 1)) == 0;
	}

//	 public static int[] getBanary(int i) {
//	 int[] temp = new int[32];
//	 int pos = 32;
//	 do {
//	 temp[--pos] = i & 1;
//	 i >>>= 1;
//	 } while (i != 0);
//	 int[] bina = new int[32 - pos];
//	 System.arraycopy(temp, pos, bina, 0, bina.length);
//	 return bina;
//	 }

//	public static void main(String[] args) throws IllegalPermissionException {
//		System.out.println(PermissionUtil.hasOneOFPermission(6,10));
//	}
	
}
