package com.zrsf.manage.action;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.manage.service.NewsService;
import com.zrsf.manage.vo.News;
import com.zrsf.manage.vo.PageModel;

public class NewsAction extends BaseAction {
	private String columnId;	
	private News news;
	private PageModel<News> page;
	private boolean done;
	
	
	private NewsService service;	
	public NewsService getService() {
		return service;
	}
	public void setService(NewsService service) {
		this.service = service;
	}

	public String execute(){
		return Action.SUCCESS;
	}

	public String list(){
		page=service.selectPage(columnId,page);		
		return "list";
	}
	public String delete(){	
		done=service.deleteOne(columnId,news.getId());		
		return "delete";
	}
	
	public String status(){	
		done=service.updateStatus(columnId,news);
		return "status";
	}
	
	public String modify(){	
		done=service.modify(news.getColumnId(),news);
		return "modified";
	}
	
	public String detail(){	
		news.setColumnId(columnId);
		news=service.selectNewsDetail(columnId,news);
		return "detail";
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	
	public PageModel<News> getPage() {
		return page;
	}
	public void setPage(PageModel<News> page) {		
		this.page = page;
	}	
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}

}
