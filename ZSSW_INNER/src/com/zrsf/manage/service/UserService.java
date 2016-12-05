package com.zrsf.manage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zrsf.common.util.IllegalPermissionException;
import com.zrsf.common.util.MD5Util;
import com.zrsf.common.util.PermissionUtil;
import com.zrsf.forclient.dao.SwryDao;
import com.zrsf.forclient.vo.Swry;
import com.zrsf.manage.vo.txlJsonBean;

public class UserService {
	private SwryDao dao;
	public SwryDao getDao() {
		return dao;
	}
	public void setDao(SwryDao dao) {		
		this.dao = dao;
	}


	public Swry login(String name, String password) {
		password=MD5Util.md5(password);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("swryGh", name);
		map.put("password", password);
	
		String sql="com.zrsf.map.login.selectLoginSwry";
		Swry user=dao.selectSwry(sql, map);
		return user;
	}
	
	public List<Swry> selectOwners() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("permissions", PermissionUtil.OWNER);
		List<Swry> owners=dao.selectSwrys("com.zrsf.map.txl.selectSwryByPermissions", map);
		return owners;
	}
	public List<Swry> selectManagers() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("permissions", PermissionUtil.MANAGER);
		List<Swry> managers=dao.selectSwrys("com.zrsf.map.txl.selectSwryByPermissions", map);
		return managers;
	}
	/**
	 * 移除用户某项权限
	 * @param swryGh
	 * @param limit 
	 * @throws IllegalPermissionException 
	 */
	public void deletePermission(String swryGh, int permission) {
		String sql="com.zrsf.map.txl.removePermission";		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("swryGh", swryGh);
		map.put("permission", permission);
		dao.updateLimit(sql,map);		
	}
	/**
	 * 给用户添加某项权限
	 * @param swryGh
	 * @param perm
	 */
	public void updatePermission(String swryGhs, String perm) {
		swryGhs=swryGhs.trim();
		if(!swryGhs.isEmpty()){
			String[] swryGh=swryGhs.split(",");
			String sql="com.zrsf.map.txl.addPermission";
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("ghs", swryGh);
			map.put("permission", perm);
			dao.updateLimit(sql,map);	
		}			
	}
	
	/**
	 * 查询组装 添加管理员时的可选人员树
	 * @param permission  
	 * @return
	 */
	public List<txlJsonBean> combineSelectable(int permission) {
		List<txlJsonBean> org=dao.selectSwjg("com.zrsf.manage.manager.selectSwjg");
		List<txlJsonBean> peos=dao.selectSelectable("com.zrsf.manage.manager.selectSelectable",permission);		
		org.addAll(peos);
	
		return org;
	}
	

}
