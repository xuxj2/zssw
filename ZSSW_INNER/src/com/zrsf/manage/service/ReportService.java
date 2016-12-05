package com.zrsf.manage.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.tags.InputTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.zrsf.backup.dao.ZGSJGrabDao;
import com.zrsf.backup.http.InputStream2String;

public class ReportService {
	private ZGSJGrabDao dao;

	public ZGSJGrabDao getDao() {
		return dao;
	}

	public void setDao(ZGSJGrabDao dao) {
		this.dao = dao;
	}

	public void parseReports(File[] uploads) throws ParserException,
			FileNotFoundException {
		for (File file : uploads) {
			if (file != null) {
				this.parseReport(file);
			}
		}
	}

	public void parseReport(File file) throws ParserException,
			FileNotFoundException {
		InputStream in = new FileInputStream(file);
		String html = InputStream2String.inputStream2String(in, "unicode");
		int end = html.indexOf("id=\"functionid\"");
		if (end > 0) {
			String str = html.substring(0, end);
			int start = str.lastIndexOf("value=") + 7;
			end = str.indexOf("\"", start);
			if (start > 0 && end > start) {
				String functionId = str.substring(start, end);
				if ("10090007".equals(functionId)) {// 税户分析 10090007
					List<Map<String, String>> list = this.parseDjhsReport(html);					
						this.insertReport("com.zrsf.map.zgsjrk.insertDjhs",list);				
				} else if ("10090008".equals(functionId)) {// 准期申报 10090008
					List<Map<String, String>> list = this.parseZqsbReport(html);					
					this.insertReport("com.zrsf.map.zgsjrk.insertZqsbl",list);
				} else if ("10090009".equals(functionId)) {// 准期入库 10090009
					List<Map<String, String>> list = this.parseZqrkReport(html);						
					this.insertReport("com.zrsf.map.zgsjrk.insertZqrkl",list);
					
				} else if ("10060012".equals(functionId)) {// 欠税增减 10060012
					List<Map<String, String>> list = this.parseQszjReport(html);					
					this.insertReport("com.zrsf.map.zgsjrk.insertQszjl",list);
				} else if ("43078".equals(functionId)) {// 任务准期完成率 43078
					List<Map<String, String>> list = this.parseRwwcReport(html);					
					this.insertReport("com.zrsf.map.zgsjrk.insertRwzqwcl",list);
				} else if ("47502".equals(functionId)) {// 逾期申报处罚率 47502
					List<Map<String, String>> list = this.parseYqsbcfReport(html);					
					this.insertReport("com.zrsf.map.zgsjrk.insertYqsbcfl",list);					
				}
				// else if ("43234".equals(functionId)) { // 未按期申报处理率 43234
				// List<Map<String, String>> list = this
				// .parseWaqsbcfReport(html);
				// this.insertReport("com.zrsf.map.zgsjrk.insertWaqsbcfl",
				// list);
				// }

			}
		}
	}

