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
import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.zrsf.backup.http.InputStream2String;
import com.zrsf.common.util.HtmlCodeMap;

/**
 * 局长信箱抓取
 * 
 */
public class JzxxGrab {
	private HttpClient httpclient;
	/*
	 * 待受理件url
	 */
	private final String dsljUri = "http://www.jsds.gov.cn/lm/manage/que_accept.jsp?currpage=";
	private int currentPage = 0;
	private int totalPage = 0;

	/**
	 * 登录局长信箱，获取httpclient
	 */
	private boolean loginJzxx() throws HttpException, IOException {
		String loginUri = "http://www.jsds.gov.cn/lm/login.jsp?action=login";
		final String loginid = "szjzxx";
		final String passwd = "lqt74121";
		httpclient = new HttpClient();
		NameValuePair[] nps = new NameValuePair[8];
		nps[0] = new NameValuePair("loginid", loginid);
		nps[1] = new NameValuePair("passwd", passwd);
		nps[2] = new NameValuePair("ca_Digest", "");
		nps[3] = new NameValuePair("ca_UserID", "");
		nps[4] = new NameValuePair("vc_id", "");
		nps[5] = new NameValuePair("vc_handlemode", "");
		nps[6] = new NameValuePair("vc_handleid", "");
		nps[7] = new NameValuePair("dbstate", "");
		httpclient.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		PostMethod postMethod = new PostMethod(loginUri);
		postMethod.setRequestBody(nps);
		int sendStatus = httpclient.executeMethod(postMethod);
		httpclient.getState().addCookies(httpclient.getState().getCookies());
		if (sendStatus == HttpStatus.SC_MOVED_TEMPORARILY
				|| sendStatus == HttpStatus.SC_MOVED_PERMANENTLY
				|| sendStatus == HttpStatus.SC_SEE_OTHER
				|| sendStatus == HttpStatus.SC_TEMPORARY_REDIRECT) {
			Header header = postMethod.getResponseHeader("location");
			postMethod.releaseConnection();
			String newuri = header.getValue();
			if ("http://www.jsds.gov.cn/lm/main/".equals(newuri)) {
				// // 设置请求时间为5秒
				// httpclient.getHttpConnectionManager().getParams()
				// .setConnectionTimeout(5000);
				// // 设置读取返回数据时间为5秒
				// httpclient.getHttpConnectionManager().getParams().setSoTimeout(
				// 5000);
				org.apache.commons.httpclient.DefaultHttpMethodRetryHandler retryhandler = new DefaultHttpMethodRetryHandler(
						0, false);
				httpclient.getHttpConnectionManager().getParams().setParameter(
						HttpMethodParams.RETRY_HANDLER, retryhandler);
				return true;
			}
		} else {
			postMethod.releaseConnection();
		}
		return false;
	}

	/**
	 * 抓取全部页
	 */
	public List<Map<String, String>> grabAll() {
		currentPage = 1;
		List<Map<String, String>> list = null;
		try {
			list = grabOnePage(currentPage);
			if (totalPage > 1) {
				while (currentPage <= totalPage) {
					list.addAll(grabOnePage(++currentPage));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 抓取指定页
	 */
	public List<Map<String, String>> grabOnePage(int pageNumber)
			throws HttpException, IOException, ParserException {
		if (httpclient == null) {
			if (!loginJzxx()) {
				return null;
			}
		}
		String listString = getResponseString(dsljUri + pageNumber);
		List<Map<String, String>> tjList = parseList(listString);
		for (int i = 0; i < tjList.size(); i++) {//
			Map<String, String> map = tjList.get(i);
			grabOne(map);
		}
		return tjList;
	}

	private void grabOne(Map<String, String> map) throws ParserException,
			IOException {
		// TODO 插入栏目代码
		map.put("sslm", "jzxx000");
		String contentString = getResponseString(map.get("sourceUrl"));
		parseContent(contentString, map);
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
	 * 解析列表页
	 */
	private List<Map<String, String>> parseList(String html)
			throws ParserException {
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		Parser parser = Parser.createParser(html, "UTF-8");
		NodeList nodeList = parser.extractAllNodesThatMatch(new AndFilter(
				new TagNameFilter("A"), new HasAttributeFilter("onclick")));
		List<Map<String, String>> tjList = null;
		if (nodeList != null && nodeList.size() > 0) {
			tjList = new ArrayList<Map<String, String>>();
			for (int i = 0; i < nodeList.size(); i += 2) {
				Node node = nodeList.elementAt(i);
				Map<String, String> map = new HashMap<String, String>();
				String source = node.getText().substring(31, 67);
				String sourceUri = "http://www.jsds.gov.cn/lm/manage/opr_accept.jsp?fn_billstatus=E&vc_id="
						+ source;
				String title = node.toPlainTextString();
				map.put("title", title);
				map.put("sourceUrl", sourceUri);
				map.put("id", "jzxx" + source);
				tjList.add(map);
			}
		}
		if (currentPage == 1) {
			parser.reset();
			nodeList = parser.extractAllNodesThatMatch(new AndFilter(
					new TagNameFilter("form"), new HasAttributeFilter("name",
							"_pageForm_")));
			String pageString = nodeList.elementAt(0).toPlainTextString();
			int beginIndex = pageString.indexOf("共", 15) + 1;
			int endIndex = pageString.indexOf("页", beginIndex);
			totalPage = Integer.parseInt(pageString.substring(beginIndex,
					endIndex));
		}
		return tjList;
	}

	/**
	 * 解析内容页
	 */
	private void parseContent(String html, Map<String, String> map)
			throws ParserException {
		if (StringUtils.isEmpty(html)) {
			return;
		}
		int begin = html.indexOf("<TABLE id=tableId");
		int end = html.indexOf("</TABLE>", begin);
		String resp = html.substring(begin, end);
		Parser parser = Parser.createParser(resp, "UTF-8");
		NodeIterator it = parser.elements();
		Node node = it.nextNode();
		TableTag table = (TableTag) node;
		TableRow[] rows = table.getRows();
		String fbsj = rows[10].getColumns()[1].toPlainTextString().replace(
				"&nbsp;", "").trim();
		String text = rows[1].getColumns()[1].toPlainTextString().replace(
				"&nbsp;", "").trim();
		text = StringUtils.replaceEach(text, HtmlCodeMap.htmlcode,
				HtmlCodeMap.sign).trim();
		map.put("content", text);
		map.put("fbrq", fbsj.substring(0, 10));
	}

}
