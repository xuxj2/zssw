package com.zrsf.backup.http.outer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.zrsf.common.util.HtmlCodeMap;


/**
 * 用于法规库栏目信息的网页抓取：包含四个子栏目，基本法规、总局法规、地方法规、优惠政策
 * @author Terry
 *
 */
public class FgkGrab extends NewsGrabBase {
	private String urlTitle = "http://hd.chinatax.gov.cn/guoshui/";	

	/**
	 * 获取新闻列表中各条新闻的url,id，所属栏目
	 * 
	 * @param url
	 *            新闻列表的url
	 * @return 多条新闻的url
	 * @throws ParserException
	 */
	public List<Map<String, String>> parseList(String sourceUrl, String respHtmlStr)
			throws ParserException {
		List<Map<String, String>> list = null;
		if (!"".equals(respHtmlStr)) {
			Parser parser = Parser.createParser(respHtmlStr, charsetName);
			HasAttributeFilter filter = new HasAttributeFilter("class",
					"a_left2");
			NodeList anodeList = parser.extractAllNodesThatMatch(filter);
			if (anodeList != null && anodeList.size() > 0) {
				list = new ArrayList<Map<String, String>>();
				for (int i = 0; i < anodeList.size(); i++) {
					Map<String, String> map=new HashMap<String, String>();
					LinkTag aTag = (LinkTag) anodeList.elementAt(i);
					String link = aTag.getLink();
					//../action/GetArticleView1.do?id=217392&flag=1
					int beginIndex=link.indexOf("?id=")+4;
					int endIndex=link.indexOf("&", beginIndex);
					String idStr=link.substring(beginIndex, endIndex);
					map.put("id", "fgk"+idStr);
					link=StringUtils.replaceEach(
							link ,HtmlCodeMap.htmlcode, HtmlCodeMap.sign).trim();
					map.put("sourceUrl", urlTitle + link.substring(3));	
					
					list.add(map);
				}
			}
		} 
		return list;
	}

	/**
	 * 对返回的html进行解析，返回信息详细内容
	 * 
	 * @param respHtmlStr
	 * @return
	 * @throws ParserException
	 */
	public Map<String, String> parseNews( Map<String, String> map,
			String respHtmlStr) throws ParserException {
		if (StringUtils.isEmpty(respHtmlStr)) {
			return null;
		}		
		Parser parser = Parser.createParser(respHtmlStr, charsetName);
		NodeList anodeList;
		// 提取标题
		HasAttributeFilter titleFilter = new HasAttributeFilter("id", "title");
		anodeList = parser.parse(titleFilter);
		if (anodeList != null && anodeList.size() > 0) {
		String	title = anodeList.elementAt(0).getChildren().elementAt(2)
					.toPlainTextString().trim();
			title=StringUtils.replaceEach(title,
					HtmlCodeMap.htmlcode, HtmlCodeMap.sign).trim();	
			map.put("title", title);
		}
		// 提取文号
		parser.reset();
		HasAttributeFilter whFilter = new HasAttributeFilter("id", "wh");
		anodeList = parser.parse(whFilter);
		if (anodeList != null && anodeList.size() > 0) {
			String	fwzh = anodeList.elementAt(0).toPlainTextString().trim().replaceAll(
					"&nbsp;", "");
			map.put("fwzh", fwzh);
		}
		// 获取全文有效 成文时间
		parser.reset();
		HasAttributeFilter cwrqFilter = new HasAttributeFilter("id", "cwrq");
		anodeList = parser.parse(cwrqFilter);
		if (anodeList != null && anodeList.size() > 0) {
			String yxx = anodeList.elementAt(0).getChildren().elementAt(1)
					.toPlainTextString().trim();
			String fwrq = anodeList.elementAt(0).getChildren().elementAt(3)
					.toPlainTextString().trim().replaceAll("&nbsp;", "");			
			fwrq = fwrq.substring(5).trim();
			map.put("yxx", yxx);
			map.put("fbrq", fwrq);		
		}
		// 获取正文
		parser.reset();
		HasAttributeFilter zwFilter = new HasAttributeFilter("id", "Zoom2");
		anodeList = parser.extractAllNodesThatMatch(zwFilter);
		if (anodeList != null && anodeList.size() > 0) {
			Node node = anodeList.elementAt(0);
			String content = node.toPlainTextString().trim();
			content=StringUtils.replaceEach(content,
					HtmlCodeMap.htmlcode, HtmlCodeMap.sign).trim();	
			map.put("content", content);
			// 获取正文中附件链接
			String enclosureUrl = getEnclosures(node.toHtml(), map.get("sourceUrl"));
			map.put("enclosureUrl", enclosureUrl);
		}
		return map;
	}

	public String getEnclosures(String docHtml, String sourceurl)
			throws ParserException {
		StringBuffer sburl = new StringBuffer();
		if (docHtml.contains("</a") || docHtml.contains("</A")) { // 检查是否含有<a>标签
			// (附件)
			Parser aparser = Parser.createParser(docHtml, "UTF-8");
			OrFilter orFilter = new OrFilter(new TagNameFilter("a"),
					new TagNameFilter("A"));
			NodeList anodelist = aparser.extractAllNodesThatMatch(orFilter);
			for (int j = 0; j < anodelist.size(); j++) {
				LinkTag aTag = (LinkTag) anodelist.elementAt(j);
				String ahtml = aTag.toHtml();
				String atext = aTag.toPlainTextString();
				String ahref = aTag.getLink();
				if (!ahtml.contains("http")) {
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
		return sburl.substring(0, sburl.length() > 0 ? sburl.length() - 6 : 0);
	}

//	public static void main(String[] args) {
//		new ApplicationListener().contextInitialized();
//		FGKGrab grab = new FGKGrab();
//		List<String> list = grab
//				.getNewsLinkList("http://hd.chinatax.gov.cn/guoshui/action/InitNewArticle.do?shuizhong=%E5%9C%B0%E6%96%B9%E6%B3%95%E8%A7%84&articleRole=localtax&articleField08=&articleField09=&articleField10=&articleField11=&articleField12=&articleField13=&articleField14=&articleField18=%E5%90%A6&intvalue=-1&intvalue1=5&initFlag=0&articleField01=&articleField03=&articleField04=&articleField05=&articleField06=&articleField07_s=&articleField07_d=&pageSize=45&cPage=1");
//		for (String str : list) {
//			System.out.println(str);
//			ContentInfoVo vo = grab
//			.getNewsContent(
//					"fgk_dffg",
//					str);
//			if(vo!=null)
//	System.out.println(JsonUtils.tojson(vo));
//		}
//
//	}
}
