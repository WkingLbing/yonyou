package com.yonyou.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class PoiUtil {
	public static void main(String[] args) throws Exception 
    {        
        getDataFromExcel("F:"+ File.separator +"1234.xls");
    }
		@SuppressWarnings("resource")
		public static List getDataFromExcel(String filePath) throws Exception {
            //String filePath = "F:\\123.xlsx";
            if(!filePath.endsWith(".xls")&&!filePath.endsWith(".xlsx")) //判断是否为excel类型文件
            {
                System.out.println("文件不是excel类型");
            }
            FileInputStream fis =null;
            Workbook wookbook = null;
            fis = new FileInputStream(filePath);  //获取一个绝对地址的流
            wookbook = new HSSFWorkbook(fis);//得到工作簿//2003版本的excel，用.xls结尾
            Sheet sheet = wookbook.getSheetAt(0);//得到一个工作表
            int totalRowNum = sheet.getLastRowNum();
            String name = "";
            String latitude = "";
            for(int i = 1 ; i <= totalRowNum ; i++)
            {
                Row row = sheet.getRow(i);//获得第i行对象
                Cell cell = row.getCell(0);//获得获得第i行第0列的 String类型对象
                cell.setCellType(Cell.CELL_TYPE_STRING);
                name = cell.getStringCellValue().toString();
                cell = row.getCell((short)1); //获得一个数字类型的数据
                latitude =cell.getStringCellValue();
                System.out.println("编号："+name+",姓名："+latitude);
            }
			return null;
    }
} 