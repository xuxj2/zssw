package com.zrsf.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class FileUtil {
	private static final Log LOG = LogFactory.getLog(FileUtil.class);
	
	private static final String WEB = "web.properties";
	private static final String HTMLCODE = "htmlcode.xml";	
	
	public static Map<String,String> htmlCodeMap;
	public static Map<String,String> dailiMap;
	public static Map<String,String> webMap;
	/**
	 * 对静态属性htmlCodeMap、 dailiMap、webMap进行初始化
	 */
	public static void initResource(){
		htmlCodeMap=getxmlValue(HTMLCODE);
		webMap=getProMap(WEB);
		dailiMap=getProMap("daili.properties");
	}
	

/**
 *读取properties文件，返回Map
 * @param pro  文件名，该文件应该在class_path路径下
 * @return
 */
	public static Map<String,String> getProMap(String pro) {
		Map<String,String> map=new HashMap<String, String>();
		Properties p = new Properties();		
		try {
			URL url=getResourceUrl(pro, FileUtil.class);
			LOG.info("读取["+pro+"]的路径是:"+url);
			p.load(url.openStream());
			Iterator< String> it=p.stringPropertyNames().iterator();
			while(it.hasNext()){
				String propertyName=it.next();
				map.put(propertyName, p.getProperty(propertyName));
			}			
		} catch (IOException e1) {
			LOG.error("===========读取["+pro+"]文件出错", e1);
		}		
		return map;
	}
	
	/**
	 * 读取properties文件文件中指定key的值：
	 * properties文件应在classPath路径下，
	 * @param pro properties文件的名称
	 * @param name  properties配置里面的key
	 * @return 获取到名称对应的value
	 */
	public static String getProValue(String pro, String name,String charsetName)  {
		Properties p = new Properties();		
		try {
			URL url=getResourceUrl(pro, FileUtil.class);
			LOG.info("读取["+pro+"]的路径是:"+url);
			p.load(url.openStream());
		} catch (IOException e1) {
			LOG.error("===========读取["+pro+"]文件出错", e1);
		}		
		String value = p.getProperty(name, "");
		try {
			value = new String(value.getBytes("ISO-8859-1"), charsetName);
		} catch (UnsupportedEncodingException e) {
			LOG.error("===========读取["+pro+"]文件,编码转换错误", e);
		}
		if ("".equals(value))
			LOG.info("在["+pro+"]文件里未找到名字是[" + name + "]的值");
		return value;
	}

	/**
	 * 读取xml文件到Map中
	 * @param xmlName  xml文件名，该文件需以utf8格式编码
	 * @return  
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> getxmlValue(String xmlName) {
		Map<String,String> map = new HashMap<String,String>();
		SAXReader sr = new SAXReader();
		sr.setEncoding("UTF-8");
		Document doc = null;
		try {
			URL url=getResourceUrl(xmlName, FileUtil.class);
			LOG.info("读取["+xmlName+"]的路径是:"+url);
			doc = sr.read(url);
			doc.setXMLEncoding("UTF-8");
		} catch (Exception e1) {
			LOG.error("===========读取["+xmlName+"]文件出错", e1);
		}		
		try{
			Element root = doc.getRootElement();
			List<Element> elements =root.elements(); // 根节点<szzssw>
			for (int i = 0; i < elements.size(); i++) {
				Element e = elements.get(i);// 子节点<html>
				List<Element> elements2 = e.elements();
				map.put(elements2.get(0).getText(), elements2.get(1).getText());// 子节点<key><value>
			}
		}catch(Exception e){
			LOG.info("解析xml出错,请确认xml文件格式是否正确");
		}
		return map;
	}

	
	/**
	 * 获取配置文件url路径
	 */
	public static URL getResourceUrl(String resourceName, Class<?> callingClass) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);
        if (url == null) {
            url = FileUtil.class.getClassLoader().getResource(resourceName);
        }
        if (url == null) {
            ClassLoader cl = callingClass.getClassLoader();
            if (cl != null)
                url = cl.getResource(resourceName);
        }
        if ((url == null) && (resourceName != null) && ((resourceName.length() == 0) || (resourceName.charAt(0) != '/'))) { 
            return getResourceUrl('/' + resourceName, callingClass);
        }
        return url;
    }

	
	
}
