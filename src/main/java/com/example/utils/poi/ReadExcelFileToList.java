package com.example.utils.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description Excel文件导入 demo演示
 * @Author blake
 * @Date 2019-01-25 11:50
 * @Version 1.0
 */
public class ReadExcelFileToList {

    private static final Logger logger = LoggerFactory.getLogger(ReadExcelFileToList.class);

    /**
     * @return java.util.List<com.example.utils.poi.Country>
     * @throws
     * @description 读取Excel文件中的数据
     * @params [fileName]
     */
    public static List<Country> readExcelData(String fileName) {

        List<Country> countryList = new ArrayList<Country>();

        try {
            // 文件输入流实例的初始化
            FileInputStream fis = new FileInputStream(fileName);

            // 按文件类型，初始化workbook
            Workbook workbook = initWorkbookByFileType(fileName, fis);

            // 获取Excel文件中有效的sheet数量
            int numberOfSheets = workbook.getNumberOfSheets();

            for (int indexOfSheets = 0; indexOfSheets < numberOfSheets; indexOfSheets++) {

                // 获取索引值对应的sheet
                Sheet sheet = workbook.getSheetAt(indexOfSheets);

                for (Row rowCells : sheet) {
                    String name = "";
                    String code = "";

                    // 获取行数据Row的迭代器
                    Iterator<Cell> cellIterator = rowCells.cellIterator();

                    while (cellIterator.hasNext()) {

                        // 获取单元格Cell实例
                        Cell cell = cellIterator.next();

                        // 校验Cell单元格数据类型，并依次做出相应的处理
                        switch (cell.getCellTypeEnum().getCode()) {

                            case Cell.CELL_TYPE_STRING:

                                if (code.equalsIgnoreCase("")) {
                                    code = cell.getStringCellValue().trim();
                                } else if (name.equalsIgnoreCase("")) {
                                    name = cell.getStringCellValue().trim();
                                } else {
                                    // 异常数据，无需做任何处理
                                    System.out.println("abnormal data::" + cell.getStringCellValue());
                                }
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                System.out.println("abnormal data::" + cell.getNumericCellValue());
                        }
                    }

                    Country country = new Country(name, code);
                    countryList.add(country);
                }
            }

            // 关闭文件输入流
            fis.close();

        } catch (IOException e) {
            logger.error("============ 异常信息：[{}] ============ ", e.getMessage());
            logger.error(" ============ [{}] ============ ", e);
        }

        return countryList;
    }

    /**
     * @return org.apache.poi.ss.usermodel.Workbook
     * @throws
     * @description 初始化workbook
     * @params [fileName, fis]
     */
    private static Workbook initWorkbookByFileType(String fileName, FileInputStream fis) throws IOException {

        Workbook workbook = null;
        if (fileName.toLowerCase().endsWith("xlsx")) {
            // 2007版本的Excel
            workbook = new XSSFWorkbook(fis);
        } else if (fileName.toLowerCase().endsWith("xls")) {
            // 2003版本的Excel
            workbook = new HSSFWorkbook(fis);
        }
        return workbook;
    }

    public static void main(String args[]) {

        List<Country> list = readExcelData("countries.xls");

        for (Country country : list) {
            System.out.println("Record ：" + country);
        }

    }

}


