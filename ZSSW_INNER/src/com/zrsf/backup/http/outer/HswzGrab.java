package com.zrsf.backup.http.outer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Bullet;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.zrsf.backup.http.InputStream2String;
import com.zrsf.common.util.HtmlCodeMap;

/**
 * 寒山闻钟解析（需要分 税、发票、社保、二手房 关键字分别抓取）
 * 
 * @author Terry
 *         http://www.12345.suzhou.gov.cn/bbs/search.php?mod=forum&searchid
 *         =2519&orderby=dateline&ascdesc=desc&searchsubmit=yes&kw=%E7%A8%8E
 *         http
 *         ://www.12345.suzhou.gov.cn/bbs/search.php?mod=forum&searchid=1277&
 *         orderby=dateline&ascdesc=desc&searchsubmit=yes&page=2
 */
public class HswzGrab {
	private HttpClient httpclient;
	private String charsetName = "utf-8";
	final String urltitle = "http://www.12345.suzhou.gov.cn/bbs/";
	final String searchUri = "http://www.12345.suzhou.gov.cn/bbs/search.php?mod=forum";
	private int currentPage = 1;// 当前页
	private int totalPage = 1;// 总页数
	private String[] keywords = { "税", "发票", "社保", "二手房" };// 

	private NameValuePair[] nps = { new NameValuePair("srchfrom", "0"),
			new NameValuePair("srchtxt", ""),
			new NameValuePair("srchfid[]", "2"),
			new NameValuePair("searchsubmit", "yes"),
			new NameValuePair("srchuname", ""),
			new NameValuePair("srchfilter", "all"),
			new NameValuePair("formhash", "0bfa943c"),
			new NameValuePair("before", ""),
			new NameValuePair("orderby", "dateline"),
			new NameValuePair("ascdesc", "desc") };

