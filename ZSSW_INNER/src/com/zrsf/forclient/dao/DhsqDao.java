package com.zrsf.forclient.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.forclient.vo.dhsq.BankAccount;
import com.zrsf.forclient.vo.dhsq.ChangeRecord;
import com.zrsf.forclient.vo.dhsq.CwxxVo;
import com.zrsf.forclient.vo.dhsq.FpxxVo;
import com.zrsf.forclient.vo.dhsq.HouseEstate;
import com.zrsf.forclient.vo.dhsq.Investor;
import com.zrsf.forclient.vo.dhsq.JdhdxxVo;
import com.zrsf.forclient.vo.dhsq.LandedEstate;
import com.zrsf.forclient.vo.dhsq.NsrDjxxForthVo;
import com.zrsf.forclient.vo.dhsq.NsrDjxxSecondVo;
import com.zrsf.forclient.vo.dhsq.NsrDjxxThirdVo;
import com.zrsf.forclient.vo.dhsq.NsrGeneral;
import com.zrsf.forclient.vo.dhsq.OfficeStaff;
import com.zrsf.forclient.vo.dhsq.QsxxVo;
import com.zrsf.forclient.vo.dhsq.RegisteSource;
import com.zrsf.forclient.vo.dhsq.RkxxVo;
import com.zrsf.forclient.vo.dhsq.SbxxVo;
import com.zrsf.forclient.vo.dhsq.StatusRecord;
import com.zrsf.forclient.vo.dhsq.TsxxVo;
import com.zrsf.forclient.vo.dhsq.WzxxVo;

/**
 * 单户税情查询
 * 
 * @author Terry
 * 
 */
public class DhsqDao {
	private SqlSessionTemplate sessionTemplate;
	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	/**
	 * 纳税人模糊搜索
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, String>> selectNsrByKeyWords(Map<String, Object> map) {
		List<Map<String, String>> list = null;
		list = sessionTemplate.selectList("com.zrsf.map.dhsq.selectNsrByKeys",
				map);
		return list;
	}

	/**
	 * 权限验证查询
	 * 
	 * @param map
	 * @return
	 */
	public int selectQx(Map<String, Object> map) {
		int k = sessionTemplate.selectOne("com.zrsf.map.dhsq.selectQx", map);
		return k;
	}

	/**
	 * 纳税人登记信息--基本信息查询
	 * 
	 * @param swglm
	 * @return
	 */
	public NsrGeneral selectNsrJbxx(String swglm) {
		NsrGeneral jbxx = sessionTemplate.selectOne(
				"com.zrsf.map.dhsq.selectNsrJbqk", swglm);
		return jbxx;
	}

