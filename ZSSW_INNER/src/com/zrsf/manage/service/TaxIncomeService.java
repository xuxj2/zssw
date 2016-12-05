package com.zrsf.manage.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;

import com.zrsf.common.util.SpringBeanUtil;
import com.zrsf.manage.dao.TaxStatisticDao;

public class TaxIncomeService {
	private TaxStatisticDao dao;
	public TaxStatisticDao getDao() {
		return dao;
	}
	public void setDao(TaxStatisticDao dao) {
		this.dao = dao;
	}
	/**
	 * 解析execel表格数据并入库	
	 * @param file
	 *            要解析入库的execel文件
	 */
	public void parseTaxStatistic( File file) {
		try {
			this.parseTaxStatistic( new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void parseTaxStatistic(FileInputStream in) {
		
		try {
			HSSFWorkbook  workBook = new HSSFWorkbook(in);			
			HSSFSheet sheet = workBook.getSheetAt(0);
			
			HSSFRow ssqRow =sheet.getRow(1);
			String ssq=ssqRow.getCell(0).getStringCellValue().trim().substring(4).trim();
			int rowNum= sheet.getPhysicalNumberOfRows();
			List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
			for(int i=3;i<rowNum;i++){
				HSSFRow row =sheet.getRow(i);
//				int cellNum=row.getPhysicalNumberOfCells();
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("ssq",ssq);
				
				HSSFCell cell0 = row.getCell(0);
				map.put("c0",cell0.getNumericCellValue());
				
//				HSSFCell cell1 = row.getCell(1);
//				map.put("c1", cell1.getStringCellValue());
				
				HSSFCell cell2 = row.getCell(2);
				map.put("c2", cell2.getNumericCellValue());
				
				HSSFCell cell3 = row.getCell(3);
				map.put("c3",cell3.getNumericCellValue());
				
				HSSFCell cell4 = row.getCell(4);
				map.put("c4", cell4.getNumericCellValue());
				
				HSSFCell cell5 = row.getCell(5);
				map.put("c5", cell5.getNumericCellValue());
				
				HSSFCell cell6 = row.getCell(6);
				map.put("c6", cell6.getNumericCellValue());
				
				HSSFCell cell7 = row.getCell(7);
				map.put("c7", cell7.getNumericCellValue());
				
				HSSFCell cell8 = row.getCell(8);
				map.put("c8",cell8.getNumericCellValue());
				
				HSSFCell cell9 = row.getCell(9);
				map.put("c9", cell9.getNumericCellValue());
				list.add(map);				
			}			
			this.updateTaxIncome(list, ssq);			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		
	}
	
	public void updateTaxIncome(List<Map<String, Object>> list,String ssq){		
		if(list!=null&&list.size()>0){
			for(Map<String, Object> map:list){
				dao.updateIncome(map);
			}
			dao.updateIncomeTotal(ssq);
		}
	}
	
	public static void main(String[] args) {
		new FileSystemXmlApplicationContext(
				new String[] { "src/applicationContext-public.xml",
						"src/applicationContext-bean.xml",
						"src/applicationContext-manage.xml" });
		File file=new File("C:\\Users\\user\\Desktop\\掌上税务二期\\报表\\组织收入非税收入总表.xls");
		TaxIncomeService service= (TaxIncomeService) SpringBeanUtil.getBean("taxIncomeService");
		service.parseTaxStatistic(file);
	}
	
	
	
	
	
	
	
	
}
