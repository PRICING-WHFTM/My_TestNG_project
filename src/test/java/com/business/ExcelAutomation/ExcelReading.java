package com.business.ExcelAutomation;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReading {

    /* Read all values of the Excel file */

    @Test
    public void readExcel() throws Exception {
        String path = "./src/test/resources/Countries.xlsx";
        FileInputStream fis = new FileInputStream(path);
        //Open the Workbook interface -  We don't have to mention XSSF, HSSF or any type pf WorkBook
        Workbook workbook = WorkbookFactory.create(fis);
        // Mention the sheet in excel file
        Sheet worksheet = workbook.getSheetAt(0);
        // Find total count of rows in worksheet
        int rowsCount = worksheet.getLastRowNum();
        // 2. Use for loop to read all rows and their cells //
        for (int i = 1; i <= rowsCount; i++) {
              System.out.println("Country ==> " + worksheet.getRow(i).getCell(0) +
                              " | Capital ==> " + worksheet.getRow(i).getCell(1) +
                              " | Continent ==>"+ worksheet.getRow(i).getCell(2));
        }


        workbook.close();
        fis.close();
    }


}






