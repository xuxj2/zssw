package com.zrsf.backup.http.outer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.zrsf.common.util.HtmlCodeMap;
/**
 * 全省工作动态抓取
 * 
 * @author user
 *         http://www.jsds.gov.cn/module/jslib/jquery/jpage/dataproxy.jsp?startrecord
 *         =46&endrecord=90&perpage=15&col=1&appid=1&webid=1&path=%2F&columnid=
 *         283
 *         &unitid=53231&webname=%E6%B1%9F%E8%8B%8F%E7%9C%81%E5%9C%B0%E6%96%B9
 *         %E7%A8%8E%E5%8A%A1%E5%B1%80&permissiontype=0
 */
public class QsdtGrab extends NewsGrabBase {

	/**
	 * 获取新闻列表中各条新闻的url
	 * 
	 * @param url
	 *            新闻列表的url
	 * @return 多条新闻的url
	 * @throws ParserException
	 * 
	 */
	public List<Map<String, String>> parseList(String url, String respHtmlStr)
			throws ParserException {
		if ("".equals(respHtmlStr)) {
			return null;
		}
		List<Map<String, String>> list = null;
		String urltitle = url.substring(0, url.indexOf("/", 8));
		respHtmlStr = respHtmlStr.substring(respHtmlStr.lastIndexOf("=['") + 3,
				respHtmlStr.lastIndexOf("'];"));
		Parser parser = Parser.createParser(respHtmlStr, charsetName);
		TagNameFilter filter = new TagNameFilter("a");
		NodeList anodeList = parser.extractAllNodesThatMatch(filter);
		if (anodeList != null && anodeList.size() > 0) {
			String href;
			list = new ArrayList<Map<String, String>>();			
			for (int i = 0; i < anodeList.size(); i++) {
				Map<String, String> map=new HashMap<String, String>();
				LinkTag aTag = (LinkTag) anodeList.elementAt(i);
				String link = aTag.getLink();
				// <a  href=\'/art/2013/12/19/art_283_617863.html\'
				href = urltitle + link.substring(2, link.length() - 2);
				String idStr=link.substring(18, link.length() - 7);
				
				map.put("id","qsdt"+idStr);
				href=StringUtils.replaceEach(
						link ,HtmlCodeMap.htmlcode, HtmlCodeMap.sign).trim();
				map.put("sourceUrl",href);
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 对返回的html进行解析，返回信息详细内容
	 */
	public Map<String, String> parseNews(Map<String, String> map, String respHtmlStr)
			throws ParserException {
		if ("".equals(respHtmlStr)) {
			return null;
		}
		Parser parser = Parser.createParser(respHtmlStr, charsetName);
		AndFilter andFilter;
		NodeList nodeList;
		// 标题
		andFilter = new AndFilter(new TagNameFilter("div"),
				new HasAttributeFilter("id", "title"));
		nodeList = parser.extractAllNodesThatMatch(andFilter);

		if (nodeList != null && nodeList.size() > 0) {
			String title = StringUtils.replaceEach(
					nodeList.elementAt(0).toPlainTextString(),
					HtmlCodeMap.htmlcode, HtmlCodeMap.sign).trim();
			map.put("title", title);
		}
		// 发布时间
		parser.reset();
		andFilter = new AndFilter(new TagNameFilter("div"),
				new HasAttributeFilter("id", "pubdate"));
		nodeList = parser.extractAllNodesThatMatch(andFilter);
		if (nodeList != null && nodeList.size() > 0) {
			String createtime = StringUtils.replaceEach(
					nodeList.elementAt(0).toPlainTextString(),
					HtmlCodeMap.htmlcode, HtmlCodeMap.sign).trim(); // 2013-1-1
			String createdOn = createtime
					.substring(createtime.indexOf("：") + 1).replace("年", "-")
					.replace("月", "-").replace("日", "");
			map.put("fbrq", createdOn);
		}
		// 内容
		parser.reset();
		andFilter = new AndFilter(new TagNameFilter("div"),
				new HasAttributeFilter("id", "contentText"));
		nodeList = parser.extractAllNodesThatMatch(andFilter);
		if (nodeList != null && nodeList.size() > 0) {
			String dochtml = nodeList.elementAt(1).toHtml();
			String sourceurl=map.get("sourceUrl");
			String imageUrl = getImages(sourceurl, dochtml);
			String enclosureUrl = getEnclosures(dochtml, sourceurl);
			String res = dochtml.replaceAll("<\\s*((img)|(IMG))([^>]*)\\s*/>",
					"@@");
			parser = Parser.createParser(res, "UTF-8");
			nodeList = parser.extractAllNodesThatMatch(andFilter);
			String text = nodeList.elementAt(0).toPlainTextString();
			text = StringUtils.replaceEach(text, HtmlCodeMap.htmlcode,
					HtmlCodeMap.sign);
			map.put("enclosureUrl", enclosureUrl);
			map.put("links", imageUrl);
			map.put("content", text);
		}

		return map;

	}

	/**
	 * 获取新闻中的超链接url
	 * 
	 * @param docHtml
	 *            html字符串
	 * @param sourceurl
	 *            原始url，便于拼接完整的超链接url
	 * @return
	 * @throws ParserException
	 * 
	 */
	public String getEnclosures(String docHtml, String sourceurl)
			throws ParserException {
		StringBuffer sburl = new StringBuffer();
		if (docHtml.contains("</a")) { // 检查是否含有<a>标签 (附件)
			String a = docHtml.substring(docHtml.indexOf("<a") - 2, docHtml
					.lastIndexOf("</a") + 4);
			Parser aparser = Parser.createParser(a, "UTF-8");
			NodeList anodelist = aparser
					.extractAllNodesThatMatch(new TagNameFilter("a"));
			for (int j = 0; j < anodelist.size(); j++) {
				LinkTag aTag = (LinkTag) anodelist.elementAt(j);
				String ahtml = aTag.toHtml();
				String atext = aTag.toPlainTextString();
				String ahref = aTag.getLink();

				if (!ahtml.contains("http")) {
					// 可考虑下载保存在本地
					ahtml = ahtml.replace("href=\"", "href=\""
							+ sourceurl
									.substring(0, sourceurl.indexOf("/", 10)));

					ahref = sourceurl.substring(0, sourceurl.indexOf("/", 10))
							+ ahref;
				}

				sburl.append(atext);
				sburl.append(":");
				sburl.append(ahref);
				sburl.append("<link>");
			}
		}
		return StringUtils.replaceEach(sburl.substring(0,
				sburl.length() > 0 ? sburl.length() - 6 : 0),
				HtmlCodeMap.htmlcode, HtmlCodeMap.sign);
	}

	public String getImages(String sourceUrl, String docHtml)
			throws ParserException {
		String urltitle = sourceUrl.substring(0, sourceUrl.indexOf("/", 8) + 1);
		StringBuffer sb = new StringBuffer();
		if (docHtml.contains("<img") || docHtml.contains("<IMG")) { // 检查是否含有<img>标签
			Parser aparser = Parser.createParser(docHtml, "UTF-8");
			NodeList imgnodelist = aparser
					.extractAllNodesThatMatch(new TagNameFilter("IMG"));
			for (int j = 0; j < imgnodelist.size(); j++) {
				ImageTag imageTag = (ImageTag) imgnodelist.elementAt(j);
				String imghtml = imageTag.toHtml();
				String imgsrc = imageTag.getImageURL();
				if (!imghtml.contains("http")) {
					sb.append(urltitle + imgsrc);
				}
				sb.append("<link>");
			}
		}
		return StringUtils.replaceEach(sb.substring(0, sb.length() > 0 ? sb
				.length() - 6 : 0), HtmlCodeMap.htmlcode, HtmlCodeMap.sign);
	}

	// public static void main(String[] args) {
	// new ApplicationListener().contextInitialized();
	// NewsGrab grab=new NewsGrab();
	// List<String>
	// list=grab.getNewsLinkList("http://sz.jsds.gov.cn/col/col6256/index.html");
	// for(String str:list){
	// System.out.println(str);
	// ContentInfoVo vo= grab.getNewsContent("sstg", str);
	// if(vo!=null)
	// System.out.println(JsonUtils.tojson(vo));
	// }
	//		 
	//		
	// }

}
