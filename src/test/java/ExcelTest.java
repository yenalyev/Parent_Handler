import INPUT.Excel;
import EXCEPTIONS.NoSuchColumnEsception;
import INPUT.InputFileWorker;
import org.apache.poi.ss.usermodel.Row;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

class ExcelTest {
    Excel excelObject = new Excel();
    InputFileWorker inputFileWorkerObhect = new InputFileWorker();
    @Test
    void readFromExcelByColumnTitle() {
        ArrayList<String> list = new ArrayList<>();
        try {
            list = Excel.readFromExcelByColumnTitle("C:\\Users\\Maxim\\Desktop\\test1.xls", "Virtual Products");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchColumnEsception nsce){
            nsce.printStackTrace();
        }
        for (String s: list) {
            System.out.println(s);
        }
    }

    @Test
    void readInMapFromExcelByColumnTitle(){
        TreeMap<Row, String> map = new TreeMap<Row, String>();
        try {
            map = excelObject.readInMapFromExcelByColumnTitle("C:\\Users\\Maxim\\Desktop\\test1.xls", "Virtual Products");
        } catch (IOException e){
            e.printStackTrace();
        }
        for(Map.Entry<Row, String> entry: map.entrySet()){
            // entry.getKey().getRowNum()+1 becouse excel counts from 1, but POI from 0
            System.out.println(entry.getKey().getRowNum()+1 + " / " + inputFileWorkerObhect.getChildIdOfVirtualProducts(entry.getValue()));
        }
    }
}