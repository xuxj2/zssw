//package com.zrsf.backup.dao;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.ibatis.session.ExecutorType;
//import org.apache.ibatis.session.SqlSession;
//import org.mybatis.spring.SqlSessionTemplate;
//
//import com.zrsf.backup.vo.NewsSynsVo;
//
///**
// *地税新闻、涉税通告、手机报、知识库、法规库、全省动态、寒山闻钟、局长信箱 抓取入库
// * 
// */
//public class NewsGrabDao {
//	private static final Log LOG = LogFactory.getLog(NewsGrabDao.class);
//	private  SqlSessionTemplate sqlSessionTemplate;// 静态属性，服务器启动时有spring初始化
//	public SqlSessionTemplate getSqlSessionTemplate() {
//		return sqlSessionTemplate;
//	}
//	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
//		this.sqlSessionTemplate = sqlSessionTemplate;
//	}
//
//	/**
//	 * 将抓取的一组数据插入新闻动态：
//	 * 
//	 * @param list
//	 *            新闻信息集合
//	 */
//	public void insertXwdt(List<Map<String, String>> list) {
//		if(list==null||list.size()<1){
//			return;
//		}
//		SqlSession session = sqlSessionTemplate.getSqlSessionFactory()
//				.openSession(ExecutorType.BATCH, false);
//		try {
//			String checkSql = "com.zrsf.map.lmxx.selectNewsForCheck";
//			String insertSql = "com.zrsf.map.lmxx.insertLmxx";
//			for (int i = 0; i < list.size(); i++) {
//				Map<String, String> map = list.get(i);
//				try {
//					int check = Integer.parseInt(session.selectOne(checkSql, map).toString());
//					if (check < 1) {
//						session.insert(insertSql, map);
//					} else {
//						LOG.info("已存在");
//					
//					}
//					if (i % 1000 == 0) {
//						session.commit();
//						session.clearCache();
//					}
//				} catch (Exception e) {					
//					LOG.error(e);
//					e.printStackTrace();
//					continue;
//				}
//			}
//			session.commit();
//			session.clearCache();
//		} catch (Exception e) {
//			LOG.error("入库出错");
//			e.printStackTrace();
//		} finally {
//			session.commit();
//			session.close();
//		}
//	}
//	/**
//	 * 将抓取的一组数据插入政策法规：
//	 * 
//	 * @param list
//	 *            新闻信息集合
//	 */
//	public void insertZcfg(List<Map<String, String>> list) {
//		if(list==null||list.size()<1){
//			return;
//		}
//		SqlSession session = sqlSessionTemplate.getSqlSessionFactory()
//				.openSession(ExecutorType.BATCH, false);
//		try {
//			String checkSql = "com.zrsf.map.lmxx.selectZcfgForCheck";
//			String insertSql = "com.zrsf.map.lmxx.insertZcfg";
//			for (int i = 0; i < list.size(); i++) {
//				Map<String, String> map = list.get(i);
//				try {
//					int check = Integer.parseInt(session.selectOne(checkSql, map).toString());
//					if (check < 1) {
//						session.insert(insertSql, map);
//					} else {
//						LOG.info("已存在");
//					}
//					if (i % 1000 == 0) {
//						session.commit();
//						session.clearCache();
//					}
//				} catch (Exception e) {					
//					LOG.error(e);
//					e.printStackTrace();
//					continue;
//				}
//			}
//			session.commit();
//			session.clearCache();
//		} catch (Exception e) {
//			LOG.error("入库出错");
//			e.printStackTrace();
//		} finally {
//			session.commit();
//			session.close();
//		}
//	}
//	/**
//	 * 将抓取的一组数据插入投诉处理：
//	 * 
//	 * @param list
//	 *            新闻信息集合
//	 */
//	public void insertTscl(List<Map<String, String>> list) {
//		if(list==null||list.size()<1){
//			return;
//		}
//		SqlSession session = sqlSessionTemplate.getSqlSessionFactory()
//				.openSession(ExecutorType.BATCH, false);
//		try {
//			String checkSql = "com.zrsf.map.lmxx.selectTsxxForCheck";
//			String insertSql = "com.zrsf.map.lmxx.insertTsxx";
//			for (int i = 0; i < list.size(); i++) {
//				Map<String, String> map = list.get(i);
//				try {
//					int check = Integer.parseInt(session.selectOne(checkSql, map).toString());
//					if (check < 1) {
//						session.insert(insertSql, map);
//					} else {
//						LOG.info("已存在");
//					}
//					if (i % 1000 == 0) {
//						session.commit();
//						session.clearCache();
//					}
//				} catch (Exception e) {					
//					LOG.error(e);
//					e.printStackTrace();
//					continue;
//				}
//			}
//			session.commit();
//			session.clearCache();
//		} catch (Exception e) {
//			LOG.error("入库出错");
//			e.printStackTrace();
//		} finally {
//			session.commit();
//			session.close();
//		}
//	}
//	
//	
//	/**
//	 * 删除投诉处理某个栏目的全部信息
//	 * 
//	 * @param lmid
//	 */
//	public void deleteLm(String lmid) {
//		sqlSessionTemplate.delete("com.zrsf.map.lmxx.deleteTsxx", lmid);
//	}
//	/**
//	 * 更新最近同步时间
//	 * @param rsvo
//	 */
//	public void updateLastTime(NewsSynsVo rsvo) {		
//		sqlSessionTemplate.update("com.zrsf.map.renewSetup.updateLastTime", rsvo);		
//	}
//
//	
//	
//	
//}
