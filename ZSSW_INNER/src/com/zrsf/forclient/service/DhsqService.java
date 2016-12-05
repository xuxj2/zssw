package com.zrsf.forclient.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zrsf.forclient.dao.DhsqDao;
import com.zrsf.forclient.util.PagingUtil;
import com.zrsf.forclient.util.Validater;
import com.zrsf.forclient.vo.ResponseObject;
import com.zrsf.forclient.vo.Swry;
import com.zrsf.forclient.vo.dhsq.CwxxVo;
import com.zrsf.forclient.vo.dhsq.FpxxVo;
import com.zrsf.forclient.vo.dhsq.JdhdxxVo;
import com.zrsf.forclient.vo.dhsq.NsrDjxxForthVo;
import com.zrsf.forclient.vo.dhsq.NsrDjxxSecondVo;
import com.zrsf.forclient.vo.dhsq.NsrDjxxThirdVo;
import com.zrsf.forclient.vo.dhsq.NsrGeneral;
import com.zrsf.forclient.vo.dhsq.QsxxPage;
import com.zrsf.forclient.vo.dhsq.QsxxVo;
import com.zrsf.forclient.vo.dhsq.RkxxPage;
import com.zrsf.forclient.vo.dhsq.RkxxVo;
import com.zrsf.forclient.vo.dhsq.SbxxPage;
import com.zrsf.forclient.vo.dhsq.SbxxVo;
import com.zrsf.forclient.vo.dhsq.TsxxPage;
import com.zrsf.forclient.vo.dhsq.TsxxVo;
import com.zrsf.forclient.vo.dhsq.WzxxVo;

/**
 * 用于单户税情查询
 * 
 * @author Terry
 * 
 */
public class DhsqService {
	private DhsqDao dao;

	public DhsqDao getDao() {
		return dao;
	}

	public void setDao(DhsqDao dao) {
		this.dao = dao;
	}

	/**
	 * 按照税务管理码片段 或 名称（多个字段空格隔开）模糊查询纳税人列表
	 * 
	 * @param swglm
	 * @param mc
	 * @param pagecode
	 * @param swry
	 * @return
	 */
	public ResponseObject selectNsrByKeyWords(String swglm, String mc,
			int pagecode, Swry swry) {
		String[] mcs = null;
		if (mc != null) {
			mcs = mc.trim().split("\\s+");
		}
		Map<String, Object> map = PagingUtil.addPageParameter(pagecode);
		map.put("mcs", mcs);
		if (swglm != null && !"".equals(swglm.trim())) {
			map.put("swglm", swglm);
		}
		map.put("gljg", swry.getRzjgDm());
		ResponseObject resp = new ResponseObject();
		Map<String, Object> entity = new HashMap<String, Object>();
		int sum = dao.selectSumNsrByKeyWords(map);
		entity.put("sum", sum);
		if (sum < 1) {
			resp.setMessage("没有匹配的纳税人");
			entity.put("nsr", null);
			resp.setEntity(entity);
		} else {
			List<Map<String, String>> list = dao.selectNsrByKeyWords(map);
			if (list != null && list.size() > 0) {
				entity.put("nsr", list);
				resp.setEntity(entity);
			} else {
				entity.put("nsr", null);
				resp.setEntity(entity);
				resp.setMessage("没有更多了");
			}
		}
		resp.setCode(ResponseObject.successCode);
		return resp;
	}

