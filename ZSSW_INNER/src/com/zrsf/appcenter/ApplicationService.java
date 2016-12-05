package com.zrsf.appcenter;

import java.util.List;

import com.zrsf.forclient.vo.ResponseObject;



public class ApplicationService {
	private ApplicationDao dao;
	public ApplicationDao getDao() {
		return dao;
	}
	public void setDao(ApplicationDao dao) {
		this.dao = dao;
	}
	public ApplicationService(){
		super();
	}	
	
	/**
	 * 获取某个app的最新信息
	 * @param packageName 安卓应用用于唯一区别的包路径名
	 * @return
	 */	 
	public ResponseObject lastest(String packageName){
		ApplicationVo app=dao.selectAppDetail(packageName);
		ResponseObject resp=new ResponseObject();
		resp.setCode(ResponseObject.successCode);
		resp.setEntity(app);
		return resp;
	}
	
	
	
	//添加新应用
	public void addNewApp(ApplicationVo app){
		
	
	//修改应用
	
	
	//保存上传的文件
	
	//
	
	}
	
	/**
	 * 查询所有应用列表
	 * @return
	 */
	public ResponseObject list() {
		List<ApplicationVo> entity=dao.selectAllApps();
		ResponseObject resp=new ResponseObject();
		resp.setCode(ResponseObject.successCode);
		resp.setEntity(entity);
		return resp;
		
	}
	
}
