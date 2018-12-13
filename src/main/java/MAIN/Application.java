package MAIN;

import INPUT.Excel;
import TEST_LOGIC.ParentPriceRangeChecker;
import INPUT.InputFileWorker;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        HSSFWorkbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Test_Results");


       ArrayList<ArrayList<String>> urlListOfLists = InputFileWorker.getParentChildInfoWithValidationByChildID("C:\\Users\\Maxim\\Desktop\\test_test.xls", "Virtual Products", "URL", "Product ID");
        for (int i = 0; i< urlListOfLists.size(); i++) {

            ArrayList<String> resultTestList = ParentPriceRangeChecker.compareParentPriceWithItsChildren(urlListOfLists.get(i));
            System.out.println(resultTestList);
            String output = "";
            for (int k = 0; k< resultTestList.size(); k++) {
                output = output + " @ " + resultTestList.get(k);
                Row row = sheet.createRow(i+1);
                Cell cell = row.createCell(0);
                cell.setCellValue(output);
            }
        }
        Excel.WriteHSSFWorkbook("C:\\Users\\Maxim\\Desktop\\Test_res.xls", wb);
    }
}
