package com.zrsf.backup.http.outer;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zrsf.backup.http.InputStream2String;

public class HttpClient {
	private static final Log LOG = LogFactory.getLog(HttpClient.class);
	public static final String GET = "GET";
	public static final String POST = "POST";
	private org.apache.commons.httpclient.HttpClient httpclient = new org.apache.commons.httpclient.HttpClient();
	private HttpMethod httpMethod = null;
	private Map<String, String> params;

	private int timeOut = 8000; // 获取连接超时时间限制
	private int soTimeOut = 8000; // 返回数据超时时间限制
	private String ProxyIP; // 代理ip
	private String port; // 代理端口号
	private String charsetName = "UTF-8"; // 网页字符编码
	private int retryTimes = 0; // 重连次数

	public HttpClient() {
		super();
	}

	public HttpClient(String ProxyIP, String port) {
		this.ProxyIP = ProxyIP;
		this.port = port;
	}

	public HttpClient(String ProxyIP, String port, String charsetName) {
		this.ProxyIP = ProxyIP;
		this.port = port;
		this.charsetName = charsetName;
	}

	public HttpClient(String ProxyIP, String port, int timeOut, int soTimeOut,
			int retryTimes, String charsetName) {
		this.ProxyIP = ProxyIP;
		this.port = port;
		this.charsetName = charsetName;
		this.timeOut = timeOut;
		this.soTimeOut = soTimeOut;
		this.retryTimes = retryTimes;
	}

	/**
	 * 添加Cookies
	 */
	public void addCookies() {
		httpclient.getState().addCookies(httpclient.getState().getCookies());
	}

	/**
	 * 
	 * @param url
	 * @param method
	 * @return
	 */
	public String getHtmlSource(String url, String method) {
		String source = "";
		url = url.trim();
		// org.apache.commons.httpclient.HttpClient httpclient = new
		// org.apache.commons.httpclient.HttpClient();
//		// 设置请求时间为5秒
//		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(
//				timeOut);
//		// 设置读取返回数据时间为5秒
//		httpclient.getHttpConnectionManager().getParams().setSoTimeout(
//				soTimeOut);
		org.apache.commons.httpclient.DefaultHttpMethodRetryHandler retryhandler = new DefaultHttpMethodRetryHandler(
				retryTimes, false);
		httpclient.getHttpConnectionManager().getParams().setParameter(
				HttpMethodParams.RETRY_HANDLER, retryhandler);

		if (StringUtils.isNotEmpty(ProxyIP) && StringUtils.isNotEmpty(port)) {
			try {
				// 设置代理服务器的ip地址和端口
				httpclient.getHostConfiguration().setProxy(ProxyIP,
						Integer.parseInt(port));
			} catch (Exception e) {
				LOG.error("设置代理时出错", e);
			}
			// 使用抢先认证
			httpclient.getParams().setAuthenticationPreemptive(true);
		}
		// 设置编码
		httpclient.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, charsetName);

		int sendStatus = 0;
		try {
			if (POST.equalsIgnoreCase(method)) {
				PostMethod postMethod = new PostMethod(url);
				setPostParams(postMethod);
				httpMethod = postMethod;
			} else if (GET.equalsIgnoreCase(method)) {
				httpMethod = new GetMethod(url);
			}
			sendStatus = httpclient.executeMethod(httpMethod);
//			if (sendStatus == HttpStatus.SC_MOVED_TEMPORARILY
//					|| sendStatus == HttpStatus.SC_MOVED_PERMANENTLY
//					|| sendStatus == HttpStatus.SC_SEE_OTHER
//					|| sendStatus == HttpStatus.SC_TEMPORARY_REDIRECT) {
//				Header header = httpMethod.getResponseHeader("location");
//				String newuri = header.getValue();
//				if (newuri == null || "".equals(newuri)) {
//					newuri = "/";
//				} else if (!newuri.startsWith("http")) {
//					newuri = url.substring(0, url.lastIndexOf("/") + 1)
//							+ newuri;
//				}
//				httpMethod = new GetMethod(newuri);
//				sendStatus = httpclient.executeMethod(httpMethod);
//			}

			// 获取网页编码格式text/html;charset=utf-8
			// httpMethod.getResponseHeader("Content-Type").getValue();

			InputStream in = httpMethod.getResponseBodyAsStream();
			source = InputStream2String.inputStream2String(in, charsetName);
		} catch (Exception e) { // HttpException, IOException
			LOG.error("访问[" + url + "]出错,如果设置了代理,请检查代理ip以及端口号是否正确", e);
		} finally {
			httpMethod.releaseConnection();
		}
		LOG.info("请求[" + url + "]返回状态码[" + sendStatus + "]");
		if (sendStatus != 200) {
			source = "";
		}
		return source;
	}

	/**
	 * 设置请求参数
	 * 
	 * @param httpMethod
	 */
	private void setPostParams(PostMethod httpMethod) {
		if (params != null && params.size() > 0) {
			NameValuePair[] nps = new NameValuePair[params.size()];
			Iterator<String> it = params.keySet().iterator();
			int i=0;
			while (it.hasNext()) {
				String parameterName = it.next();
				nps[i++] = new NameValuePair(parameterName, params
						.get(parameterName));
			}
			httpMethod.setRequestBody(nps);
		}
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getCharsetName() {
		return charsetName;
	}

	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public int getSoTimeOut() {
		return soTimeOut;
	}

	public String getProxyIP() {
		return ProxyIP;
	}

	public String getPort() {
		return port;
	}

	public int getRetryTimes() {
		return retryTimes;
	}

	public org.apache.commons.httpclient.HttpClient getHttpclient() {
		return httpclient;
	}

	public void setHttpclient(
			org.apache.commons.httpclient.HttpClient httpclient) {
		this.httpclient = httpclient;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public void setSoTimeOut(int soTimeOut) {
		this.soTimeOut = soTimeOut;
	}

	public void setProxyIP(String proxyIP) {
		ProxyIP = proxyIP;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}

}