	/**
	 * 未按期申报处理率页面解析
	 * @param html
	 * @return
	 * @throws ParserException
	 */
//	private List<Map<String, String>> parseWaqsbcfReport(String html)
//			throws ParserException {
//		List<Map<String, String>> list = null;
//		String year = null;
//		String month = null;
//		Parser parser = Parser.createParser(html, "unicode");
//		NodeList nodeList = parser
//				.extractAllNodesThatMatch(new HasAttributeFilter("id",
//						"MTH_DM_y1"));
//		if (nodeList != null && nodeList.size() > 0) {
//			InputTag tag = (InputTag) nodeList.elementAt(0);
//			year = tag.getAttribute("value");
//		}
//		parser.reset();
//		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
//				"MTH_DM_m1"));
//		if (nodeList != null && nodeList.size() > 0) {
//			InputTag tag = (InputTag) nodeList.elementAt(0);
//			month = tag.getAttribute("value");
//		}
//		parser.reset();
//		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
//				"dimension_table_body"));
//		if (nodeList != null && nodeList.size() > 0) {
//			TableTag table = (TableTag) nodeList.elementAt(0);
//			TableRow[] rows = table.getRows();
//			if (rows != null && rows.length > 0) {
//				list = new ArrayList<Map<String, String>>();
//				for (int i = 1; i < rows.length; i++) {// 第一行是空行
//					Map<String, String> map = new HashMap<String, String>();
//					TableRow row = rows[i];
//					TableColumn[] columns = row.getColumns();
//					String swjgMc = columns[1].toPlainTextString().trim();
//					String tr = columns[1].childAt(0).getText();
//					int be = tr.indexOf("OLAPFORM.SWJG_DM_l") + 21;
//					int en = tr.indexOf("'", be);
//					String swjgDm = tr.substring(be, en);
//					// 已处理户
//					map.put("yclhs", columns[2].toPlainTextString().trim()
//							.replaceAll(",", ""));
//					// 上上个月未准期申报户
//					map.put("wzqsbhs", columns[3].toPlainTextString().trim()
//							.replaceAll(",", ""));
//					// 未处理户
//					map.put("wclhs", columns[4].toPlainTextString().trim()
//							.replaceAll(",", ""));
//					// 未按期申报处理率
//					map.put("waqsbcll", columns[5].toPlainTextString().trim()
//							.replaceAll("[%,]", ""));
//					map.put("swjgMc", swjgMc);
//					map.put("swjgDm", swjgDm);
//					map.put("year", year);
//					map.put("month", month);
//					list.add(map);
//				}
//			}
//		}
//		return list;
//	}
	
/**
 * 逾期申报处罚率页面解析
 * @param html
 * @return
 * @throws ParserException
 */
	private List<Map<String, String>> parseYqsbcfReport(String html)
			throws ParserException {
		List<Map<String, String>> list = null;
		String year = null;
		String month = null;
		Parser parser = Parser.createParser(html, "unicode");
		NodeList nodeList = parser
				.extractAllNodesThatMatch(new HasAttributeFilter("id",
						"MTH_DM_y1"));
		if (nodeList != null && nodeList.size() > 0) {
			InputTag tag = (InputTag) nodeList.elementAt(0);
			year = tag.getAttribute("value");
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"MTH_DM_m1"));
		if (nodeList != null && nodeList.size() > 0) {
			InputTag tag = (InputTag) nodeList.elementAt(0);
			month = tag.getAttribute("value");
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"dimension_table_body"));
		if (nodeList != null && nodeList.size() > 0) {
			TableTag table = (TableTag) nodeList.elementAt(0);
			TableRow[] rows = table.getRows();
			if (rows != null && rows.length > 0) {
				list = new ArrayList<Map<String, String>>();
				for (int i = 1; i < rows.length; i++) {// 第一行是空行
					Map<String, String> map = new HashMap<String, String>();
					TableRow row = rows[i];
					TableColumn[] columns = row.getColumns();
					String swjgMc = columns[1].toPlainTextString().trim();
					String tr = columns[1].childAt(0).getText();
					int be = tr.indexOf("OLAPFORM.SWJG_DM_l") + 21;
					int en = tr.indexOf("'", be);
					String swjgDm = tr.substring(be, en);
					// 已处罚户数
					map.put("ycfhs", columns[2].toPlainTextString().trim()
							.replaceAll(",", ""));
					// //未处罚户数
					// map.put("wcfhs",
					// columns[3].toPlainTextString().trim().replaceAll(",",
					// ""));
					// 逾期申报应处罚户数
					map.put("yqsbycfhs", columns[4].toPlainTextString().trim()
							.replaceAll(",", ""));
					// 逾期申报处罚率
					map.put("yqsbcfl", columns[5].toPlainTextString().trim()
							.replaceAll("[%,]", ""));
					map.put("swjgMc", swjgMc);
					map.put("swjgDm", swjgDm);
					map.put("year", year);
					map.put("month", month);
					list.add(map);
				}
			}

		}
		return list;
	}

	/**
	 * 任务准期完成率页面解析
	 * @param html
	 * @return
	 * @throws ParserException
	 */
	private List<Map<String, String>> parseRwwcReport(String html)
			throws ParserException {
		List<Map<String, String>> list = null;
		String year = null;
		String month = null;
		Parser parser = Parser.createParser(html, "unicode");
		NodeList nodeList = parser
				.extractAllNodesThatMatch(new HasAttributeFilter("id",
						"MTH_DM_y1"));
		if (nodeList != null && nodeList.size() > 0) {
			InputTag tag = (InputTag) nodeList.elementAt(0);
			year = tag.getAttribute("value");
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"MTH_DM_m1"));
		if (nodeList != null && nodeList.size() > 0) {
			InputTag tag = (InputTag) nodeList.elementAt(0);
			month = tag.getAttribute("value");
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"dimension_table_body"));
		if (nodeList != null && nodeList.size() > 0) {
			TableTag table = (TableTag) nodeList.elementAt(0);
			TableRow[] rows = table.getRows();
			if (rows != null && rows.length > 0) {
				list = new ArrayList<Map<String, String>>();
				for (int i = 1; i < rows.length; i++) {// 第一行是空行
					Map<String, String> map = new HashMap<String, String>();
					TableRow row = rows[i];
					TableColumn[] columns = row.getColumns();
					String swjgMc = columns[1].toPlainTextString().trim();
					String tr = columns[1].childAt(0).getText();
					int be = tr.indexOf("OLAPFORM.SWJG_DM_l") + 21;
					int en = tr.indexOf("'", be);
					String swjgDm = tr.substring(be, en);
					// 及时完成任务数
					map.put("jswcrws", columns[2].toPlainTextString().trim()
							.replaceAll(",", ""));
					// //未及时完成任务数
					// map.put("wjswcrws",
					// columns[3].toPlainTextString().trim().replaceAll(",",
					// ""));
					// 任务总数
					map.put("rwzs", columns[4].toPlainTextString().trim()
							.replaceAll(",", ""));
					// 基础任务完成及时率
					map.put("jcrwwcjsl", columns[5].toPlainTextString().trim()
							.replaceAll("[%,]", ""));
					map.put("swjgMc", swjgMc);
					map.put("swjgDm", swjgDm);
					map.put("year", year);
					map.put("month", month);
					list.add(map);
				}
			}
		}
		return list;
	}

	/**
	 * 欠税增加率页面解析
	 * @param html
	 * @return
	 * @throws ParserException
	 */
	private List<Map<String, String>> parseQszjReport(String html)
			throws ParserException {
		List<Map<String, String>> list = null;
		String year = null;
		String month = null;
		Parser parser = Parser.createParser(html, "unicode");
		NodeList nodeList = parser
				.extractAllNodesThatMatch(new HasAttributeFilter("id",
						"id_mth_y1"));
		if (nodeList != null && nodeList.size() > 0) {
			InputTag tag = (InputTag) nodeList.elementAt(0);
			year = tag.getAttribute("value");
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"id_mth_m1"));
		if (nodeList != null && nodeList.size() > 0) {
			InputTag tag = (InputTag) nodeList.elementAt(0);
			month = tag.getAttribute("value");
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"dimension_table_body0"));
		if (nodeList != null && nodeList.size() > 0) {
			TableTag table = (TableTag) nodeList.elementAt(0);
			TableRow[] rows = table.getRows();
			if (rows != null && rows.length > 0) {
				list = new ArrayList<Map<String, String>>();
				for (int i = 1; i < rows.length; i++) {// 第一行是空行
					TableRow row = rows[i];
					Map<String, String> map = new HashMap<String, String>();
					TableColumn[] columns = row.getColumns();
					String swjgMc = columns[1].toPlainTextString().trim();
					String tr = columns[1].childAt(0).getText();
					int be = tr.indexOf("OLAPFORM.SWJG_l") + 18;
					int en = tr.indexOf("'", be);
					String swjgDm = tr.substring(be, en);
					map.put("year", year);
					map.put("month", month);
					map.put("swjgMc", swjgMc);
					map.put("swjgDm", swjgDm);
					list.add(map);
				}
			}
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"dimension_table_body"));
		if (nodeList != null && nodeList.size() > 0) {
			TableTag table = (TableTag) nodeList.elementAt(0);
			TableRow[] rows = table.getRows();
			for (int i = 1; i < rows.length; i++) {// 第一行是空行
				TableRow row = rows[i];
				TableColumn[] columns = row.getColumns();
				// 截至上年底欠税余额(万元)
				list.get(i - 1).put(
						"zzsndqsye",
						columns[0].toPlainTextString().trim().replaceAll(",",
								""));
				// 本年累计清理陈欠(万元)
				list.get(i - 1).put(
						"bnljqlcq",
						columns[1].toPlainTextString().trim().replaceAll(",",
								""));
				// // 清欠率
				// tjList.get(i-1).put("qql",
				// columns[2].toPlainTextString().trim().replaceAll("%",
				// ""));
				// 本期新增欠税(万元)
				list.get(i - 1).put(
						"bnxzqs",
						columns[3].toPlainTextString().trim().replaceAll(",",
								""));
				// 期末欠税余额(万元)
				list.get(i - 1).put(
						"qmqsye",
						columns[4].toPlainTextString().trim().replaceAll(",",
								""));
				// 欠税增长率
				list.get(i - 1).put(
						"qszzl",
						columns[5].toPlainTextString().trim().replaceAll(
								"[%,]", ""));
			}
		}
		return list;
	}

	/**
	 * 准期入库率页面解析
	 * @param html
	 * @return
	 * @throws ParserException
	 */
	private List<Map<String, String>> parseZqrkReport(String html)
			throws ParserException {
		List<Map<String, String>> list = null;
		String year = null;
		String month = null;
		Parser parser = Parser.createParser(html, "unicode");
		NodeList nodeList = parser
				.extractAllNodesThatMatch(new HasAttributeFilter("id",
						"id_mth_y1"));
		if (nodeList != null && nodeList.size() > 0) {
			InputTag tag = (InputTag) nodeList.elementAt(0);
			year = tag.getAttribute("value");
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"id_mth_m1"));
		if (nodeList != null && nodeList.size() > 0) {
			InputTag tag = (InputTag) nodeList.elementAt(0);
			month = tag.getAttribute("value");
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"dimension_table_body0"));
		if (nodeList != null && nodeList.size() > 0) {
			TableTag table = (TableTag) nodeList.elementAt(0);
			TableRow[] rows = table.getRows();
			if (rows != null && rows.length > 0) {
				list = new ArrayList<Map<String, String>>();
				for (int i = 1; i < rows.length; i++) {// 第一行是空行
					TableRow row = rows[i];
					Map<String, String> map = new HashMap<String, String>();
					TableColumn[] columns = row.getColumns();
					String swjgMc = columns[1].toPlainTextString().trim();
					String tr = columns[1].childAt(0).getText();
					int be = tr.indexOf("OLAPFORM.SWJG_l") + 18;
					int en = tr.indexOf("'", be);
					String swjgDm = tr.substring(be, en);
					map.put("year", year);
					map.put("month", month);
					map.put("swjgMc", swjgMc);
					map.put("swjgDm", swjgDm);
					list.add(map);
				}
			}
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"dimension_table_body"));
		if (nodeList != null && nodeList.size() > 0) {
			TableTag table = (TableTag) nodeList.elementAt(0);
			TableRow[] rows = table.getRows();
			for (int i = 1; i < rows.length; i++) {// 第一行是空行
				TableRow row = rows[i];
				TableColumn[] columns = row.getColumns();
				// 应入库户数
				list.get(i - 1).put(
						"yrkhs",
						columns[0].toPlainTextString().trim().replaceAll(",",
								""));
				// 准期入库户数
				list.get(i - 1).put(
						"zqrkhs",
						columns[1].toPlainTextString().trim().replaceAll(",",
								""));
				// 准期入库率（按户数）
				list.get(i - 1).put(
						"zhrkl",
						columns[2].toPlainTextString().trim().replaceAll("%",
								""));
				// 应入库金额
				list.get(i - 1).put(
						"yrkje",
						columns[3].toPlainTextString().trim().replaceAll(",",
								""));
				// 已入库金额
				list.get(i - 1).put(
						"yirkje",
						columns[4].toPlainTextString().trim().replaceAll(",",
								""));
				// // 组合入库税款
				// tjList.get(i - 1).put("zhrksk",
				// columns[5].toPlainTextString());
				// 入库率（按金额）
				list.get(i - 1).put(
						"rkl",
						columns[6].toPlainTextString().trim().replaceAll(
								"[%,]", ""));
				// // 组合入库率（按金额）
				// tjList.get(i - 1).put("zhrkl",
				// columns[7].toPlainTextString());
				// // 未准期入库户
				// tjList.get(i - 1).put("wzqrkh",
				// columns[8].toPlainTextString());
				// // 未准期未处理户
				list.get(i - 1).put("wzqwclh", columns[9].toPlainTextString());
			}
		}
		return list;
	}
