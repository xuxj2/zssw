package com.zrsf.backup.http.outer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.util.ParserException;

import com.zrsf.common.util.FileUtil;

public abstract class NewsGrabBase {
	private static final Log LOG = LogFactory.getLog(NewsGrabBase.class);
	private HttpClient httpclient;
	protected String charsetName="utf-8";
	public String getCharsetName() {
		return charsetName;
	}
	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}
	/**
	 * 按照栏目名称抓取栏目信息 通过FileUtil的webMap配置文件获取栏目抓取url
	 * 
	 * @param column栏目
	 * @return
	 */
	public List<Map<String,String>> grabNews(String column) {		
		return grabNews(column, FileUtil.webMap.get(column));
	}

	/**
	 * 根据列表页url,批量抓取信息
	 * 
	 * @param column
	 *            栏目
	 * @param url
	 *            抓取的url地址
	 * @return 新闻内容信息list集合
	 */
	public List<Map<String,String>> grabNews(String column, String url) {	
		if(httpclient==null){
			httpclient=new HttpClient();
		}		
		List<Map<String,String>> urlList = getNewsLinkList(url);		
		List<Map<String,String>> newsList = null;
		if (urlList != null && urlList.size() > 0) {
			newsList = new ArrayList<Map<String,String>>();
			for (int i = 0; i < urlList.size(); i++) {//
				Map<String,String> vo = getNewsContent(column, urlList.get(i));
				if (vo != null) {									
					vo.put("sslm", column);					
					newsList.add(vo);					
				}
			}
		}
		return newsList;
	}

	/**
	 * 获取信息列表
	 * 
	 * @param url信息列表网页url
	 * @return
	 */
	private List<Map<String,String>> getNewsLinkList(String url) {
		List<Map<String,String>> list = null;
		try {
			String respHtmlStr = httpclient.getHtmlSource(url, HttpClient.POST);			
			try {
				list = this.parseList(url,respHtmlStr);
			} catch (Exception e) {
				LOG.error("解析[" + url + "]网站出错\n"+e);
				e.printStackTrace();
			}
		} catch (Exception e) {
			LOG.error("访问[" + url + "]失败\n"+e);	
			e.printStackTrace();
		}
		return list;
	}

	/** 
	 * 获取信息具体内容	 
	 * @param column 信息所属栏目
	 * @param url  信息原始网页url
	 * @return
	 */
	private Map<String,String> getNewsContent(String column, Map<String,String> map) {
		String url=map.get("sourceUrl");
		try {			
			String respHtmlStr = httpclient.getHtmlSource( url, HttpClient.GET);
			try {
				map = parseNews( map, respHtmlStr);				
			} catch (Exception e) {
				map=null;
				LOG.error("解析[" + column + " 栏目： " + url + "]出错\n"+e);	
				e.printStackTrace();				
			}
		} catch (Exception e) {
			LOG.error("访问[" + url + "]失败\n"+e);	
			e.printStackTrace();
		}
		return map;
	}

	/**
	 *  解析新闻列表网页中各条新闻的url	 	
	 * @param sourceUrl 新闻列表网页的url
	 * @param charsetName 网页编码的url
	 * @param respHtmlStr 网页源码字符串
	 * @return
	 * @throws ParserException
	 */
	 
	public abstract List<Map<String,String>> parseList(String sourceUrl,String respHtmlStr) throws ParserException;

	/**
	 * 对返回的html进行解析，返回信息详细内容
	 * 
	 * @param respHtmlStr  网页源码字符串
	 * @param sourceurl	原始网页url
	 * @param charsetName 网页字符编码
	 * @return map  
	 * @throws ParserException
	 */
	public abstract Map<String,String> parseNews(Map<String,String> map,String respHtmlStr) throws ParserException;

}
