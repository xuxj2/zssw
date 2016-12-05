package com.zrsf.backup.http.inner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Parser;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.ParserException;

import com.zrsf.backup.http.InputStream2String;

/**
 * 准期申报率抓取 00094186 准期申报率 基础管理类 2340 10090008
 * 
 * @author Terry
 * 
 */
public class ZQSBLGrab extends ZGSJGrab {
	private final String functionid = "10090008";

	/**
	 * 获取网页daoutput.jsp返回的html
	 */
	public String getReportResponseHtml(String swjgl0, String swjgl1,
			String swjgl2, String jc, String year, String month)
			throws HttpException, IOException {
		NameValuePair[] nps = new NameValuePair[22];
		nps[12] = new NameValuePair("SWJG_l0", swjgl0);
		nps[13] = new NameValuePair("SWJG_l1", swjgl1);
		nps[14] = new NameValuePair("SWJG_l2", swjgl2);
		nps[15] = new NameValuePair("SWJG_l3", "");
		nps[21] = new NameValuePair("analevel_SWJG_0", jc);
		nps[16] = new NameValuePair("id_mth_y1", year);
		nps[17] = new NameValuePair("id_mth_m1", month);
		nps[3] = new NameValuePair("functionid", functionid);
		nps[0] = new NameValuePair("useraction", "1");
		nps[1] = new NameValuePair("gxsjHidden", "");
		nps[2] = new NameValuePair("oldInfo", "false");
		nps[4] = new NameValuePair("targetid", "");
		nps[5] = new NameValuePair("methodid", "0");
		nps[6] = new NameValuePair("entitybatch", "");
		nps[8] = new NameValuePair("entitykey", "");
		nps[9] = new NameValuePair("publish", "false");
		nps[10] = new NameValuePair("mainwindow", "true");
		nps[11] = new NameValuePair("moneyunit", "0");
		nps[18] = new NameValuePair("gridfield_10090008_0_0", "");
		nps[19] = new NameValuePair("chartfield_10090008_0_0", "");
		nps[20] = new NameValuePair("viewstyle_10090008_0_0", "0");
		nps[7] = new NameValuePair("anadimn_10090008_0_0", "SWJG");

		String source = null;
		PostMethod postMethod = null;
		try {
			postMethod = new PostMethod(
					"http://141.16.30.30:10022/engine/daoutput.jsp");
			postMethod.setRequestBody(nps);
			postMethod
					.setRequestHeader("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; Trident/7.0; rv:11.0) like Gecko");
			postMethod.setRequestHeader("Connection", "Keep-Alive");
			int st = 0;
			st = httpclient.executeMethod(postMethod);
			if (st == 200) {
				InputStream in = postMethod.getResponseBodyAsStream();
				source = InputStream2String.inputStream2String(in, "gb2312");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}

		return source;
	}

	/**
	 * 准期申报率网页 解析
	 * 
	 */
	public List<Map<String, String>> parseOutput(String responseString,
			String swjgl0, String swjgl1, String swjgl2, String jc,
			String year, String month) throws ParserException {
		if (StringUtils.isEmpty("responseString")) {
			return null;
		}
		List<Map<String, String>> tjList = null;
		int begin = responseString
				.indexOf("<TABLE id=\"dimension_table_body0\"");
		int end = responseString.indexOf("</TABLE>", begin);
		Parser parser = Parser.createParser(responseString
				.substring(begin, end), "GB_2312");
		NodeIterator it = parser.elements();
		while (it.hasMoreNodes()) {
			TableTag table = (TableTag) it.nextNode();
			tjList = new ArrayList<Map<String, String>>();
			TableRow[] rows = table.getRows();
			for (int i = 1; i < rows.length; i++) {
				TableColumn[] columns = rows[i].getColumns();
				Map<String, String> map = new HashMap<String, String>();
				String swjg_mc = columns[1].toPlainTextString();
				if (columns[1].childAt(0).getText().contains("OLAPFORM")) {
					String dmString = columns[1].childAt(0).getText();
					begin = dmString.indexOf("OLAPFORM") + 18;
					end = dmString.indexOf("document", begin) - 2;
					dmString = dmString.substring(begin, end);
					map.put("bjjg", dmString);
				} else {
					map.put("bjjg", null);
				}
				if ("1".equals(jc)) {
					map.put("sjjg", swjgl0);
				} else if ("2".equals(jc)) {
					map.put("sjjg", swjgl1);
				} else if ("3".equals(jc)) {
					map.put("sjjg", swjgl2);
				} else {
					map.put("sjjg", null);
				}
				map.put("nf", year);
				map.put("yf", month);
				map.put("swjg_mc", swjg_mc);
				tjList.add(map);
			}
		}
		begin = responseString.indexOf("<TABLE id=\"dimension_table_body\"");
		end = responseString.indexOf("</TABLE>", begin);
		parser = Parser.createParser(responseString.substring(begin, end),
				"GB_2312");
		it = parser.elements();
		while (it.hasMoreNodes()) {
			TableTag table = (TableTag) it.nextNode();
			TableRow[] rows = table.getRows();
			for (int i = 1; i < rows.length; i++) {
				TableColumn[] columns = rows[i].getColumns();
				// 应申报户数
				tjList.get(i - 1).put(
						"ysbhs",
						columns[0].toPlainTextString().trim().replaceAll(",",
								""));
				// 准期申报户数
				tjList.get(i - 1).put(
						"zqsbhs",
						columns[1].toPlainTextString().trim().replaceAll(",",
								""));
				// 组合申报户数
				tjList.get(i - 1).put(
						"zhsbhs",
						columns[2].toPlainTextString().trim().replaceAll(",",
								""));
				 // 逾期申报户数
				 tjList.get(i - 1).put("yqsbhs",
				 columns[3].toPlainTextString().trim().replaceAll(",", ""));
				// // 零申报户数
				// tjList.get(i - 1).put("lsbhs",
				// columns[4].toPlainTextString().trim().replaceAll(",", ""));
				// 准期申报率
				tjList.get(i - 1).put(
						"zqsbv",
						columns[5].toPlainTextString().trim().replaceAll(
								"[%,]", ""));
				// 组合申报率
				tjList.get(i - 1).put(
						"zhsbv",
						columns[6].toPlainTextString().trim().replaceAll(
								"[%,]", ""));
				// // 零申报率
				// tjList.get(i - 1).put("lsbv",
				// columns[7].toPlainTextString().trim().replaceAll("%", ""));
				 // 逾期申报率
				 tjList.get(i - 1).put("yqsbv",
				 columns[8].toPlainTextString().trim().replaceAll("%", ""));
				// for (int j = 0; j < columns.length; j++) {
				// tjList.get(i).put("column" + j,
				// columns[j].toPlainTextString());
				// }
			}
		}
		return tjList;
	}

	//
	// /**
	// * 准期申报率网页解析
	// *
	// * @throws ParserException
	// */
	// public List<Map<String, String>> parseZqsbv(String responseString)
	// throws ParserException {
	// List<Map<String, String>> tjList = new ArrayList<Map<String, String>>();
	// Parser parser = Parser.createParser(responseString, "GB_2312");
	// // <TR height="24" class="default_report_tr_data">
	// NodeList nodeList = parser.extractAllNodesThatMatch(new AndFilter(
	// new TagNameFilter("TR"), new HasAttributeFilter("class",
	// "default_report_tr_data")));
	// for (int i = 1; i < nodeList.size() - 1; i++) {
	// Node node = nodeList.elementAt(i);
	// TableRow row = (TableRow) node;
	// TableColumn[] columns = row.getColumns();
	// Map<String, String> map = new HashMap<String, String>();
	// // 税务机关
	// map.put("swjg_dm", columns[0].toPlainTextString());
	// // 应申报户数
	// map.put("yshhs", columns[1].toPlainTextString());
	// // 准期申报户数
	// map.put("zqsbhs", columns[2].toPlainTextString());
	// // 组合申报户数
	// map.put("zhsbhs", columns[3].toPlainTextString());
	// // 逾期申报户数
	// map.put("yqsbhs", columns[4].toPlainTextString());
	// // 零申报户数
	// map.put("lsbhs", columns[5].toPlainTextString());
	// // 准期申报率
	// map.put("zqsbv", columns[6].toPlainTextString());
	// // 组合申报率
	// map.put("zhsbv", columns[7].toPlainTextString());
	// // 零申报率
	// map.put("lsbv", columns[8].toPlainTextString());
	// // 逾期申报率
	// map.put("yqsbv", columns[9].toPlainTextString());
	// tjList.add(map);
	// }
	// return tjList;
	// }

	// public List<Map<String, String>> parseOutput(String responseString)
	// throws ParserException {
	// List<Map<String, String>> tjList = null;
	// int begin = responseString
	// .indexOf("<TABLE id=\"dimension_table_body\"");
	// int end = responseString.indexOf("</TABLE>", begin);
	// Parser parser = Parser.createParser(responseString
	// .substring(begin, end), "GB_2312");
	// NodeIterator it = parser.elements();
	// while (it.hasMoreNodes()) {
	// TableTag table = (TableTag) it.nextNode();
	// tjList = new ArrayList<Map<String, String>>();
	// TableRow[] rows = table.getRows();
	// for (int i = 1; i < rows.length; i++) {
	// TableColumn[] columns = rows[i].getColumns();
	// Map<String, String> map = new HashMap<String, String>();
	// for (int j = 0; j < columns.length; j++) {
	// map.put("column" + j, columns[j].toPlainTextString());
	// }
	// tjList.add(map);
	// }
	// }
	// return tjList;
	// }

}