/**
 * 准期申报率页面解析
 * @param html
 * @return
 * @throws ParserException
 */
	private List<Map<String, String>> parseZqsbReport(String html)
			throws ParserException {
		List<Map<String, String>> list = null;
		String year = null;
		String month = null;
		Parser parser = Parser.createParser(html, "unicode");
		NodeList nodeList = parser
				.extractAllNodesThatMatch(new HasAttributeFilter("id",
						"id_mth_y1"));
		if (nodeList != null && nodeList.size() > 0) {
			InputTag tag = (InputTag) nodeList.elementAt(0);
			year = tag.getAttribute("value");
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"id_mth_m1"));
		if (nodeList != null && nodeList.size() > 0) {
			InputTag tag = (InputTag) nodeList.elementAt(0);
			month = tag.getAttribute("value");
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"dimension_table_body0"));
		if (nodeList != null && nodeList.size() > 0) {
			TableTag table = (TableTag) nodeList.elementAt(0);
			TableRow[] rows = table.getRows();
			if (rows != null && rows.length > 0) {
				list = new ArrayList<Map<String, String>>();
				for (int i = 1; i < rows.length; i++) {// 第一行是空行
					TableRow row = rows[i];
					Map<String, String> map = new HashMap<String, String>();
					TableColumn[] columns = row.getColumns();
					String swjgMc = columns[1].toPlainTextString().trim();
					String tr = columns[1].childAt(0).getText();
					int be = tr.indexOf("OLAPFORM.SWJG_l") + 18;
					int en = tr.indexOf("'", be);
					String swjgDm = tr.substring(be, en);
					map.put("year", year);
					map.put("month", month);
					map.put("swjgMc", swjgMc);
					map.put("swjgDm", swjgDm);
					list.add(map);
				}
			}
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"dimension_table_body"));
		if (nodeList != null && nodeList.size() > 0) {
			TableTag table = (TableTag) nodeList.elementAt(0);
			TableRow[] rows = table.getRows();
			for (int i = 1; i < rows.length; i++) {// 第一行是空行
				TableRow row = rows[i];
				TableColumn[] columns = row.getColumns();

				// 应申报户数
				list.get(i - 1).put(
						"ysbhs",
						columns[0].toPlainTextString().trim().replaceAll(",",
								""));
				// 准期申报户数
				list.get(i - 1).put(
						"zqsbhs",
						columns[1].toPlainTextString().trim().replaceAll(",",
								""));
				// 组合申报户数
				list.get(i - 1).put(
						"zhsbhs",
						columns[2].toPlainTextString().trim().replaceAll(",",
								""));
				// 逾期申报户数
				list.get(i - 1).put(
						"yqsbhs",
						columns[3].toPlainTextString().trim().replaceAll(",",
								""));
				// // 零申报户数
				// tjList.get(i - 1).put("lsbhs",
				// columns[4].toPlainTextString().trim().replaceAll(",", ""));
				// 准期申报率
				list.get(i - 1).put(
						"zqsbv",
						columns[5].toPlainTextString().trim().replaceAll(
								"[%,]", ""));
				// 组合申报率
				list.get(i - 1).put(
						"zhsbv",
						columns[6].toPlainTextString().trim().replaceAll(
								"[%,]", ""));
				// // 零申报率
				// tjList.get(i - 1).put("lsbv",
				// columns[7].toPlainTextString().trim().replaceAll("%", ""));
				// 逾期申报率
				list.get(i - 1).put(
						"yqsbv",
						columns[8].toPlainTextString().trim().replaceAll("%",
								""));
			}
		}
		return list;
	}
