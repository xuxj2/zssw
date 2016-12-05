package com.zrsf.manage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zrsf.manage.dao.NewsDao;
import com.zrsf.manage.vo.News;
import com.zrsf.manage.vo.PageModel;

public class NewsService {
	private NewsDao dao;
	public NewsDao getDao() {
		return dao;
	}
	public void setDao(NewsDao dao) {
		this.dao = dao;
	}


	public PageModel<News> selectPage(String columnId, PageModel<News> page) {
		Map<String, Object> paramsMap=new HashMap<String, Object>();
		paramsMap.put("columnId", columnId);
		paramsMap.put("startNum", page.getPageNo()*page.getPageSize()-page.getPageSize()+1);
		paramsMap.put("endNum", page.getPageSize()*page.getPageNo());
		
		String listSql="com.zrsf.manage.news.selectXwdtList";
		String amountSql="com.zrsf.manage.news.selectXwdtAmount";
		if(columnId.startsWith("zsk")||columnId.startsWith("fgk")){
			listSql="com.zrsf.manage.news.selectZcfgList";
			amountSql="com.zrsf.manage.news.selectZcfgAmount";
		}else if(columnId.startsWith("jzxx")||columnId.startsWith("hswz")){
			listSql="com.zrsf.manage.news.selectTsclList";
			amountSql="com.zrsf.manage.news.selectTsclAmount";
		}
		
		List<News> list=dao.selectNewsList(listSql, paramsMap);		
		page.setList(list);	
		int amount=dao.selectNewsAmount(amountSql,columnId);
		page.setTotalRecords(amount);
		return page;
	}
	
	public boolean deleteOne(String columnId, String id) {
		String deleteSql="com.zrsf.manage.news.deleteXwdtById";
		if(columnId.startsWith("zsk")||columnId.startsWith("fgk")){
			deleteSql="com.zrsf.manage.news.deleteZcfgById";			
		}else if(columnId.startsWith("jzxx")||columnId.startsWith("hswz")){
			deleteSql="com.zrsf.manage.news.deleteTsclById";		
		}
		try {
			dao.deleteOne(deleteSql,id);
		} catch (Exception e) {
			return false;
		}		
		return true;
	}
	public boolean updateStatus(String columnId, News news) {
		String statusSql="com.zrsf.manage.news.upXwdtStaById";
		if(columnId.startsWith("zsk")||columnId.startsWith("fgk")){			
			return false;
		}else if(columnId.startsWith("jzxx")||columnId.startsWith("hswz")){
			statusSql="com.zrsf.manage.news.upTsxxStaById";		
		}
		try {
			dao.upstaOne(statusSql,news);
		} catch (Exception e) {
			return false;
		}		
		return true;
	}
	public boolean modify(String columnId, News news) {
		String sql="com.zrsf.manage.news.updateXwdtNews";
		if(columnId.startsWith("zsk")||columnId.startsWith("fgk")){
			sql="com.zrsf.manage.news.updateZcfgNews";			
		}else if(columnId.startsWith("jzxx")||columnId.startsWith("hswz")){
			sql="com.zrsf.manage.news.updateTsxxNews";			
		}
		try {
		dao.modify(sql,news);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public News selectNewsDetail(String columnId, News news) {
		String sql="com.zrsf.manage.news.selectXwdtNewsById";
		if(columnId.startsWith("zsk")||columnId.startsWith("fgk")){
			sql="com.zrsf.manage.news.selectZcfgNewsById";			
		}else if(columnId.startsWith("jzxx")||columnId.startsWith("hswz")){
			sql="com.zrsf.manage.news.selectTsxxNewsById";			
		}		
		news=dao.selectNewsDetail(sql,news);		
		return news;
	}
	
	
}
