package com.zrsf.forclient.service;

import com.zrsf.forclient.dao.IndividuationDao;
import com.zrsf.forclient.vo.ResponseObject;
import com.zrsf.forclient.vo.Swry;

public class IndividuationService {
	private IndividuationDao dao;

	public IndividuationDao getDao() {
		return dao;
	}

	public void setDao(IndividuationDao dao) {
		this.dao = dao;
	}

	public ResponseObject updateAvatar(String swryDm, String fileFileName) {
		ResponseObject resp = new ResponseObject();
		try {		
		dao.updateAvatar(swryDm, fileFileName);
		resp.setCode(ResponseObject.successCode);
		resp.setEntity("http://61.177.61.251:8222/shuixin/tx/" + fileFileName);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setCode(ResponseObject.failCode);
			resp.setMessage("记录修改信息出错");
		}
		return resp;
	}

	public ResponseObject updateIndInfo(Swry swryUp) {	
		ResponseObject resp = new ResponseObject();	
		if(swryUp.getXb()==null&&swryUp.getYddh()==null&&swryUp.getBgdh()==null){
			resp.setCode(ResponseObject.successCode);
			return resp;
		}		
		if(swryUp.getYddh()!=null&&!swryUp.getYddh().trim().matches("\\d{0,13}")){			
			resp.setCode(ResponseObject.failCode);
			return resp;
		}
		if(swryUp.getBgdh()!=null&&!swryUp.getBgdh().trim().matches("\\d{0,12}")){			
			resp.setCode(ResponseObject.failCode);
			return resp;
		}
		if(swryUp.getXb()!=null&&!swryUp.getXb().matches("[12]")){			
			resp.setCode(ResponseObject.failCode);
			return resp;
		}		
		try {
			dao.updateIndInfo(swryUp);
			resp.setCode(ResponseObject.successCode);			
		} catch (Exception e) {
			e.printStackTrace();
			resp.setCode(ResponseObject.failCode);
			resp.setMessage("入库出错");
		}
		return resp;
	}

}
