package com.zrsf.forclient.vo.dhsq;

import java.io.Serializable;

public class NsrGeneral implements Serializable {
	private static final long serialVersionUID = 1L;
	private String swglm; 	// 税务管理码
	private String nsrsbm; 	// 纳税人识别码",
	private String mc; 		// 纳税人名称",
	private String zzjgdmzh; // 组织机构代码证号",
	private String gsdjzh; 	// 工商登记证号",
	private String yyzzzh; 	// 营业执照证号,
	//private String frname; 	// 法人代表,
	//private String frtel; 	// 法人联系电话,
	private String zczb; 	// 注册资本,
	private String address; // 实际营业地址,
	private String tel; 	// 实际电话,
	private String zy; 		// 主营,
	private String jy; 		// 兼营;
	
	private String hyml;	// 所属国标行业门类
	private String hydl;	// 所属国标行业大类,
	
	private String sshy; 	// 所属国标行业中小类,
	private String zclx; 	// 注册类型,
	private String sgqx; 	// 企业所得税管权限,
	private String zsfs; 	// 企业所得说征收方式,
	private String grzsfs; 	// 个人所得税征收方式,
	private String nsrzt; 	// 纳税人状态
	private String zcdz;	//注册地址
	private String zcddh;	//注册地电话
	//private String sdh;	//双定户 vs申报户
	
	private String jcjg;	//注册地址
	private String gljg;	//注册地电话
	
	public NsrGeneral(){
		
	}
	public String getSwglm() {
		return swglm;
	}
	public void setSwglm(String swglm) {
		this.swglm = swglm;
	}
	public String getNsrsbm() {
		return nsrsbm;
	}
	public void setNsrsbm(String nsrsbm) {
		this.nsrsbm = nsrsbm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getZzjgdmzh() {
		return zzjgdmzh;
	}
	public void setZzjgdmzh(String zzjgdmzh) {
		this.zzjgdmzh = zzjgdmzh;
	}
	public String getGsdjzh() {
		return gsdjzh;
	}
	public void setGsdjzh(String gsdjzh) {
		this.gsdjzh = gsdjzh;
	}
	public String getYyzzzh() {
		return yyzzzh;
	}
	public void setYyzzzh(String yyzzzh) {
		this.yyzzzh = yyzzzh;
	}
//	public String getFrname() {
//		return frname;
//	}
//	public void setFrname(String frname) {
//		this.frname = frname;
//	}
//	public String getFrtel() {
//		return frtel;
//	}
//	public void setFrtel(String frtel) {
//		this.frtel = frtel;
//	}
	
	public String getZczb() {
		return zczb;
	}
public String getHyml() {
		return hyml;
	}
	public void setHyml(String hyml) {
		this.hyml = hyml;
	}
	public String getHydl() {
		return hydl;
	}
	public void setHydl(String hydl) {
		this.hydl = hydl;
	}
	//	public String getSdh() {
//		return sdh;
//	}
//	public void setSdh(String sdh) {
//		this.sdh = sdh;
//	}
	public void setZczb(String zczb) {
		this.zczb = zczb;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getJy() {
		return jy;
	}
	public void setJy(String jy) {
		this.jy = jy;
	}
	public String getSshy() {
		return sshy;
	}
	public void setSshy(String sshy) {
		this.sshy = sshy;
	}
	public String getZclx() {
		return zclx;
	}
	public void setZclx(String zclx) {
		this.zclx = zclx;
	}
	public String getSgqx() {
		return sgqx;
	}
	public void setSgqx(String sgqx) {
		this.sgqx = sgqx;
	}
	public String getZsfs() {
		return zsfs;
	}
	public void setZsfs(String zsfs) {
		this.zsfs = zsfs;
	}
	public String getGrzsfs() {
		return grzsfs;
	}
	public void setGrzsfs(String grzsfs) {
		this.grzsfs = grzsfs;
	}
	public String getNsrzt() {
		return nsrzt;
	}
	public void setNsrzt(String nsrzt) {
		this.nsrzt = nsrzt;
	}
	

	public String getJcjg() {
		return jcjg;
	}
	public void setJcjg(String jcjg) {
		this.jcjg = jcjg;
	}
	public String getGljg() {
		return gljg;
	}
	public void setGljg(String gljg) {
		this.gljg = gljg;
	}
	public String getZcdz() {
		return zcdz;
	}
	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}
	public String getZcddh() {
		return zcddh;
	}
	public void setZcddh(String zcddh) {
		this.zcddh = zcddh;
	}
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(this==obj)return true;
		if(obj instanceof NsrDjxxSecondVo){
			NsrGeneral other =(NsrGeneral) obj;
			return this.swglm.equals(other.getSwglm());
		}
		return super.equals(obj);
	}	
	public int hashCode() {		
		return Integer.parseInt(this.getSwglm());
	}
	
	public String toString() {		
		return this.mc+"("+this.swglm+")";
	}
	
	
	
	
}
