package INPUT;

import EXCEPTIONS.NoSuchColumnEsception;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

public class InputFileWorker {
    public static ArrayList<String> getChildIdOfVirtualProducts(String virtualProduct){
        ArrayList<String> listOfChildId = new ArrayList<>();
        String[] splitInputValue = virtualProduct.split("\\[:&:\\]");

        for (String s: splitInputValue) {
            if (s.contains("productid[:=:]")){
                //s.replace("productid[:=:]", "");

                listOfChildId.add(s.replace("productid[:=:]", ""));

            }
        }
        HashSet<String> uniqueValues = new HashSet<>(listOfChildId);
        return new ArrayList<>(uniqueValues);
    }

   public static ArrayList<ArrayList<String>> getParentChildInfo(String filename, String columnTitleForSearch, String columnTitleForInfo){
        TreeMap<Row, String> excelContent = new TreeMap<>();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        try {
            excelContent = Excel.readInMapFromExcelByColumnTitle(filename, columnTitleForSearch);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int maxKeyRowNumber = maxKeyInMap(excelContent);
        for (Map.Entry<Row, String> entry: excelContent.entrySet()){
            ArrayList<String> resultForOneParent = new ArrayList<>();
            if (entry.getKey().getRowNum() < maxKeyRowNumber){
                int nextRowNumder = excelContent.higherEntry(entry.getKey()).getKey().getRowNum();
                try {
                    resultForOneParent = Excel.readFromExcelByColumnTitleFromTo(filename, columnTitleForInfo, entry.getKey().getRowNum()+1, nextRowNumder);
                    result.add(resultForOneParent);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchColumnEsception noSuchColumnEsception) {
                    noSuchColumnEsception.printStackTrace();
                }
            } else {
                try {
                    resultForOneParent = Excel.readFromExcelByColumnTitleFrom(filename, columnTitleForInfo, entry.getKey().getRowNum()+1);
                    result.add(resultForOneParent);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchColumnEsception noSuchColumnEsception) {
                    noSuchColumnEsception.printStackTrace();
                }
            }
        }




        return result;
    }

    /**
     * Возвращает массив массивов стрингов из информации в ячейках колонки columnTitleForInfo, для всех парентов. Для конкретного парента возвращяет массив стрингов
     * на основании чайлд ид из виртуального продукта (columnTitleForSearch), для данного парента, которые есть в столбце columnForValidation. Первый элемент в массиве -
     * информация с строки парента колонки columnTitleForInfo, дальше - информация с строк чайлдов колонки columnTitleForInfo
     * @param filename - path to .xls file for reading
     * @param columnTitleForSearch - name of column which is used for searching Virtual Product structure (typical column name - Virtual Products)
     * @param columnTitleForInfo - name of column which is used for return information
     * @param columnForValidation  - name of column which is used for validation. Information in this column (for specific row). (typical column name - Product ID)
     * have to be in list of Child IDs from columnTitleForSearch column
     * @return ArrayList<ArrayList<String>> with information from columnTitleForInfo column for each parent in .xls file
     */

    public static ArrayList<ArrayList<String>> getParentChildInfoWithValidationByChildID(String filename, String columnTitleForSearch, String columnTitleForInfo, String columnForValidation){
        TreeMap<Row, String> excelContent = new TreeMap<>();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        int columnForInfoNumber = -1;
        try {
            columnForInfoNumber = Excel.getColumnIndexByStringByFilename(filename, columnTitleForInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            excelContent = Excel.readInMapFromExcelByColumnTitle(filename, columnTitleForSearch);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int maxKeyRowNumber = maxKeyInMap(excelContent);
        for (Map.Entry<Row, String> entry: excelContent.entrySet()){
            ArrayList<String> resultForOneParent = new ArrayList<>();
            if (entry.getKey().getRowNum() < maxKeyRowNumber){
                int nextRowNumder = excelContent.higherEntry(entry.getKey()).getKey().getRowNum();
                try {
                    String stringOfChildID = entry.getValue();
                    ArrayList<String> listOfChildID = getChildIdOfVirtualProducts(stringOfChildID);
                    resultForOneParent = Excel.readFromExcelByColumnTitleFromToWithValidationByChildID(filename, columnForValidation, columnTitleForInfo, entry.getKey().getRowNum()+1, nextRowNumder, listOfChildID);
                    resultForOneParent.add(0, entry.getKey().getCell(columnForInfoNumber).getStringCellValue());
                    result.add(resultForOneParent);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchColumnEsception noSuchColumnEsception) {
                    noSuchColumnEsception.printStackTrace();
                }
            } else {
                try {
                    String stringOfChildID = entry.getValue();
                    ArrayList<String> listOfChildID = getChildIdOfVirtualProducts(stringOfChildID);
                    resultForOneParent = Excel.readFromExcelByColumnTitleFromWithValidationByChildID(filename, columnForValidation, columnTitleForInfo, entry.getKey().getRowNum()+1, listOfChildID);
                    resultForOneParent.add(0, entry.getKey().getCell(columnForInfoNumber).getStringCellValue());
                    result.add(resultForOneParent);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchColumnEsception noSuchColumnEsception) {
                    noSuchColumnEsception.printStackTrace();
                }
            }
        }




        return result;
    }

    public static int maxKeyInMap(TreeMap<Row, String> map){
        return map.lastEntry().getKey().getRowNum();
    }


}
