package com.zrsf.backup.http.inner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.util.ParserException;

import com.zrsf.backup.http.InputStream2String;

/**
 * 征管数据抓取
 * 
 * @author Terry
 * 
 */
public abstract class ZGSJGrab {
	private static final Log LOG = LogFactory.getLog(ZGSJGrab.class);
	protected HttpClient httpclient;

	/**
	 * 抓取当月数据
	 * 
	 * @return
	 * @throws HttpException
	 * @throws ParserException
	 * @throws IOException
	 */
	public List<Map<String, String>> grabCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		return grabOneMonth(year, month);
	}

	/**
	 * 抓取指定月份数据
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 * @throws ParserException
	 */
	public List<Map<String, String>> grabOneMonth(String year, String month) {
		if (httpclient == null) {
			boolean logon = loginDjzxt();
			if (!logon) {
				return null;
			}
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<Map<String, String>> list1 = grabYzsj("23205", "", "", "1", year,
				month);
		list.addAll(list1);
		if (list1 != null && list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				try {
					Map<String, String> map = list1.get(i);
					// 钻取第二级
					String swjgl1 = map.get("bjjg");
					if (swjgl1 != null) {
						List<Map<String, String>> list2 = grabYzsj("23205",
								swjgl1, "", "2", year, month);
						list.addAll(list2);
						if (list2 != null && list2.size() > 0) {
							for (int k = 0; k < list2.size(); k++) {
								try {
									map = list2.get(k);
									// 钻取第三级
									String swjgl2 = map.get("bjjg");
									if (swjgl2 != null) {
										List<Map<String, String>> list3 = grabYzsj(
												"23205", swjgl1, swjgl2, "3",
												year, month);
										list.addAll(list3);
									}
								} catch (Exception e) {
									LOG.error("钻取第三级"+list2.get(k).get("bjjg")+"出错"+e);
									continue;
								}
							}
						}
					}
				} catch (Exception e) {
					LOG.error("");
					continue;
				}
			}
		}
		return list;
	}

	private List<Map<String, String>> grabYzsj(String swjgl0, String swjgl1,
			String swjgl2, String cj, String year, String month) {
		List<Map<String, String>> list = null;
		try {
			String html = getReportResponseHtml(swjgl0, swjgl1, swjgl2, cj,
					year, month);
			list = parseOutput(html, swjgl0, swjgl1, swjgl2, cj, year, month);
		} catch (Exception e) {
			LOG.error("获取" + swjgl0 + "下级机关" + year + "-" + month
					+ "数据出错"+e);			
		}
		return list;
	}

	public abstract String getReportResponseHtml(String swjgl0, String swjgl1,
			String swjgl2, String jc, String year, String month)
			throws HttpException, IOException;

	public abstract List<Map<String, String>> parseOutput(
			String responseString, String swjgl0, String swjgl1, String swjgl2,
			String jc, String year, String month) throws ParserException;

	/**
	 * 登陆大集中系统
	 * http://141.16.30.30/loginAction.do?operationType=chooseOrg&userCode
	 * =32051000481&password =888888
	 */

	public boolean loginDjzxt() {
		final String userCode = "32051000481";
		final String password = "888888";
		final String loginUrl = "http://141.16.30.30/loginAction.do?operationType=chooseOrg";
		String source = null;
		InputStream in = null;
		String valiCode=null;
		httpclient = new HttpClient();
		
		GetMethod get=null;
		try {
			get=new GetMethod("http://141.16.30.30:80/image.jsp");
			httpclient.executeMethod(get);
			httpclient.getState().addCookies(httpclient.getState().getCookies());
			in = get.getResponseBodyAsStream();
			valiCode=ImgIdentifier.identify(in);		
			System.out.println("==============viliCode:"+valiCode);
		} catch (Exception e) {
			//LOG.error("请求验证码出错"+e);
			e.printStackTrace();
			httpclient = null;
			return false;
		}finally{
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LOG.error("关闭流出错"+e);
				}
			}
			get.releaseConnection();
		}		
		NameValuePair[] nps = new NameValuePair[3];
		nps[0] = new NameValuePair("BGGTEB", userCode);
		nps[1] = new NameValuePair("IJDJL", password);
		nps[2] = new NameValuePair("SWR", valiCode);
		
		httpclient.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		PostMethod postMethod = new PostMethod(loginUrl);
		postMethod.setRequestBody(nps);
		try {
			httpclient.executeMethod(postMethod);
			httpclient.getState()
					.addCookies(httpclient.getState().getCookies());
			in = postMethod.getResponseBodyAsStream();
			source = InputStream2String.inputStream2String(in, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("验证密码验证码出错"+e);
			httpclient = null;
			return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LOG.error("关闭流出错"+e);
				}
			}
			postMethod.releaseConnection();
		}

		JSONObject node = JSONObject.fromObject(source).getJSONObject(
				"responseHeader");
		if (1 == node.getInt("responseCode")) {
			GetMethod getMethod = new GetMethod(
					"http://141.16.30.30/loginAction.do?operationType=login&userCode=32051000481&orgCode=23205000800");
			try {
				httpclient.executeMethod(getMethod);
				in = getMethod.getResponseBodyAsStream();
				httpclient.getState().addCookies(
						httpclient.getState().getCookies());
				source = InputStream2String.inputStream2String(in, "utf-8");
			} catch (Exception e) {
				LOG.error("获取cookie出错"+e);
				httpclient = null;
				return false;
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				getMethod.releaseConnection();
			}
			if (!source.contains("<title>江苏地税</title>")) {
				// TODO记写登陆错误日志
				httpclient = null;
				return false;
			}			
		} else {
			//source = node.getString("responseMessage");
			httpclient = null;
			return false;
		}
		GetMethod getMethod = new GetMethod(
				"http://141.16.30.30/tiles/factory/fraMain.jsp");
		try {
			httpclient.executeMethod(getMethod);
			httpclient.getState().addCookies(httpclient.getState().getCookies());
			getMethod.releaseConnection();
			getMethod = new GetMethod(
					"http://141.16.30.30/UserOperationServlet?funcCode=20303010002&funcName=%E7%A8%8E%E6%BA%90%E4%B8%8E%E5%BE%81%E7%AE%A1%E7%8A%B6%E5%86%B5%E7%9B%91%E6%8E%A7%E5%88%86%E6%9E%90&forwardUrl=@zgjk_main@/taxQuality/index.jsp");
			httpclient.executeMethod(getMethod);
			httpclient.getState().addCookies(httpclient.getState().getCookies());
		} catch (Exception e) {
			httpclient = null;
			return false;
		}finally{
			getMethod.releaseConnection();
		}
		DefaultHttpMethodRetryHandler retryhandler = new DefaultHttpMethodRetryHandler(
				0, false);
		httpclient.getHttpConnectionManager().getParams().setParameter(
				HttpMethodParams.RETRY_HANDLER, retryhandler);

		return true;
	}

}
