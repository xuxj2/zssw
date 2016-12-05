package com.zrsf.forclient.service;

import java.util.List;
import java.util.Map;

import com.zrsf.forclient.dao.DbrwDao;
import com.zrsf.forclient.util.PagingUtil;
import com.zrsf.forclient.vo.ResponseObject;
import com.zrsf.forclient.vo.Swry;
import com.zrsf.forclient.vo.TodoTask;

public class DbrwService {
	private DbrwDao dao;	
	public DbrwDao getDao() {
		return dao;
	}
	public void setDao(DbrwDao dao) {		
		this.dao = dao;
	}

/**
 * 查询某个税务人员的待办任务
 * @param swry
 * @return
 */
	public ResponseObject queryToDoList(Swry swry,int pagecode) {
		Map<String, Object> map = PagingUtil.addPageParameter(pagecode);
		map.put("swryDm", swry.getSwryDm());
		ResponseObject resp = new ResponseObject();
		List<TodoTask> entity=dao.selectDbrw(map);
		if(entity!=null&&entity.size()>0){
			resp.setEntity(entity);
		}		
		resp.setCode(ResponseObject.successCode);
		return resp;
	}

	
	
}
