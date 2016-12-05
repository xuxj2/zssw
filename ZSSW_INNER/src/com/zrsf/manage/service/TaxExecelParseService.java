package com.zrsf.manage.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.zrsf.manage.dao.TaxStatisticDao;
import com.zrsf.manage.vo.TaxStatisticRow;

public class TaxExecelParseService {
	private TaxStatisticDao dao;
	public TaxStatisticDao getDao() {
		return dao;
	}
	public void setDao(TaxStatisticDao dao) {
		this.dao = dao;
	}

	/**
	 * 解析execel表格数据并入库
	 * 
	 * @param ssq
	 *            所属期，4位年份和两位月份
	 * @param file
	 *            要解析入库的execel文件
	 */
	public void parseTaxStatistic(String ssq, File file) {
		try {
			this.parseTaxStatistic(ssq, new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析execel表格数据并入库
	 * @param ssq
	 * @param in
	 */
	public void parseTaxStatistic(String ssq, InputStream in) {
		try {
			HSSFWorkbook workBook = new HSSFWorkbook(in);
			HSSFSheet sheet = workBook.getSheetAt(0);
			/*
			 * 税收收入预测数
			 */
			List<TaxStatisticRow> sssrList = new ArrayList<TaxStatisticRow>();
			HSSFRow row = sheet.getRow(2);// 地区标题行
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					TaxStatisticRow statistic = new TaxStatisticRow();
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						statistic.setSwjgMc(cell.getStringCellValue());
						statistic.setSsq(ssq);
						statistic.setXh(c-1);
					}
					sssrList.add(statistic);
				}
			}
			row = sheet.getRow(3);// 当月预计
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c <= cellNum; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						sssrList.get(c - 2).setByyj(cell.getNumericCellValue());
					}
				}
			}
			row = sheet.getRow(4);// 上年同期
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						sssrList.get(c - 2).setSntq(cell.getNumericCellValue());
					}
				}
			}
			row = sheet.getRow(5);// 当月增减
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						sssrList.get(c - 2).setBytb(cell.getNumericCellValue());
					}
				}
			}
			row = sheet.getRow(6);// 上月
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						sssrList.get(c - 2).setSysz(cell.getNumericCellValue());
					}
				}
			}
			row = sheet.getRow(7);// 累计预计
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						sssrList.get(c - 2).setBnlj(cell.getNumericCellValue());
					}
				}
			}
			row = sheet.getRow(8);// 上年累计
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						sssrList.get(c - 2).setSnlj(cell.getNumericCellValue());
					}
				}
			}
			row = sheet.getRow(9);// 累计增减
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						sssrList.get(c - 2).setLjtb(cell.getNumericCellValue());
					}
				}
			}
			this.insertTaxStatistic("com.zrsf.tax.deleteSssryj",
					"com.zrsf.tax.insertSssryj", ssq, sssrList);

			/*
			 * 公共财政预算收入预测数
			 */
			List<TaxStatisticRow> czysList = new ArrayList<TaxStatisticRow>();
			row = sheet.getRow(14);// 地区标题行
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					TaxStatisticRow statistic = new TaxStatisticRow();
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						statistic.setSwjgMc(cell.getStringCellValue());
						statistic.setSsq(ssq);
						statistic.setXh(c-1);
					}
					czysList.add(statistic);
				}
			}
			row = sheet.getRow(15);// 当月预计
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						czysList.get(c - 2).setByyj(cell.getNumericCellValue());
					}
				}
			}
			row = sheet.getRow(16);// 上年同期
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						czysList.get(c - 2).setSntq(cell.getNumericCellValue());
					}
				}
			}
			row = sheet.getRow(17);// 当月增减
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						czysList.get(c - 2).setBytb(cell.getNumericCellValue());
					}
				}
			}
			row = sheet.getRow(18);// 上月
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						czysList.get(c - 2).setSysz(cell.getNumericCellValue());
					}
				}
			}
			row = sheet.getRow(19);// 累计预计
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						czysList.get(c - 2).setBnlj(cell.getNumericCellValue());
					}
				}
			}
			row = sheet.getRow(20);// 上年累计
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						czysList.get(c - 2).setSnlj(cell.getNumericCellValue());
					}
				}
			}
			row = sheet.getRow(21);// 累计增减
			if (row != null) {
				int cellNum = row.getPhysicalNumberOfCells();
				for (int c = 2; c < cellNum; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						czysList.get(c - 2).setLjtb(cell.getNumericCellValue());
					}
				}
			}
			this.insertTaxStatistic("com.zrsf.tax.deleteCzysyj",
					"com.zrsf.tax.insertCzysyj", ssq, sssrList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void insertTaxStatistic(String delSql, String insertSql,
			String ssq, List<TaxStatisticRow> list) {
		if (list != null) {
			try {
				dao.deleteTaxStatistic(delSql, ssq);
				for (TaxStatisticRow row : list) {
					dao.insertTaxStatisticRow(insertSql, row);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<TaxStatisticRow> selectSssrReport(String ssq) {
		List<TaxStatisticRow> list=	dao.selectReport("com.zrsf.tax.selectSssryjBySsq",ssq);   
		return list;
	}

	public List<TaxStatisticRow> selectCzysReport(String ssq) {
		List<TaxStatisticRow> list=	dao.selectReport("com.zrsf.tax.selectCzysyjBySsq",ssq);   
		return list;
	}

	public List<String> selectSssrReportList() {
		List<String> list=dao.selectReportList("com.zrsf.tax.selectSssryjList"); 
		return list;
	}

	public List<String> selectCzysReportList() {
		List<String> list=dao.selectReportList("com.zrsf.tax.selectCzysyjList");
		return list;
	}

}
