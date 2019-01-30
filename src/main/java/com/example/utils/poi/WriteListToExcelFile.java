package com.example.utils.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description 导出Excel demo演示
 * @Author blake
 * @Date 2019-01-25 11:40
 * @Version 1.0
 */
public class WriteListToExcelFile {


    public static void writeCountryListToFile(String fileName, List<Country> countryList) throws Exception {
        Workbook workbook = null;

        if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new Exception("invalid file name, should be xls or xlsx");
        }

        Sheet sheet = workbook.createSheet("Countries");

        Iterator<Country> iterator = countryList.iterator();

        int rowIndex = 0;
        while (iterator.hasNext()) {
            Country country = iterator.next();
            Row row = sheet.createRow(rowIndex++);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(country.getName());
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(country.getCode());
        }

        //lets write the excel data to file now
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos); // 借助文件输出流将数据写入Excel工作表
        fos.close();
        System.out.println(fileName + " written successfully");
    }

    public static void main(String args[]) throws Exception {
        // ==================================
        List<Country> list = new ArrayList<>();

        Country country1 = new Country();
        country1.setName("中国");
        country1.setCode("CN");

        Country country2 = new Country();
        country2.setName("美国");
        country2.setCode("USA");

        list.add(country1);
        list.add(country2);

        WriteListToExcelFile.writeCountryListToFile("Countries.xls", list);
        // ==================================


//        Country country = new Country();
//        Field[] declaredFields = country.getClass().getDeclaredFields();
//
//        System.out.println("Country对象的属性个数：" + declaredFields.length);

    }

}
