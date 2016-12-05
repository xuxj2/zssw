package com.zrsf.appcenter;

import java.io.Serializable;
import java.util.Date;
/**
 * 应用中心应用的实体类
 * @author Terry
 * 2014-3-21
 */
public class ApplicationVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id; 				//主键
	private String name;			//应用名
	private String packageName;		//应用包名（android凭此区分不同应用）
	private int    versionCode;		//版本代码（android凭此区分不同版本）
	private String versionName;		//版本名称
	private String icon;			//应用的图标图片路径
	private String uri;				//下载路径
	private int download;			//下载量
	private Date uploadDate;		//上传时间
	private Date modifyTime;		//最后修改时间
	private String description;		//版本说明
	private String image1;		//详情图片1地址，地址是应用下的相对路径
	private String image2;		//详情图片2地址，地址是应用下的相对路径
	private String image3;		//详情图片3地址，地址是应用下的相对路径
	private String image4;		//详情图片4地址，地址是应用下的相对路径
	private String image5;		//详情图片5地址，地址是应用下的相对路径
	private String publisher;		//应用发布人
	private String appDescription;
		
	public ApplicationVo(){
		super();
	}
	public ApplicationVo(String id, String name, String packageName,
			int versionCode, Date uploadDate) {
		super();
		this.id = id;
		this.name = name;
		this.packageName = packageName;
		this.versionCode = versionCode;
		this.uploadDate = uploadDate;
	}
	public ApplicationVo( String name, String packageName,
			int versionCode, String versionName, String icon, Date uploadDate) {
		super();		
		this.name = name;
		this.packageName = packageName;
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.icon = icon;
		this.uploadDate = uploadDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public int getDownload() {
		return download;
	}


	public void setDownload(int download) {
		this.download = download;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(obj==this)return true;
		if(obj instanceof ApplicationVo){
			ApplicationVo other=(ApplicationVo) obj;
			return other.getPackageName().equals(this.getPackageName());
		}
		return super.equals(obj);
	}
	public int hashCode() {		
		return this.packageName.hashCode();
	}	
	public String toString() {		
		return this.name+" "+this.versionName;
	}	
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAppDescription() {
		return appDescription;
	}
	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getImage4() {
		return image4;
	}
	public void setImage4(String image4) {
		this.image4 = image4;
	}
	public String getImage5() {
		return image5;
	}
	public void setImage5(String image5) {
		this.image5 = image5;
	}	

}