	/**
	 * 抓取指定日期内的数据
	 */
	public List<Map<String, String>> grabDates(long time) {
		List<Map<String, String>> tjList = null;
		try {
			if (httpclient == null) {
				httpclient = getHttpclient();
			}
			tjList = new ArrayList<Map<String, String>>();
			for (String keyword : keywords) {
				List<Map<String, String>> keyList = new ArrayList<Map<String, String>>();
				String listUri = getListUri(time, keyword);
				if (StringUtils.isEmpty(listUri)) {
					continue;
				}
				currentPage = 1;
				while (currentPage <= totalPage) {
					System.out.println(listUri + "&page=" + currentPage);
					String listString = getResponseString(listUri + "&page="
							+ currentPage);
					List<Map<String, String>> pageList = parseList(listString);
					currentPage++;
					keyList.addAll(pageList);
				}
				for (int i = 0; i < keyList.size(); i++) {
					Map<String, String> map = keyList.get(i);
					try {
						grabOne(map);
					} catch (Exception e) {
						//TODO
//						System.out.println(map.get("sourceUrl"));
						continue;
						
					}
				}
				tjList.addAll(keyList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tjList;
	}

	/**
	 * 抓取指定（url）的一条信息的内容
	 * 
	 * @param map
	 * @throws ParserException
	 * @throws IOException
	 */
	private void grabOne(Map<String, String> map) throws ParserException,
			IOException {		
		map.put("sslm", "hswz000");
		String contentString = getResponseString(map.get("sourceUrl"));
		parseNews(map, contentString);
	}

	/**
	 * 获取uri网页返回的html字符串
	 */
	private String getResponseString(String uri) throws IOException {
		String responseString = null;
		GetMethod get = new GetMethod(uri);
		int state = httpclient.executeMethod(get);
		if (state == 200) {
			InputStream in = get.getResponseBodyAsStream();
			responseString = InputStream2String.inputStream2String(in, "UTF-8");
		}
		get.releaseConnection();
		return responseString;
	}

	/**
	 * 获取搜索成功后重定向的uri，搜索结果为空时请求返回的状态码为200
	 */
	private String getListUri(long time, String keyword) throws HttpException,
			IOException {
		String newuri = "";
		PostMethod postMethod = new PostMethod(searchUri);
		this.addParamers(time, keyword);
		postMethod.setRequestBody(nps);
		int sendStatus = httpclient.executeMethod(postMethod);
		httpclient.getState().addCookies(httpclient.getState().getCookies());
		if (sendStatus == HttpStatus.SC_MOVED_TEMPORARILY
				|| sendStatus == HttpStatus.SC_MOVED_PERMANENTLY
				|| sendStatus == HttpStatus.SC_SEE_OTHER
				|| sendStatus == HttpStatus.SC_TEMPORARY_REDIRECT) {
			Header header = postMethod.getResponseHeader("location");
			postMethod.releaseConnection();
			newuri = header.getValue();
			// if (newuri == null || "".equals(newuri)) {
			// newuri = "/";
			// } else if (!newuri.startsWith("http")) {
			newuri = searchUri.substring(0, searchUri.lastIndexOf("/") + 1)
					+ newuri;
		}
		// newuri.replaceAll("&kw=(%[\\dABCDE])*", "");
		return newuri;
	}

	/**
	 * 配置搜索参数
	 * 
	 * @param time
	 * @param keyword
	 */
	private void addParamers(long time, String keyword) {
		nps[0].setValue(String.valueOf(time));
		nps[1].setValue(keyword);
		// nps[0] = new NameValuePair("srchfrom", String.valueOf(time));//
		// 搜索时间--0全部，86400一天，172800两天，604800一周，
		// nps[1] = new NameValuePair("srchtxt", keyword);// 搜索关键字
		// nps[2] = new NameValuePair("srchuname", "");// 作者
		// nps[3] = new NameValuePair("srchfilter", "all");// 主题范围 --精华，置顶，全部
		// nps[4] = new NameValuePair("formhash", "0bfa943c");
		// nps[5] = new NameValuePair("before", "");// ""以内，1以前
		// nps[6] = new NameValuePair("orderby", "dateline");// 排序类型 lastport
		// // 回复时间，dateline
		// // 发布时间
		// nps[7] = new NameValuePair("ascdesc", "desc");// 升序，降序
		// nps[8] = new NameValuePair("srchfid[]", "2");// 搜索范围 2寒山闻钟
		// nps[9] = new NameValuePair("searchsubmit", "yes");

	}

	/**
	 * 列表页解析：获取 标题，id,内容页url，添加所属栏目 当当前页是第一页时，获取总页数
	 * 
	 * @param sourceUrl
	 * @param respHtmlStr
	 * @return
	 * @throws ParserException
	 */
	public List<Map<String, String>> parseList(String respHtmlStr)
			throws ParserException {
		if ("".equals(respHtmlStr)) {
			return null;
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String conStr = respHtmlStr.substring(respHtmlStr
				.indexOf("<div class=\"sttl mbn\">"), respHtmlStr
				.lastIndexOf("<div id=\"ft\""));
		Parser parser = Parser.createParser(conStr, charsetName);
		AndFilter andFilter = new AndFilter(new TagNameFilter("li"),
				new HasAttributeFilter("class", "pbw"));
		NodeList nodeList = parser.extractAllNodesThatMatch(andFilter);
		if (nodeList != null && nodeList.size() > 0) {
			for (int i = 0; i < nodeList.size(); i++) {
				Node node = nodeList.elementAt(i);
				Bullet li = (Bullet) node;
				String id = li.getAttribute("id");
				LinkTag link = (LinkTag) li.childAt(1).getChildren().elementAt(
						1);
				String href = link.getLink();
				href = StringUtils.replaceEach(href, HtmlCodeMap.htmlcode,
						HtmlCodeMap.sign).trim();
				String title = link.getLinkText();
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", "hswz" + id);
				map.put("title", title);
				map.put("sourceUrl", urltitle + href);
				list.add(map);
			}
		}
		if (currentPage == 1) {
			if (respHtmlStr.contains("<div class=\"pgs cl mbm\">")) {// 搜索结果多于1页
				String pageStr = respHtmlStr.substring(respHtmlStr
						.indexOf("<div class=\"pgs cl mbm\">"), respHtmlStr
						.lastIndexOf("class=\"nxt\">"));
				int beginIndex = pageStr.indexOf("<span title=") + 14;
				int endIndex = pageStr.indexOf("页", beginIndex);
				pageStr = pageStr.substring(beginIndex, endIndex).trim();
				totalPage = Integer.valueOf(pageStr);
			} else {// //搜索结果仅1页
				totalPage = 1;
			}
		}
		return list;
	}

	/**
	 * 内容页解析：获取发布时间、内容、图片链接
	 * 
	 * @param map
	 * @param respHtmlStr
	 * @return
	 * @throws ParserException
	 */
	public Map<String, String> parseNews(Map<String, String> map,
			String respHtmlStr) throws ParserException {
		if (StringUtils.isEmpty(respHtmlStr)) {
			return null;
		}
		// System.out.println(respHtmlStr);
		Parser parser = Parser.createParser(respHtmlStr, charsetName);
		NodeList nodeList = parser.extractAllNodesThatMatch(new AndFilter(
				new TagNameFilter("div"),
				new HasAttributeFilter("class", "pti")));
		if (nodeList == null || nodeList.size() < 1) {
			return null;
		}
		String emHtml = nodeList.elementAt(0).toHtml();
		int bg = emHtml.indexOf("发表于");
		int ed = emHtml.indexOf("</em>", bg);
		String fbsj = emHtml.substring(bg, ed);
		if (fbsj.contains("<span title=")) {
			int begin = fbsj.indexOf("title=") + 7;
			int end = fbsj.indexOf(">", begin) - 1;
			fbsj = fbsj.substring(begin, end).trim();
		} else {
			int begin = fbsj.indexOf("发表于") + 4;
			int end = fbsj.indexOf("</em>");
			fbsj = fbsj.substring(begin, end).trim();
		}
		map.put("fbrq", fbsj.substring(0, fbsj.lastIndexOf("-") + 2).trim());
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new AndFilter(
				new TagNameFilter("div"), new HasAttributeFilter("id",
						"JIATHIS_CODE_HTML4")));

		String reshtml = nodeList.elementAt(0).toHtml();
		Parser parser2 = Parser.createParser(reshtml, charsetName);
		NodeList fileNodeList = parser2.extractAllNodesThatMatch(new OrFilter(
				new TagNameFilter("IMG"), new TagNameFilter("img")));
		if (fileNodeList != null && fileNodeList.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < fileNodeList.size(); j++) {
				ImageTag imageTag = (ImageTag) fileNodeList.elementAt(j);
				String uri = imageTag.getAttribute("zoomfile");
				if (uri != null && !"".equals(uri)) {
					sb.append(urltitle + uri + "<link>");
				}
			}

			String imageUrl = StringUtils.replaceEach(sb.substring(0, sb
					.length() > 0 ? sb.length() - 6 : 0), HtmlCodeMap.htmlcode,
					HtmlCodeMap.sign);

			if (!StringUtils.isEmpty(imageUrl)) {
				map.put("ljUrl", imageUrl);

			}
		}
		String dochtml = nodeList.elementAt(0).toHtml();
		String res = dochtml.replaceAll("<\\s*((img)|(IMG))([^>]*)/>", "@@");
//		res = res.substring(0, res.indexOf("</td>", res.indexOf("<td")));
		parser = Parser.createParser(res, charsetName);
//		StringBuffer sb = new StringBuffer();
//		NodeIterator it = parser.elements();
//		while (it.hasMoreNodes()) {
//			sb.append(it.nextNode().toPlainTextString());
//		}		
//		String text = sb.toString();
		nodeList = parser.extractAllNodesThatMatch(new TagNameFilter("table"));
		String text =nodeList.elementAt(0).toPlainTextString();
		text = StringUtils.replaceEach(text, HtmlCodeMap.htmlcode,
				HtmlCodeMap.sign).trim();
		map.put("content", text);
		return map;
	}

	/**
	 * 获取httpclient
	 * 
	 * @return
	 */
	private HttpClient getHttpclient() {
		HttpClient httpclient2 = new HttpClient();
		// // 设置请求时间为5秒
		// httpclient2.getHttpConnectionManager().getParams().setConnectionTimeout(
		// 8000);
		// // 设置读取返回数据时间为5秒
		// httpclient2.getHttpConnectionManager().getParams().setSoTimeout(8000);
		org.apache.commons.httpclient.DefaultHttpMethodRetryHandler retryhandler = new DefaultHttpMethodRetryHandler(
				0, false);
		httpclient2.getHttpConnectionManager().getParams().setParameter(
				HttpMethodParams.RETRY_HANDLER, retryhandler);
		httpclient2.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, charsetName);
		return httpclient2;
	}

}