	/**
	 * 当税务人员查看单户税情时，验证其所在机构管辖权限
	 * 
	 * @param swglm
	 * @param swry
	 * @return
	 */
	private ResponseObject validate(String swglm, Swry swry) {
		ResponseObject resp = new ResponseObject();
		if (swglm == null || !Validater.validateSwglm(swglm)) {
			resp.setCode(ResponseObject.failCode);
			resp.setMessage("税务管理码不正确");
			return resp;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gljg", swry.getRzjgDm());
		map.put("swglm", swglm);
		// 验证权限
		int allowed = dao.selectQx(map);
		if (allowed == 1) {
			resp.setCode(ResponseObject.successCode);
		} else {
			resp.setCode(ResponseObject.failCode);
			resp.setMessage("您对该纳税人没有管理权限");
		}
		return resp;
	}

	/**
	 * 查询纳税人登记信息--基本信息
	 * 
	 * @param swglm
	 * @param swry
	 * @return
	 */
	public ResponseObject queryJbxx(String swglm, Swry swry) {
		ResponseObject resp = validate(swglm, swry);
		if (resp.getCode().equals(ResponseObject.successCode)) {
			NsrGeneral djxx = dao.selectNsrJbxx(swglm);
			resp.setEntity(djxx);
		}
		return resp;
	}

	/**
	 * 纳税人登记信息--第二分页，投资人、银行账户、任职人员信息
	 * 
	 * @param swglm
	 * @param swry
	 * @return
	 */
	public ResponseObject queryDjxxSecond(String swglm, Swry swry) {
		ResponseObject resp = validate(swglm, swry);
		if (resp.getCode().equals(ResponseObject.successCode)) {
			NsrDjxxSecondVo djxxSecondVo = dao.selectNsrDjxxSecond(swglm);
			resp.setEntity(djxxSecondVo);
		}
		return resp;
	}

	/**
	 * 房产登记、土地登记信息
	 */
	public ResponseObject queryDjxxThird(String swglm, Swry swry) {
		ResponseObject resp = validate(swglm, swry);
		if (resp.getCode().equals(ResponseObject.successCode)) {
			NsrDjxxThirdVo thirdVo = dao.selectNsrDjxxThird(swglm);
			resp.setEntity(thirdVo);
		}
		return resp;
	}

	public ResponseObject queryDjxxForth(String swglm, Swry swry) {
		ResponseObject resp = validate(swglm, swry);
		if (resp.getCode().equals(ResponseObject.successCode)) {
			NsrDjxxForthVo forthVo = dao.selectNsrDjxxForth(swglm);
			resp.setEntity(forthVo);
		}
		return resp;
	}

	/**
	 * 鉴定核定信息查询
	 * 
	 * @param swglm
	 * @param swry
	 * @param pagecode
	 * @return
	 */
	public ResponseObject queryJdhdxx(String swglm, Swry swry, int pagecode) {
		ResponseObject resp = validate(swglm, swry);
		if (resp.getCode().equals(ResponseObject.successCode)) {
			Map<String, Object> map = PagingUtil.addPageParameter(pagecode);
			map.put("swglm", swglm);
			List<JdhdxxVo> jdhdxx = null;
			int zsfs = dao.selectZsfs(swglm);
			System.out.println(zsfs);
			if (zsfs > 39 && zsfs < 43) {
				jdhdxx = dao.selectHdxx(map);
			} else {
				jdhdxx = dao.selectJdxx(map);
			}
			if (jdhdxx != null && jdhdxx.size() > 0) {
				resp.setEntity(jdhdxx);
			}
		}
		return resp;
	}

	/**
	 * 申报信息查询
	 * 
	 * @param swglm
	 * @param swry
	 * @param endDate
	 * @param startDate
	 * @return
	 */
	public ResponseObject querySbxx(String swglm, Swry swry, int pagecode,
			String startDate, String endDate) {
		ResponseObject resp = validate(swglm, swry);
		SbxxPage sbxx = new SbxxPage();
		if (resp.getCode().equals(ResponseObject.successCode)) {
			Map<String, Object> map = PagingUtil.addPageParameter(pagecode);
			map.put("swglm", swglm);
			if (startDate != null && Validater.validateDate(startDate)) {
				map.put("startDate", startDate);
			}
			if (endDate != null && Validater.validateDate(endDate)) {
				map.put("endDate", endDate);
			}
			List<SbxxVo> sbxxList = dao.selectSbxx(map);
			if (sbxxList != null && sbxxList.size() > 0) {
				sbxx.setSbxx(sbxxList);
			}
			double zje = dao.selectSbzje(map);
			sbxx.setSum(zje);
			resp.setEntity(sbxx);
		}
		return resp;
	}

	/**
	 * 纳税人税款入库信息查询
	 * 
	 * @param swglm
	 * @param swry
	 * @param pagecode
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public ResponseObject queryRkxx(String swglm, Swry swry, int pagecode,
			String startDate, String endDate) {
		ResponseObject resp = validate(swglm, swry);
		RkxxPage rkxx = new RkxxPage();
		if (resp.getCode().equals(ResponseObject.successCode)) {
			Map<String, Object> map = PagingUtil.addPageParameter(pagecode);
			map.put("swglm", swglm);
			if (startDate != null && Validater.validateDate(startDate)) {
				map.put("startDate", startDate);
			}
			if (endDate != null && Validater.validateDate(endDate)) {
				map.put("endDate", endDate);
			}
			List<RkxxVo> rkxxList = dao.selectRkxx(map);
			if (rkxxList != null && rkxxList.size() > 0) {
				rkxx.setRkxx(rkxxList);
			}
			double rkzje = dao.selectRkzje(map);
			rkxx.setSum(rkzje);
			resp.setEntity(rkxx);
		}
		return resp;
	}

	/**
	 * 纳税人欠税信息查询
	 * 
	 * @param swglm
	 * @param swry
	 * @param pagecode
	 * @param endDate
	 * @return
	 */
	public ResponseObject queryQsxx(String swglm, Swry swry, int pagecode,
			String endDate) {
		ResponseObject resp = validate(swglm, swry);
		QsxxPage qsxx = new QsxxPage();
		if (resp.getCode().equals(ResponseObject.successCode)) {
			Map<String, Object> map = PagingUtil.addPageParameter(pagecode);
			map.put("swglm", swglm);
			if (endDate != null && Validater.validateDate(endDate)) {
				map.put("endDate", endDate);
			}
			List<QsxxVo> qsxxList = dao.selectQsxx(map);
			if (qsxxList != null && qsxxList.size() > 0) {
				qsxx.setQsxx(qsxxList);
			}
			double qszje = dao.selectQszje(map);
			qsxx.setSum(qszje);
			resp.setEntity(qsxx);
		}
		return resp;
	}

	/**
	 * 退税日期
	 * 
	 * @param swglm
	 * @param swry
	 * @param pagecode
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public ResponseObject queryTsxx(String swglm, Swry swry, int pagecode,
			String startDate, String endDate) {
		ResponseObject resp = validate(swglm, swry);
		TsxxPage tsxx = new TsxxPage();
		if (resp.getCode().equals(ResponseObject.successCode)) {
			Map<String, Object> map = PagingUtil.addPageParameter(pagecode);
			map.put("swglm", swglm);
			if (endDate != null && Validater.validateDate(endDate)) {
				map.put("endDate", endDate);
			}
			if (startDate != null && Validater.validateDate(startDate)) {
				map.put("startDate", startDate);
			}
			List<TsxxVo> tsxxList = dao.selectTsxx(map);
			if (tsxxList != null && tsxxList.size() > 0) {
				tsxx.setTsxx(tsxxList);
			}
			double tszje = dao.selectTszje(map);
			tsxx.setSum(tszje);
			resp.setEntity(tsxx);
		}
		return resp;
	}

	/**
	 * 发票领用存信息查询
	 * 
	 * @param swglm
	 * @param swry
	 * @param pagecode
	 * @param startDate
	 * @param endDate
	 * @param lycbj
	 * @return
	 */
	public ResponseObject queryFpxx(String swglm, Swry swry, int pagecode,
			String startDate, String endDate, int lycbj) {
		ResponseObject resp = validate(swglm, swry);
		if (resp.getCode().equals(ResponseObject.successCode)) {
			Map<String, Object> map = PagingUtil.addPageParameter(pagecode);
			map.put("swglm", swglm);
			if (endDate != null && Validater.validateDate(endDate)) {
				map.put("endDate", endDate);
			}
			if (startDate != null && Validater.validateDate(startDate)) {
				map.put("startDate", startDate);
			}
			List<FpxxVo> fpxxList = dao.selectFpxx(map, lycbj);
			if (fpxxList != null && fpxxList.size() > 0) {
				resp.setEntity(fpxxList);
			}
		}
		return resp;
	}

	/**
	 * 财务信息查询：根据纳税人管理码查询该纳税人所属期为指定年度的所有财务报表，按所属期倒序。返回值中将财务报表详细信息载入
	 * 
	 * @param swglm
	 * @param swry
	 * @param pagecode
	 * @param nf
	 * @return
	 */
	public ResponseObject queryCwxx(String swglm, Swry swry, int pagecode,
			String nf) {
		ResponseObject resp = validate(swglm, swry);
		if (resp.getCode().equals(ResponseObject.successCode)) {
			Map<String, Object> map = PagingUtil.addPageParameter(pagecode);
			map.put("swglm", swglm);
			if (nf != null && nf.matches("\\d{4}")) {
				map.put("nf", nf);
			}
			List<CwxxVo> cwxxList = dao.selectCwxx(map);
			if (cwxxList != null && cwxxList.size() > 0) {
				resp.setEntity(cwxxList);
			}
		}
		return resp;
	}

	/**
	 * 违章信息查询：根据纳税人管理码查询该纳税人在制定年度内受到的违章处罚记录
	 * 
	 * @param swglm
	 * @param swry
	 * @param pagecode
	 * @param nf
	 * @return
	 */
	public ResponseObject queryWzxx(String swglm, Swry swry, int pagecode,
			String nf) {
		ResponseObject resp = validate(swglm, swry);
		if (resp.getCode().equals(ResponseObject.successCode)) {
			Map<String, Object> map = PagingUtil.addPageParameter(pagecode);
			map.put("swglm", swglm);
			if (nf != null && nf.matches("\\d{4}")) {
				map.put("nf", nf);
			}
			List<WzxxVo> wzxxList = dao.selectWzxx(map);
			if (wzxxList != null && wzxxList.size() > 0) {
				resp.setEntity(wzxxList);
			}
		}
		return resp;
	}

	public ResponseObject queryQsxx(String swglm, Swry swry, int pagecode) {
		ResponseObject resp = validate(swglm, swry);
		QsxxPage qsxx = new QsxxPage();
		if (resp.getCode().equals(ResponseObject.successCode)) {
			Map<String, Object> map = PagingUtil.addPageParameter(pagecode);
			map.put("swglm", swglm);
			// if (endDate != null && Validater.validateDate(endDate)) {
			// map.put("endDate", endDate);
			// }
			List<QsxxVo> qsxxList = dao.selectQsxx(map);
			if (qsxxList != null && qsxxList.size() > 0) {
				qsxx.setQsxx(qsxxList);
			}
			double qszje = dao.selectQszje(map);
			qsxx.setSum(qszje);
			resp.setEntity(qsxx);
		}
		return resp;
	}

	/**
	 * 纳税人申报信息查询：
	 * 
	 * @param swglm
	 *            查询对象：纳税人税务管理码
	 * @param swry
	 *            查询主体：税务人员
	 * @param startDate
	 *            查询条件1：申报起始日期
	 * @param endDate
	 *            查询条件2：申报截至日期
	 * @return 期间内纳税人申报信息:税种、税目、申报金额 ，组装到ResponseObject对象
	 */
	public ResponseObject querySbxx(String swglm, Swry swry, String startDate,
			String endDate) {
		ResponseObject resp = validate(swglm, swry);
		if (resp.getCode().equals(ResponseObject.successCode)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("swglm", swglm);
			if (startDate != null && Validater.validateDate(startDate)) {
				map.put("startDate", startDate);
			}
			if (endDate != null && Validater.validateDate(endDate)) {
				map.put("endDate", endDate);
			}

			List<Map<String, Object>> sbxxList = dao.selectSbxxMain(map);
			if (sbxxList != null && sbxxList.size() > 0) {
				double sum = 0;
				for (Map<String, Object> tMap : sbxxList) {
					String d = (String) tMap.get("sbje");
					sum += Double.parseDouble(d);
				}
				JSONObject json = new JSONObject();
				json.put("sbxx", sbxxList);
				json.put("sum", String.format("%.2f", sum));
				resp.setEntity(json);
			}
		}
		return resp;
	}

	/**
	 * 纳税人申报信息查询：首先验证税务人员对纳税人的管理权限；二，验证各参数，放入查询map中；三，查询各税种税目申报金额list集合；四、
	 * 将查询结果放入ResponseObject对象
	 * 
	 * @param swglm
	 *            查询对象：纳税人税务管理码
	 * @param swry
	 *            查询主体：税务人员
	 * @param pagecode
	 *            页码
	 * @param zsxmDm
	 *            查询条件1：税种，征收项目代码
	 * @param zspmDm
	 *            查询条件1：税目，征收品目代码
	 * @param startDate
	 *            查询条件3：申报起始日期
	 * @param endDate
	 *            查询条件4：申报截至日期
	 * @return ResponseObject setEntity a list as List<SbxxVo>
	 */
	public ResponseObject querySbxx(String swglm, Swry swry, int pagecode,
			String zsxmDm, String zspmDm, String startDate, String endDate) {
		ResponseObject resp = validate(swglm, swry);
		if (resp.getCode().equals(ResponseObject.successCode)) {
			Map<String, Object> map = PagingUtil.addPageParameter(pagecode);
			map.put("swglm", swglm);
			if (startDate != null && Validater.validateDate(startDate)) {
				map.put("startDate", startDate);
			}
			if (endDate != null && Validater.validateDate(endDate)) {
				map.put("endDate", endDate);
			}
			if (zsxmDm != null && !zsxmDm.trim().isEmpty()) {
				map.put("zsxmDm", zsxmDm);
				if (zspmDm != null && !zspmDm.trim().isEmpty()) {
					map.put("zspmDm", zspmDm);
				}
			}
			List<SbxxVo> sbxxList = dao.selectSbxx(map);
			if (sbxxList != null && sbxxList.size() > 0) {
				resp.setEntity(sbxxList);
			}
		}
		return resp;
	}

	/**
	 * 纳税人入库信息查询：
	 * 
	 * @param swglm
	 *            查询对象：纳税人税务管理码
	 * @param swry
	 *            查询主体：税务人员
	 * @param startDate
	 *            查询条件1：入库起始日期
	 * @param endDate
	 *            查询条件2：入库截至日期
	 * @return 期间内纳税人入库信息:税种、税目、申报金额 ，组装到ResponseObject对象
	 */
	public ResponseObject queryRkxx(String swglm, Swry swry, String startDate,
			String endDate) {
		ResponseObject resp = validate(swglm, swry);
		if (resp.getCode().equals(ResponseObject.successCode)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("swglm", swglm);
			if (startDate != null && Validater.validateDate(startDate)) {
				map.put("startDate", startDate);
			}
			if (endDate != null && Validater.validateDate(endDate)) {
				map.put("endDate", endDate);
			}
			List<Map<String, Object>> rkxxList = dao.selectRkxxMain(map);
			if (rkxxList != null && rkxxList.size() > 0) {
				double sum = 0;
				for (Map<String, Object> tMap : rkxxList) {
//					BigDecimal d = (BigDecimal) tMap.get("rkje");
//					sum += d.doubleValue();
					String d = (String) tMap.get("rkje");
					sum += Double.parseDouble(d);
				}
				JSONObject json = new JSONObject();
				json.put("sbxx", rkxxList);
				json.put("sum", String.format("%.2f", sum));
				resp.setEntity(json);
			}
		}
		return resp;
	}
	/**
	 * 纳税人入库信息查询：首先验证税务人员对纳税人的管理权限；二，验证各参数，放入查询map中；三，查询各税种税目入库金额list集合；四、
	 * 将查询结果放入ResponseObject对象
	 * 
	 * @param swglm
	 *            查询对象：纳税人税务管理码
	 * @param swry
	 *            查询主体：税务人员
	 * @param pagecode
	 *            页码
	 * @param zsxmDm
	 *            查询条件1：税种，征收项目代码
	 * @param zspmDm
	 *            查询条件1：税目，征收品目代码
	 * @param startDate
	 *            查询条件3：入库起始日期
	 * @param endDate
	 *            查询条件4：入库截至日期
	 * @return ResponseObject setEntity a list as List<SbxxVo>
	 */
	public ResponseObject queryRkxx(String swglm, Swry swry, int pagecode,
			String zsxmDm, String zspmDm, String startDate, String endDate) {
		ResponseObject resp = validate(swglm, swry);
		if (resp.getCode().equals(ResponseObject.successCode)) {
			Map<String, Object> map = PagingUtil.addPageParameter(pagecode);
			map.put("swglm", swglm);
			if (startDate != null && Validater.validateDate(startDate)) {
				map.put("startDate", startDate);
			}
			if (endDate != null && Validater.validateDate(endDate)) {
				map.put("endDate", endDate);
			}
			if (zsxmDm != null && !zsxmDm.trim().isEmpty()) {
				map.put("zsxmDm", zsxmDm);
				if (zspmDm != null && !zspmDm.trim().isEmpty()) {
					map.put("zspmDm", zspmDm);
				}
			}
			List<RkxxVo> sbxxList = dao.selectRkxx(map);
			if (sbxxList != null && sbxxList.size() > 0) {
				resp.setEntity(sbxxList);
			}
		}
		return resp;
	}

}
