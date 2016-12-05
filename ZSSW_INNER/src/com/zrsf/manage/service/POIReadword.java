package com.zrsf.manage.service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Paragraph;   
import org.apache.poi.hwpf.usermodel.Table;   
import org.apache.poi.hwpf.usermodel.TableCell;   
import org.apache.poi.hwpf.usermodel.TableIterator;   
import org.apache.poi.hwpf.usermodel.TableRow;   
import org.apache.struts2.ServletActionContext;


public class POIReadword {

	/**
	 * �س���ASCII��
	 */
	private static final short ENTER_ASCII = 13;

	/**
	 * �ո��ASCII��
	 */
	private static final short SPACE_ASCII = 32;

	/**
	 * ˮƽ�Ʊ��ASCII��
	 */
	private static final short TABULATION_ASCII = 9;

	public static String htmlText = "";
	public static String htmlTextTbl = "";
	public static int counter = 0;
	public static int beginPosi = 0;
	public static int endPosi = 0;
	public static int beginArray[];
	public static int endArray[];
	public static String htmlTextArray[];
	public static boolean tblExist = false;


	

	/**
	 * 
	 * @param fileName
	 * @throws Exception
	 */

	public  String getWordAndStyle(File file,String filename) throws Exception {
		FileInputStream in = new FileInputStream(file);
		HWPFDocument doc = new HWPFDocument(in);
		Range rangetbl = doc.getRange();// �õ��ĵ��Ķ�ȡ��Χ
		TableIterator it = new TableIterator(rangetbl);
		int num = 100;

		beginArray = new int[num];
		endArray = new int[num];
		htmlTextArray = new String[num];

		// ȡ���ĵ����ַ������
		int length = doc.characterLength();
		// ����ͼƬ����
		PicturesTable pTable = doc.getPicturesTable();

		htmlText = "<html><head><title>"
				+ doc.getSummaryInformation().getTitle()
				+ "</title></head><body>";
		// ������ʱ�ַ�,�ü����ж�һ���ַ��Ƿ������ͬ��ʽ
		if (it.hasNext()) {
			readTable(it, rangetbl);
		}
		int cur = 0;

		String tempString = "";
		for (int i = 0; i < length - 1; i++) {
			// ��ƪ���µ��ַ�ͨ��һ�����ַ�����ж�,rangeΪ�õ��ĵ��ķ�Χ
			Range range = new Range(i, i + 1, doc);
			CharacterRun cr = range.getCharacterRun(0);
			if (tblExist) {
				if (i == beginArray[cur]) {
					htmlText += tempString + htmlTextArray[cur];
					tempString = "";
					i = endArray[cur] - 1;
					cur++;
					continue;
				}
			}
			if (pTable.hasPicture(cr)) {
				htmlText += tempString;
				// ��дͼƬ
				readPicture(pTable, cr);
				tempString = "";
			} else {

				Range range2 = new Range(i + 1, i + 2, doc);
				// �ڶ����ַ�
				CharacterRun cr2 = range2.getCharacterRun(0);
				char c = cr.text().charAt(0);

				// �ж��Ƿ�Ϊ�س���
				if (c == ENTER_ASCII) {
					tempString += "<br/>";

				}
				// �ж��Ƿ�Ϊ�ո��
				else if (c == SPACE_ASCII)
					tempString += "&nbsp;";
				// �ж��Ƿ�Ϊˮƽ�Ʊ��
				else if (c == TABULATION_ASCII)
					tempString += " &nbsp;&nbsp;&nbsp;";
				// �Ƚ�ǰ��2���ַ��Ƿ������ͬ�ĸ�ʽ
				boolean flag = compareCharStyle(cr, cr2);
				if (flag)
					tempString += cr.text();
				else {
					String fontStyle = "<span style=\"font-family:"
							+ cr.getFontName() + ";font-size:"
							+ cr.getFontSize() / 2 + "pt;";

					if (cr.isBold())
						fontStyle += "font-weight:bold;";
					if (cr.isItalic())
						fontStyle += "font-style:italic;";

					htmlText += fontStyle + "\" mce_style=\"font-family:"
							+ cr.getFontName() + ";font-size:"
							+ cr.getFontSize() / 2 + "pt;";

					if (cr.isBold())
						fontStyle += "font-weight:bold;";
					if (cr.isItalic())
						fontStyle += "font-style:italic;";

					htmlText += fontStyle + "\">" + tempString + cr.text()
							+ "</span>";
					tempString = "";
				}
			}
		}

		htmlText += tempString + "</body></html>";
		return writeFile(htmlText,filename);
	}

	/**
	 * ��д�ĵ��еı��
	 * 
	 * @param pTable
	 * @param cr
	 * @throws Exception
	 */
	public static void readTable(TableIterator it, Range rangetbl)
			throws Exception {

		htmlTextTbl = "";
		// ����ĵ��еı��

		counter = -1;
		while (it.hasNext()) {
			tblExist = true;
			htmlTextTbl = "";
			Table tb = (Table) it.next();
			beginPosi = tb.getStartOffset();
			endPosi = tb.getEndOffset();

			counter = counter + 1;
			// ����У�Ĭ�ϴ�0��ʼ
			beginArray[counter] = beginPosi;
			endArray[counter] = endPosi;

			htmlTextTbl += "<table border>";
			for (int i = 0; i < tb.numRows(); i++) {
				TableRow tr = tb.getRow(i);
				htmlTextTbl += "<tr>";
				// ����У�Ĭ�ϴ�0��ʼ
				for (int j = 0; j < tr.numCells(); j++) {
					TableCell td = tr.getCell(j);// ȡ�õ�Ԫ��
					int cellWidth = td.getWidth();
					// ȡ�õ�Ԫ�������
					for (int k = 0; k < td.numParagraphs(); k++) {
						Paragraph para = td.getParagraph(k);
						String s = para.text().toString().trim();
						if (s == "") {
							s = " ";
						}
						htmlTextTbl += "<td width=" + cellWidth + ">" + s
								+ "</td>";
					} // end for
				} // end for
			} // end for
			htmlTextTbl += "</table>";
			htmlTextArray[counter] = htmlTextTbl;

		} // end while
	}

	/**
	 * ��д�ĵ��е�ͼƬ
	 * 
	 * @param pTable
	 * @param cr
	 * @throws Exception
	 */
	public static void readPicture(PicturesTable pTable, CharacterRun cr)
			throws Exception {
		// ��ȡͼƬ
		Picture pic = pTable.extractPicture(cr, false);
		// ����POI�����ͼƬ�ļ���
		String afileName = pic.suggestFullFileName();
		OutputStream out = new FileOutputStream(new File(
				"http://61.177.61.251:8222/shuixin/files/" + File.separator + afileName));
		pic.writeImageContent(out);
		htmlText += "<img src=\"http://61.177.61.251:8222/shuixin/files/" + afileName
				+ "\" mce_src=\"http://61.177.61.251:8222/shuixin/files/" + afileName
				+ "\"/>";
	}

	public static boolean compareCharStyle(CharacterRun cr1, CharacterRun cr2) {
		boolean flag = false;
		if (cr1.isBold() == cr2.isBold() && cr1.isItalic() == cr2.isItalic()
				&& cr1.getFontName().equals(cr2.getFontName())
				&& cr1.getFontSize() == cr2.getFontSize()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * д�ļ�
	 * 
	 * @param s
	 */
	public String writeFile(String s,String fileFileName) {
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


