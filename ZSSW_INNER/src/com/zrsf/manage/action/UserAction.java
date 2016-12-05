package com.zrsf.manage.action;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.common.util.IllegalPermissionException;
import com.zrsf.common.util.PermissionUtil;
import com.zrsf.forclient.vo.Swry;
import com.zrsf.manage.service.UserService;
import com.zrsf.manage.vo.txlJsonBean;

public class UserAction extends BaseAction {
	private String name;
	private String password;
	private boolean logon;
	private Swry user;

	private UserService service;

	public String execute() {
		return Action.LOGIN;
	}

	public String login() {
		user = service.login(name, password);
		try {
			if (user != null
					&& PermissionUtil.hasOneOFPermission(user.getLimit(),
							PermissionUtil.MANAGER + PermissionUtil.OWNER)) {
				session.put("user", user);
				logon = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!logon) {
			return Action.LOGIN;
		}
		return Action.SUCCESS;
	}

	public String list() {
		List<Swry> owners = service.selectOwners();
		List<Swry> managers = service.selectManagers();
		request.setAttribute("owners", owners);
		request.setAttribute("managers", managers);
		return "list";
	}

	/**
	 * 删除权限
	 * 
	 * @return
	 */
	public String remove() {
		try {
			Swry swry = (Swry) session.get("user");
			if (swry != null
					&& PermissionUtil.hasPermission(swry.getLimit(),
							PermissionUtil.OWNER)) {
				String swryGh = request.getParameter("swryGh");
				String perm = request.getParameter("permission");
				int permission = Integer.parseInt(perm);
				service.deletePermission(swryGh, permission);
				logon = true;
			}
		} catch (IllegalPermissionException e) {
			e.printStackTrace();
			logon = false;
		}
		return "add";
	}

	/**
	 * 返回前端可选人员树
	 */
	public String selectable(){
		String perm = request.getParameter("permission");
		int permission = Integer.parseInt(perm);
		List<txlJsonBean> tree=service.combineSelectable(permission);
		request.setAttribute("tree", tree);
		return "tree";
	}
	
	/**
	 * 添加权限
	 * 
	 * @return
	 */
	public String add() {
		try {
			Swry swry = (Swry) session.get("user");
			if (swry != null
					&& PermissionUtil.hasPermission(swry.getLimit(),
							PermissionUtil.OWNER)) {
				String swryGhs = request.getParameter("swryGhs");
				String perm = request.getParameter("permission");								
				service.updatePermission(swryGhs, perm);
				logon = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logon = false;
		}
		return this.list();
	}

	public String logout() {
		session.clear();
		return Action.LOGIN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogon() {
		return logon;
	}

	public Swry getUser() {
		return user;
	}

	public void setUser(Swry user) {
		this.user = user;
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public void setLogon(boolean logon) {
		this.logon = logon;
	}

}
