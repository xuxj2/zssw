package com.zrsf.forclient.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.zrsf.forclient.dao.TaxCalendarDao;
import com.zrsf.forclient.vo.ResponseObject;
import com.zrsf.forclient.vo.TaxCalendarVo;
import com.zrsf.forclient.vo.TaxcalendarDetailVo;

public class TaxCalendarService {
	private TaxCalendarDao dao;
	public TaxCalendarDao getDao() {
		return dao;
	}
	public void setDao(TaxCalendarDao dao) {
		this.dao = dao;
	}

	public ResponseObject getCalendarList(String year) {
		ResponseObject resp=new ResponseObject();
		if(!year.matches("20\\d{2}")){
			resp.setCode(ResponseObject.failCode);
			resp.setMessage("参数不正确");
			return resp;
		}
		List<TaxCalendarVo> entity=dao.selectCalendarList(year);
		System.out.println(entity.size());
		if(entity!=null&&entity.size()<1){
			entity=null;
		}	
		
		resp.setCode(ResponseObject.successCode);
		resp.setEntity(entity);
		resp.setMessage("成功");
		return resp;
	}

	public ResponseObject getCalendarDetail(String id) {
		ResponseObject resp=new ResponseObject();
		if(StringUtils.isEmpty(id)){
			resp.setCode(ResponseObject.failCode);
			resp.setMessage("参数不能为空");
			return resp;
		}
		List<TaxcalendarDetailVo> entity=dao.selectCalendarDetail(id);			
		resp.setCode(ResponseObject.successCode);
		resp.setEntity(entity);
		resp.setMessage("成功");
		return resp;
	}

	
	public static void main(String[] args) {
		System.out.println(!"20104".matches("20\\d{2}"));
	}
}
