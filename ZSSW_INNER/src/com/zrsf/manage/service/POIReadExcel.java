package com.zrsf.manage.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;

public class POIReadExcel {

	public  String ReadExcke(File file,String fileName)
	{
		String Url=null;
		try
		{
		POIReadExcel poire = new POIReadExcel();
		InputStream is = new FileInputStream(file);
		POIFSFileSystem fs = new POIFSFileSystem(is);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		Url=writeFile(poire.getExcelInfo(wb),fileName);
		is.close();
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return Url;
	}
	public String getExcelInfo(HSSFWorkbook wb) throws Exception {

		StringBuffer sb = new StringBuffer();

		Sheet sheet = wb.getSheetAt(0);

		int lastRowNum = sheet.getLastRowNum();

		Map<String, String> map[] = getRowSpanColSpanMap(sheet);

		sb.append("<table border='0' cellspacing='1' width='100%'>");

		HSSFRow row = null;

		HSSFCell cell = null;

		for (int rowNum = sheet.getFirstRowNum(); rowNum < lastRowNum+1; rowNum++) {

			row = (HSSFRow) sheet.getRow(rowNum);

			if (row == null) {

				sb.append("<tr><td > &nbsp;</td></tr>");

				continue;
			}

			sb.append("<tr>");

			int lastColNum = row.getLastCellNum();

			for (int colNum = 0; colNum < lastColNum; colNum++) {

				cell = row.getCell(colNum);

				if (cell == null) {

					sb.append("<td>&nbsp;</td>");

					continue;
				}

				String stringValue = getCellValue(cell);

				if (map[0].containsKey(rowNum + "," + colNum)) {

					String pointString = map[0].get(rowNum + "," + colNum);

					map[0].remove(rowNum + "," + colNum);

					int bottomeRow = Integer.valueOf(pointString.split(",")[0]);

					int bottomeCol = Integer.valueOf(pointString.split(",")[1]);

					int rowSpan = bottomeRow - rowNum + 1;

					int colSpan = bottomeCol - colNum + 1;

					sb.append("<td rowspan= '" + rowSpan + "' colspan= '"
							+ colSpan + "' ");

				} else if (map[1].containsKey(rowNum + "," + colNum)) {

					map[1].remove(rowNum + "," + colNum);

					continue;

				} else {

					sb.append("<td ");
				}

				HSSFCellStyle cellStyle = cell.getCellStyle();

				if (cellStyle != null) {

					short alignment = cellStyle.getAlignment();

					sb.append("align='" + convertAlignToHtml(alignment) + "' ");

					short verticalAlignment = cellStyle.getVerticalAlignment();

					sb.append("valign='"
							+ convertVerticalAlignToHtml(verticalAlignment)
							+ "' ");

					HSSFFont hf = cellStyle.getFont(wb);

					short boldWeight = hf.getBoldweight();

					short fontColor = hf.getColor();

					sb.append("style='");

					HSSFPalette palette = wb.getCustomPalette(); // ��HSSFPalette���������ɫ�Ĺ�ʱ�׼��ʽ

					HSSFColor hc = palette.getColor(fontColor);

					sb.append("font-weight:" + boldWeight + ";"); // ����Ӵ�

					sb.append("font-size: " + hf.getFontHeight() / 2 + "%;"); // �����С

					String fontColorStr = convertToStardColor(hc);

					if (fontColorStr != null && !"".equals(fontColorStr.trim())) {

						sb.append("color:" + fontColorStr + ";"); // ������ɫ
					}

					short bgColor = cellStyle.getFillForegroundColor();

					hc = palette.getColor(bgColor);

					String bgColorStr = convertToStardColor(hc);

					if (bgColorStr != null && !"".equals(bgColorStr.trim())) {

						sb.append("background-color:" + bgColorStr + ";"); // ������ɫ
					}

					short borderColor = cellStyle.getBottomBorderColor();

					hc = palette.getColor(borderColor);

					String borderColorStr = convertToStardColor(hc);

					if (borderColorStr != null
							&& !"".equals(borderColorStr.trim())) {

						sb.append("border-color:" + borderColorStr + ";"); // �߿���ɫ
					}

					sb.append("' ");
				}

				sb.append(">");

				if (stringValue == null || "".equals(stringValue.trim())) {

					sb.append(" &nbsp; ");
				} else {

					sb.append(stringValue.replace(String.valueOf((char) 160),
							"&nbsp;"));

				}

				sb.append("</td>");

			}

			sb.append("</tr>");
		}

		sb.append("</table>");

		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	private Map<String, String>[] getRowSpanColSpanMap(Sheet sheet) {

		Map<String, String> map0 = new HashMap<String, String>();
		Map<String, String> map1 = new HashMap<String, String>();

		int mergedNum = sheet.getNumMergedRegions();

		CellRangeAddress range = null;

		for (int i = 0; i < mergedNum; i++) {

			range = sheet.getMergedRegion(i);

			int topRow = range.getFirstRow();

			int topCol = range.getFirstColumn();

			int bottomRow = range.getLastRow();

			int bottomCol = range.getLastColumn();

			map0.put(topRow + "," + topCol, bottomRow + "," + bottomCol);

			int tempRow = topRow;

			while (tempRow <= bottomRow) {

				int tempCol = topCol;

				while (tempCol <= bottomCol) {

					map1.put(tempRow + "," + tempCol, "");

					tempCol++;
				}

				tempRow++;
			}

			map1.remove(topRow + "," + topCol);

		}

		Map[] map = { map0, map1 };

		return map;
	}

	private String convertAlignToHtml(short alignment) {

		String align = "left";

		switch (alignment) {

		case HSSFCellStyle.ALIGN_LEFT:
			align = "left";
			break;
		case HSSFCellStyle.ALIGN_CENTER:
			align = "center";
			break;
		case HSSFCellStyle.ALIGN_RIGHT:
			align = "right";
			break;

		default:
			break;
		}

		return align;
	}

	private String convertVerticalAlignToHtml(short verticalAlignment) {

		String valign = "middle";

		switch (verticalAlignment) {

		case HSSFCellStyle.VERTICAL_BOTTOM:
			valign = "bottom";
			break;
		case HSSFCellStyle.VERTICAL_CENTER:
			valign = "center";
			break;
		case HSSFCellStyle.VERTICAL_TOP:
			valign = "top";
			break;
		default:
			break;
		}

		return valign;
	}

	private String convertToStardColor(HSSFColor hc) {

		StringBuffer sb = new StringBuffer("");

		if (hc != null) {

			if (HSSFColor.AUTOMATIC.index == hc.getIndex()) {

				return null;
			}

			sb.append("#");

			for (int i = 0; i < hc.getTriplet().length; i++) {

				sb.append(fillWithZero(Integer.toHexString(hc.getTriplet()[i])));
			}
		}

		return sb.toString();
	}

	private String fillWithZero(String str) {

		if (str != null && str.length() < 2) {

			return "0" + str;
		}
		return str;
	}

	private String getCellValue(HSSFCell cell) {
	
		switch (cell.getCellType()) {

		case HSSFCell.CELL_TYPE_NUMERIC:
			if(cell.getCellStyle().getDataFormat()==31||cell.getCellStyle().getDataFormat()==14)
			{	 Date date = new Date() ;
					date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
					SimpleDateFormat SDF=new SimpleDateFormat("yyyy年MM月dd日");
		    

				return SDF.format(date);
			}
			else if(cell.getCellStyle().getDataFormat()==178)
			{
				 Date date = new Date() ;
					date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
					SimpleDateFormat SDF=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
		    

				return SDF.format(date);
			}
			else
			{
			cell.setCellType (HSSFCell.CELL_TYPE_STRING);
			return cell.getRichStringCellValue().toString();
			
			}

		case HSSFCell.CELL_TYPE_STRING:

			return cell.getStringCellValue();

		case HSSFCell.CELL_TYPE_FORMULA:
			
		 return cell.getCellFormula();

		default:
			return "";
		}
	}
	public  String writeFile(String s,String fileFileName) {
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		String filePath = ServletActionContext.getServletContext().getRealPath("files");
		try {
			File file = new File(filePath,fileFileName+".html");
			fos = new FileOutputStream(file);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(s);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fos != null)
					fos.close();
			} catch (IOException ie) {
			}
		}
		return fileFileName+".html";
	}
}
