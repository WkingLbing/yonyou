/**
 * Copyright 2018-2020 yonyou.com.
 * All rights reserved.
 */
package com.yonyou.util;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * TODO
 * 
 * @Author:zhoujuan
 * @version $id:ImportExcelUtil.java,v0.1 2018年7月3日 下午6:57:43 zhoujuan Exp$
 */
public class ImportExcelUtil {

	/** 2003- 版本的excel */
	private final static String	EXCEL_2003L	= ".xls";
	/** 2007+ 版本的excel */
	private final static String	EXCEL_2003U	= ".xlsx";

	/**
	 * 获取IO流中的数据，组装成List<List<Object>>对象
	 * 
	 * @param in
	 * @param fileName
	 * @return
	 * @throws Exception
	 * @author: zhoujuan v0.1 2018年7月3日
	 */
	public List<List<Object>> getPartListByExcel(InputStream in, String fileName) throws Exception {

		List<List<Object>> list = null;
		// 创建Excel工作薄
		Workbook work = this.getWorkbook(in, fileName);
		if (null == work) {
			throw new Exception("创建Excel工作薄为空！");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;

		list = new ArrayList<List<Object>>();

		// 遍历Excel中所有的sheet
		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			// 遍历当前sheet中的所有行
			for (int j = sheet.getFirstRowNum(); j < sheet.getLastRowNum() + 1; j++) {
				row = sheet.getRow(j);
				if (row == null || row.getFirstCellNum() == j) {
					continue;
				}

				// 遍历所有的列
				List<Object> li = new ArrayList<Object>();
				for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					li.add(cell);
				}
				list.add(li);
			}
		}
		work.close();
		return list;

	}

	/**
	 * 
	 * 根据文件后缀，自适应上传文件的版本
	 * 
	 * @param inStr
	 * @param fileName
	 * @return
	 * @throws Exception
	 * @author: zhoujuan v0.1 2018年7月3日
	 */
	public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {

		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (EXCEL_2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr);
		} else if (EXCEL_2003U.equals(fileType)) {
			wb = new XSSFWorkbook(inStr);
		} else {
			throw new Exception("解析的文件格式有误！");
		}
		return wb;
	}

	/**
	 * 获得Cell内容
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {

		String value = "";
		if (cell != null) {
			// 以下是判断数据的类型
			switch (cell.getCellType()) {
			// 数字
			case HSSFCell.CELL_TYPE_NUMERIC:
				value = cell.getNumericCellValue() + "";
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					if (date != null) {
						value = new SimpleDateFormat("yyyy-MM-dd").format(date);
					} else {
						value = "";
					}
				} else {
					value = new DecimalFormat("0").format(cell.getNumericCellValue());
				}
				break;
			// 字符串
			case HSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			// 布尔值
			case HSSFCell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue() + "";
				break;
			// 公式
			case HSSFCell.CELL_TYPE_FORMULA:
				value = cell.getCellFormula() + "";
				break;
			// 空值
			case HSSFCell.CELL_TYPE_BLANK:
				value = "";
				break;
			// 故障
			case HSSFCell.CELL_TYPE_ERROR:
				value = "非法字符";
				break;
			default:
				value = "未知类型";
				break;
			}
		}
		return value.trim();
	}
	
	/**
	 * TODO 判断Excel中行是否为空，防止因样式空格等隐私导致空行被解析
	 * 
	 * @param row
	 * @return
	 * @author: xiao.yang v0.1 2019年1月22日
	 */
	public static boolean isNullRow(Row row) {

		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
				return false;
		}
		return true;
	}
	
	/**
	 * 
	 * 正则判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 * @author: liangqf v0.1 2018年12月19日
	 */
	public static boolean isNumeric(String str) {

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}
