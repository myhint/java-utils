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


    public static void writeCountryListToFile(String fileName, List<Country> countryList)
            throws Exception {

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

        // lets write the excel data to file now
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

        Country country3 = new Country();
        country3.setName("英国");
        country3.setCode("EN");

        list.add(country1);
        list.add(country2);
        list.add(country3);

        // TODO 本地测试时，务必指定文件的保存路径。这里的 /Origin/dev/code/githubmine/java-utils/ 是当前项目根目录的绝对路径，请自行调整
        WriteListToExcelFile.writeCountryListToFile("/Origin/dev/code/githubmine/java-utils/OutCountries.xls", list);

        // ==================================

    }

}