	public NsrDjxxSecondVo selectNsrDjxxSecond(String swglm) {
		NsrDjxxSecondVo djxxSecondVo = new NsrDjxxSecondVo();
		try {
			
		
		List<Investor> tzrxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectNsrDjxxTZrxx", swglm);
		List<OfficeStaff> rzryxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectNsrRzryxx", swglm);
		List<BankAccount> yhzhxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectNsrYhzhxx", swglm);
		if (rzryxx != null && rzryxx.size() > 0) {
			djxxSecondVo.setRzryxx(rzryxx);
		}
		if (tzrxx != null && tzrxx.size() > 0) {
			djxxSecondVo.setTzrxx(tzrxx);
		}
		if (yhzhxx != null && yhzhxx.size() > 0) {
			djxxSecondVo.setYhzhxx(yhzhxx);
		}
		// NsrDjxxSecondVo
		// djxxSecondVo=sessionTemplate.selectOne("com.zrsf.map.dhsq.selectNsrDjxxSecond",
		// swglm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return djxxSecondVo;
	}

	public int selectSumNsrByKeyWords(Map<String, Object> map) {
		Integer nsrAmount = sessionTemplate.selectOne(
				"com.zrsf.map.dhsq.selectSumNsrByKeys", map);
		return nsrAmount;
	}

	public NsrDjxxThirdVo selectNsrDjxxThird(String swglm) {
		NsrDjxxThirdVo djxxThirdVo = new NsrDjxxThirdVo();
		List<HouseEstate> fcdjxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectNsrFcdj", swglm);
		if (fcdjxx != null && fcdjxx.size() > 0) {
			djxxThirdVo.setFcdjxx(fcdjxx);
		}
		List<LandedEstate> tddjxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectNsrTddj", swglm);
		if (tddjxx != null && tddjxx.size() > 0) {
			djxxThirdVo.setTddjxx(tddjxx);
		}
		return djxxThirdVo;
	}

	public NsrDjxxForthVo selectNsrDjxxForth(String swglm) {
		NsrDjxxForthVo djxxForthVo = new NsrDjxxForthVo();
		List<ChangeRecord> djbgxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectNsrDjbg", swglm);
		if (djbgxx != null && djbgxx.size() > 0) {
			djxxForthVo.setDjbgxx(djbgxx);
		}
		List<RegisteSource> djlyxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectNsrDjly", swglm);
		if (djlyxx != null && djlyxx.size() > 0) {
			djxxForthVo.setDjlyxx(djlyxx);
		}
		List<StatusRecord> nsrztxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectNsrZt", swglm);
		if (nsrztxx != null && nsrztxx.size() > 0) {
			djxxForthVo.setNsrztxx(nsrztxx);
		}
		return djxxForthVo;
	}

	public List<JdhdxxVo> selectJdhdxx(Map<String, Object> map) {
		List<JdhdxxVo> jdhdxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectJdhdxx", map);
		return jdhdxx;
	}

	public List<SbxxVo> selectSbxx(Map<String, Object> map) {
		List<SbxxVo> sbxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectSbxx", map);
		return sbxx;
	}

	public List<Map<String, Object>> selectSbxxMain(Map<String, Object> map) {
		 List<Map<String, Object>> sbxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectSbxxMain", map);
		return sbxx;
	}	
	
	public double selectSbzje(Map<String, Object> map) {
		Double sbzje = sessionTemplate.selectOne(
				"com.zrsf.map.dhsq.selectSbZje", map);
		return sbzje;
	}

	public List<RkxxVo> selectRkxx(Map<String, Object> map) {
		List<RkxxVo> rkxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectRkxx", map);
		return rkxx;
	}

	public List<Map<String, Object>> selectRkxxMain(Map<String, Object> map) {
		List<Map<String, Object>> rkxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectRkxxMain", map);
		return rkxx;
	}
	
	public double selectRkzje(Map<String, Object> map) {
		Double rkzje = sessionTemplate.selectOne(
				"com.zrsf.map.dhsq.selectRkzje", map);
		return rkzje;
	}

	public List<QsxxVo> selectQsxx(Map<String, Object> map) {
		List<QsxxVo> qsxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectQsxx", map);
		return qsxx;
	}

	public double selectQszje(Map<String, Object> map) {
		Double qszje = sessionTemplate.selectOne(
				"com.zrsf.map.dhsq.selectQszje", map);
		return qszje;
	}

	public List<TsxxVo> selectTsxx(Map<String, Object> map) {
		List<TsxxVo> tsxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectTsxx", map);
		return tsxx;
	}

	public double selectTszje(Map<String, Object> map) {
		Double tszje = sessionTemplate.selectOne(
				"com.zrsf.map.dhsq.selectTszje", map);
		return tszje;
	}

	/**
	 * 发票领用存信息
	 * 
	 * @param map
	 * @param lycbj
	 * @return
	 */
	public List<FpxxVo> selectFpxx(Map<String, Object> map, int lycbj) {
		String sql = null;
		switch (lycbj) {
		case 0:
			sql = "com.zrsf.map.dhsq.selectFply";
			break;
		case 1:
			sql = "com.zrsf.map.dhsq.selectFpjxhx";
			break;
		default:
			sql = "com.zrsf.map.dhsq.selectFpkc";
			break;
		}
		List<FpxxVo> fpxx = sessionTemplate.selectList(sql, map);
		return fpxx;
	}

	public List<CwxxVo> selectCwxx(Map<String, Object> map) {
		List<CwxxVo> cwxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectCwxx", map);
		return cwxx;
	}

	public List<WzxxVo> selectWzxx(Map<String, Object> map) {
		List<WzxxVo> wzxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectWzxx", map);
		return wzxx;
	}
	public int selectZsfs(String swglm) {		
		return sessionTemplate.selectOne("com.zrsf.map.dhsq.selectNsrZsfs",swglm);
	}
	public List<JdhdxxVo> selectJdxx(Map<String, Object> map) {
		List<JdhdxxVo> jdhdxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectJdxx", map);
		return jdhdxx;
	}
	public List<JdhdxxVo> selectHdxx(Map<String, Object> map) {
		List<JdhdxxVo> jdhdxx = sessionTemplate.selectList(
				"com.zrsf.map.dhsq.selectHdxx", map);
		return jdhdxx;
	}
	
}
