package com.zrsf.backup.http.outer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.zrsf.common.util.HtmlCodeMap;
/**
 * 用于知识库的网页抓取：包含三个栏目，税收法规、办税流程、热点问题
 * @author Terry
 *
 */
public class ZskGrab extends NewsGrabBase{	
	private String urlTitle = "http://221.226.40.115:7001/";

	/**
	 * @Override
	 * 获取新闻列表中各条新闻的url
	 * 
	 * @param url
	 *            新闻列表的url
	 * @return 多条新闻的url
	 * @throws ParserException
	 */
	public List<Map<String, String>> parseList(String sourceUrl, String respHtmlStr)
			throws ParserException {		
		if ("".equals(respHtmlStr)||respHtmlStr.indexOf("Sword 出错信息提示")>0) {
			return null;
		}
		List<Map<String, String>> list = null;
			Parser parser = Parser.createParser(respHtmlStr, charsetName);
			NodeList anodeList = parser
					.extractAllNodesThatMatch(new TagNameFilter("a"));
			if (anodeList != null && anodeList.size() > 0) {
				list =new ArrayList<Map<String, String>>();
				String href = "jswwzsk/sword?tid=ZskjsBLH_findFgContent&id=";
				String href2 = "&title=&zlnr=&qybm=232000000";
				for (int i = 0; i < anodeList.size() - 2; i++) {
					LinkTag aTag = (LinkTag) anodeList.elementAt(i);
					String link = aTag.getLink();
					//<a href="javascript:showMx('2320000002011092600037','1')
					// showMx('2320000002011092200027','2');
//					link = StringUtils.replaceEach(
//							link ,HtmlCodeMap.htmlcode, HtmlCodeMap.sign).trim();
					String newsurl = "";
					try {
						link = link.substring(link.indexOf("'") + 1, link
								.indexOf("'", 20));
						link=StringUtils.replaceEach(
								link ,HtmlCodeMap.htmlcode, HtmlCodeMap.sign).trim();
					} catch (Exception e) {
						continue;
					}					
					newsurl = urlTitle + href + link + href2;	
					Map<String, String> map=new HashMap<String, String>();
					map.put("id","zsk"+link);
					map.put("sourceUrl",newsurl);
					list.add(map);					
				}
			}		
		return list;
	}

	/**
	 * @Override
	 * 对返回的html进行解析，返回信息详细内容
	 * @param respHtmlStr
	 * @return
	 * @throws ParserException
	 */
	public	Map<String, String>  parseNews( Map<String, String> map,String respHtmlStr)
			throws ParserException {
		if ("".equals(respHtmlStr)||respHtmlStr.indexOf("Sword 出错信息提示")>0) {
			return null;
		}			
		respHtmlStr = respHtmlStr.replaceAll(
				"<\\s*(span)([^(</span>)]*)\\s*</span>", "");//去除隐藏域		
		Parser parser = Parser.createParser(respHtmlStr, charsetName);		
		// 标题
		AndFilter andFilter = new AndFilter(new TagNameFilter("div"),
				new HasAttributeFilter("class", "tit"));
		NodeList nodeList = parser.extractAllNodesThatMatch(andFilter);
		if (nodeList != null && nodeList.size() > 0) {
		String	title = StringUtils.replaceEach(
					nodeList.elementAt(0).toPlainTextString(),
					HtmlCodeMap.htmlcode, HtmlCodeMap.sign).trim();
		map.put("title", title);
		}
		// 文号 发布时间 或发文日期 是否有效
		parser.reset();
		andFilter = new AndFilter(new TagNameFilter("div"),
				new HasAttributeFilter("class", "info"));
		nodeList = parser.extractAllNodesThatMatch(andFilter);
		if (nodeList != null && nodeList.size() > 0) {
			String rowText = StringUtils.replaceEach(
					nodeList.elementAt(0).toPlainTextString(),
					HtmlCodeMap.htmlcode, HtmlCodeMap.sign).trim();
			int ind = rowText.indexOf("日期");
			String fwzh = rowText.substring(0, ind - 2).trim();	
			map.put("fwzh", fwzh);
			String createtime = rowText.substring(ind + 3, ind + 14).trim();
			String fwrq = createtime.substring(createtime.indexOf("：") + 1,
					createtime.lastIndexOf("月") + 4).replace("年", "-").replace(
					"月", "-").replace("日", "");
			Node node=nodeList.elementAt(0).getChildren().elementAt(1);
			String qwyx = node==null?null:node.toPlainTextString();
			map.put("yxx", qwyx);
			map.put("fbrq", fwrq);
		}

		// 内容
		parser.reset();
		andFilter = new AndFilter(new TagNameFilter("div"),
				new HasAttributeFilter("id", "zoom"));
		nodeList = parser.extractAllNodesThatMatch(andFilter);
		if (nodeList != null && nodeList.size() > 0) {
			String text = nodeList.elementAt(0).toPlainTextString();
			//text=text.replaceAll("&nbsp;", " ");
			String content = StringUtils.replaceEach(text, HtmlCodeMap.htmlcode,
					HtmlCodeMap.sign);
			map.put("content", content);
			//String enclosureUrl = getEnclosures(node.toHtml(), sourceurl);
		}
		return map;
		
	}
	
//	public static void main(String[] args) {		
//		new ApplicationListener().contextInitialized();
//		ZSKGrab grab=new ZSKGrab();
////		List<String> list=grab.getNewsLinkList("http://221.226.40.115:7001/jswwzsk/zrarpages/main/zsk/zlkjs/qwjs_main.jsp?iCurrentPage=1&check=1&page=&pageSize=15&treeVal=&keywords=&sortDate=&sortSfyx=&sortDjl=&qybm=232000000");
////		for(String str:list){
////			System.out.println(str);
////			ContentInfoVo vo=  grab.getNewsContent("zsk_ssfg",str);
////			if(vo!=null)
////			System.out.println(JsonUtils.tojson(vo));
////		}		
//		
//		ContentInfoVo vo=  grab.getNewsContent("zsk_ssfg","http://221.226.40.115:7001/jswwzsk/sword?tid=ZskjsBLH_findFgContent&id=0000000002010111001052&title=&zlnr=&qybm=232000000");
//		
//	}

}
