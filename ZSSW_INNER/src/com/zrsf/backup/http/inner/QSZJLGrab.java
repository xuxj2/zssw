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
 * 欠税增减率
 * 
 * @author Terry
 * 
 */
public class QSZJLGrab extends ZGSJGrab {
	private final static String FUNCTIONID = "10060012";

	@Override
	public String getReportResponseHtml(String swjgl0, String swjgl1,
			String swjgl2, String jc, String year, String month)
			throws HttpException, IOException {
		NameValuePair[] nps = new NameValuePair[22];
		nps[12] = new NameValuePair("SWJG_l0", swjgl0);
		nps[21] = new NameValuePair("analevel_SWJG_0", jc);
		nps[16] = new NameValuePair("id_mth_y1", year);
		nps[17] = new NameValuePair("id_mth_m1", month);
		nps[3] = new NameValuePair("functionid", FUNCTIONID);
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
		nps[13] = new NameValuePair("SWJG_l1", swjgl1);
		nps[14] = new NameValuePair("SWJG_l2", swjgl2);
		nps[15] = new NameValuePair("SWJG_l3", "");
		nps[18] = new NameValuePair("gridfield_10060012_0_0", "");
		nps[19] = new NameValuePair("chartfield_10060012_0_0", "");
		nps[20] = new NameValuePair("viewstyle_10060012_0_0", "0");
		nps[7] = new NameValuePair("anadimn_10060012_0_0", "SWJG");
		PostMethod postMethod = null;
		String source = null;
		try {
			postMethod = new PostMethod(
					"http://141.16.30.30:10022/engine/daoutput.jsp");
			postMethod.setRequestBody(nps);
			postMethod
					.setRequestHeader("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; Trident/7.0; rv:11.0) like Gecko");
			int st = httpclient.executeMethod(postMethod);

			if (st == 200) {
				InputStream in = postMethod.getResponseBodyAsStream();
				source = InputStream2String.inputStream2String(in, "gb2312");

			}
		} catch (Exception e) {
			source = null;
		}finally{
			postMethod.releaseConnection();
		}
		
		return source;
	}

	@Override
	public List<Map<String, String>> parseOutput(String responseString,
			String swjgl0, String swjgl1, String swjgl2, String jc,
			String year, String month) throws ParserException {
		if (StringUtils.isEmpty(responseString)) {
			return null;
		}
		List<Map<String, String>> tjList = null;

		try {

			int begin = responseString
					.indexOf("<TABLE id=\"dimension_table_body0\"");
			int end = responseString.indexOf("</TABLE>", begin);
			Parser parser = Parser.createParser(responseString.substring(begin,
					end), "GB_2312");
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
					}
					if ("1".equals(jc)) {
						map.put("sjjg", swjgl0);
					} else if ("2".equals(jc)) {
						map.put("sjjg", swjgl1);
					} else if ("3".equals(jc)) {
						map.put("sjjg", swjgl2);
					}
					map.put("nf", year);
					map.put("yf", month);
					map.put("swjg_mc", swjg_mc);
					tjList.add(map);
				}
			}
			begin = responseString
					.indexOf("<TABLE id=\"dimension_table_body\"");
			end = responseString.indexOf("</TABLE>", begin);
			parser = Parser.createParser(responseString.substring(begin, end),
					"GB_2312");
			it = parser.elements();
			while (it.hasMoreNodes()) {
				TableTag table = (TableTag) it.nextNode();
				TableRow[] rows = table.getRows();
				for (int i = 1; i < rows.length; i++) {
					TableColumn[] columns = rows[i].getColumns();
					// 截至上年底欠税余额(万元)
					tjList.get(i - 1).put(
							"zzsndqsye",
							columns[0].toPlainTextString().trim().replaceAll(
									",", ""));
					// 本年累计清理陈欠(万元)
					tjList.get(i - 1).put(
							"bnljqlcq",
							columns[1].toPlainTextString().trim().replaceAll(
									",", ""));
					// // 清欠率
					// tjList.get(i-1).put("qql",
					// columns[2].toPlainTextString().trim().replaceAll("%",
					// ""));
					// 本期新增欠税(万元)
					tjList.get(i - 1).put(
							"bnxzqs",
							columns[3].toPlainTextString().trim().replaceAll(
									",", ""));
					// 期末欠税余额(万元)
					tjList.get(i - 1).put(
							"qmqsye",
							columns[4].toPlainTextString().trim().replaceAll(
									",", ""));
					// 欠税增长率
					tjList.get(i - 1).put(
							"qszzl",
							columns[5].toPlainTextString().trim().replaceAll(
									"[%,]", ""));
				}
			}
		} catch (Exception e) {
			// System.out.println(responseString);
		}
		return tjList;
	}

}
