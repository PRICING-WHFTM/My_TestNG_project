package com.business.ExcelAutomation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelWriting {

    @Test
    public void excelWriting() throws Exception {
        String path = "./src/test/resources/ExcelWriting.xlsx";
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet worksheet = workbook.getSheetAt(0);   //can be sheetName too.
        // Create new Column(cell) in CERTAIN index.  -->  Row 0 Column 2 index
        Cell newColIndex = worksheet.getRow(0).getCell(2);
        if (newColIndex == null) {     // Telling, if the cell not exist create it.
           newColIndex= worksheet.getRow(0).createCell(2);
        }
        // Give the name to new column by giving the value created cell.
        newColIndex.setCellValue("Nationality");
        // Passing the value to row 1 column 2  (index)
        // Note : 1st must be created cell itself then can be added value to that
        Cell nationality1 = worksheet.getRow(1).createCell(2);
        nationality1.setCellValue("American");
        // Save the changes in excel file
        // Open the file with output-stream
        FileOutputStream fos = new FileOutputStream(path);
        // Write changes and Save excel file.
        workbook.write(fos);

        //close the file
        fos.close();
        workbook.close();
        fis.close();


    }
}