/**
 * 税户情况分析--登记户数页面解析
 * @param html
 * @return
 * @throws ParserException
 */
	private List<Map<String, String>> parseDjhsReport(String html)
			throws ParserException {
		List<Map<String, String>> list = null;
		String year = null;
		String month = null;
		Parser parser = Parser.createParser(html, "unicode");
		NodeList nodeList = parser
				.extractAllNodesThatMatch(new HasAttributeFilter("id",
						"id_mth_y1"));
		if (nodeList != null && nodeList.size() > 0) {
			InputTag tag = (InputTag) nodeList.elementAt(0);
			year = tag.getAttribute("value");
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"id_mth_m1"));
		if (nodeList != null && nodeList.size() > 0) {
			InputTag tag = (InputTag) nodeList.elementAt(0);
			month = tag.getAttribute("value");
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"dimension_table_body0"));
		if (nodeList != null && nodeList.size() > 0) {
			TableTag table = (TableTag) nodeList.elementAt(0);
			TableRow[] rows = table.getRows();
			if (rows != null && rows.length > 0) {
				list = new ArrayList<Map<String, String>>();
				for (int i = 1; i < rows.length; i++) {// 第一行是空行
					TableRow row = rows[i];
					Map<String, String> map = new HashMap<String, String>();
					TableColumn[] columns = row.getColumns();
					String swjgMc = columns[1].toPlainTextString().trim();
					String tr = columns[1].childAt(0).getText();
					int be = tr.indexOf("OLAPFORM.SWJG_l") + 18;
					int en = tr.indexOf("'", be);
					String swjgDm = tr.substring(be, en);
					map.put("year", year);
					map.put("month", month);
					map.put("swjgMc", swjgMc);
					map.put("swjgDm", swjgDm);
					list.add(map);
				}
			}
		}
		parser.reset();
		nodeList = parser.extractAllNodesThatMatch(new HasAttributeFilter("id",
				"dimension_table_body"));
		if (nodeList != null && nodeList.size() > 0) {
			TableTag table = (TableTag) nodeList.elementAt(0);
			TableRow[] rows = table.getRows();
			for (int i = 1; i < rows.length; i++) {// 第一行是空行
				TableRow row = rows[i];
				TableColumn[] columns = row.getColumns();

				String qyhs = columns[0].toPlainTextString().trim().replaceAll(
						",", "");
				String gths = columns[1].toPlainTextString().trim().replaceAll(
						",", "");
				String zchs = columns[2].toPlainTextString().trim().replaceAll(
						",", "");
				String fzchs = columns[3].toPlainTextString().trim()
						.replaceAll(",", "");
				String tyhs = columns[4].toPlainTextString().trim().replaceAll(
						",", "");
				list.get(i - 1).put("qyhs", qyhs);
				list.get(i - 1).put("gths", gths);
				list.get(i - 1).put("zchs", zchs);
				list.get(i - 1).put("fzchs", fzchs);
				list.get(i - 1).put("tyhs", tyhs);
			
			}
		}
		return list;
	}

	private int deleteReport(String sql, Map<String, Object> map) {
		return dao.removeReport(sql, map);

	}

	/**
	 * 将一组报表数据插入数据库
	 * @param sql
	 * @param list
	 */
	private void insertReport(String sql, List<Map<String, String>> list) {
		if (list != null && list.size() > 0) {
			dao.addReport(sql, list);
		}
	}

	/**
	 * 查询指定报表某个月份的数据
	 * @param reportId
	 * @param year
	 * @param month
	 * @return
	 */
	public List<?> selectReport(String reportId, int year, int month) {
		String sql = null;
		if (reportId.equals("B04001")) {
			sql = "com.zrsf.map.bbcx.selectDjhs";
		}
		 else if(reportId.equals("B04002")){
		 sql="com.zrsf.map.bbcx.selectDjhsbd";
		 }
		else if (reportId.equals("B04003")) {
			sql = "com.zrsf.map.bbcx.selectZqsbl";
		} else if (reportId.equals("B04004")) {
			sql = "com.zrsf.map.bbcx.selectZqrkl";
		} else if (reportId.equals("B04005")) {
			sql = "com.zrsf.map.bbcx.selectQszjl";
		} else if (reportId.equals("B04006")) {
			sql = "com.zrsf.map.bbcx.selectYqsbcfl";
		} else if (reportId.equals("B04007")) {
			sql = "com.zrsf.map.bbcx.selectRwzqwcl";
		} else {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nf", year);
		map.put("yf", month);
		map.put("swjg", "23205%");
		List<?> list = dao.selectTaxCollectionReport(sql, map);
		return list;
	}

	/**
	 * 删除指定报表
	 * @param reportId
	 * @param year
	 * @param month
	 */
public int removeReport(String reportId, int year, int month){
	String sql = null;
	if (reportId.equals("B04001")) {
		sql = "com.zrsf.map.zgsjrk.deleteDjhs";
	}
	 else if(reportId.equals("B04002")){
		 return 1;
	 }
	else if (reportId.equals("B04003")) {
		sql = "com.zrsf.map.zgsjrk.deleteZqsbl";
	} else if (reportId.equals("B04004")) {
		sql = "com.zrsf.map.zgsjrk.deleteZqrkl";
	} else if (reportId.equals("B04005")) {
		sql = "com.zrsf.map.zgsjrk.deleteQszjl";
	} else if (reportId.equals("B04006")) {
		sql = "com.zrsf.map.zgsjrk.deleteYqsbcfl";
	} else if (reportId.equals("B04007")) {
		sql = "com.zrsf.map.zgsjrk.deleteRwzqwcl";
	} else {
		return 0;
	}
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("year", year);
	map.put("month", month);
	return this.deleteReport(sql,
			map);
}


}
