package INPUT;

import EXCEPTIONS.NoSuchColumnEsception;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class Excel {
    public static Workbook WriteHSSFWorkbook(String filename, Workbook wb){
        FileOutputStream fos;
        {try {
            fos = new FileOutputStream(filename);
            wb.write(fos);
            fos.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        return wb;
    }
    public static ArrayList<String> ReadFromExcel(String filename) throws IOException {
        InputStream inputStream = new FileInputStream(filename);
        HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inputStream));
        ArrayList<String> ExcelContent = new ArrayList<String>();
        Sheet sheet1 = wb.getSheetAt(0);
        for (Row row : sheet1){
            for (Cell cell : row){

                ExcelContent.add(cell.getStringCellValue());
            }
        }
        return ExcelContent;
    }
    public static int getColumnIndexByString(Sheet sheet, String columnTitle){
        int columnIndex;
        Row titleRow = sheet.getRow(0);
        Iterator<Cell> cellIterator = titleRow.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if(cell.getCellType()==Cell.CELL_TYPE_STRING){
                String text = cell.getStringCellValue();
                if (columnTitle.equals(text)) {
                    columnIndex=cell.getColumnIndex();
                    return columnIndex;
                    //break;
                }
            }
        }

        //return new NoSuchColumnEsception();
        return -1;
    }

    public static ArrayList<String> readFromExcelByColumnTitle(String filename, String columnTitle) throws IOException, NoSuchColumnEsception {
        InputStream inputStream = new FileInputStream(filename);
        HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inputStream));
        ArrayList<String> ExcelContent = new ArrayList<String>();
        Sheet sheet = wb.getSheetAt(0);
        int columnIndex = getColumnIndexByString(sheet, columnTitle);
        if (columnIndex==-1){
            throw new NoSuchColumnEsception();
        }
        for (Row row : sheet){
           Cell cell = row.getCell(columnIndex);
                if (cell!=null && row.getRowNum()!=0){
                    ExcelContent.add(cell.getStringCellValue());
                }
        }
        wb.close();
        return ExcelContent;
    }

    /**
     * get data from excel file
     * input: filename - path to file, columnTitle -
     * output: Row and Text find in specific column (by columnTitle). First cell and empty cells are ignored
     * output sorted by ascending
     */
    public static TreeMap<Row, String> readInMapFromExcelByColumnTitle(String filename, String columnTitle) throws IOException {
        InputStream inputStream = new FileInputStream(filename);
        HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inputStream));
        TreeMap<Row, String> excelContent = new TreeMap<Row, String>();
        Sheet sheet = wb.getSheetAt(0);
        int columnIndex = getColumnIndexByString(sheet, columnTitle);
        for (Row row : sheet){
            Cell cell = row.getCell(columnIndex);
            if (cell!=null && row.getRowNum()!=0){
                excelContent.put(cell.getRow(), cell.getStringCellValue());
            }
        }
        wb.close();
        return excelContent;
    }

    public static ArrayList<String> readFromExcelByColumnTitleFromTo(String filename, String columnTitle, int from, int to) throws IOException, NoSuchColumnEsception {
        InputStream inputStream = new FileInputStream(filename);
        HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inputStream));
        ArrayList<String> ExcelContent = new ArrayList<String>();
        Sheet sheet = wb.getSheetAt(0);
        int columnIndex = getColumnIndexByString(sheet, columnTitle);
        if (columnIndex==-1){
            throw new NoSuchColumnEsception();
        }
        for (Row row : sheet){
            Cell cell = row.getCell(columnIndex);
            if (cell!=null && row.getRowNum()>=from && row.getRowNum()<to){
                ExcelContent.add(cell.getStringCellValue());
            }
        }
        wb.close();
        return ExcelContent;
    }

    public static ArrayList<String> readFromExcelByColumnTitleFrom(String filename, String columnTitle, int from) throws IOException, NoSuchColumnEsception {
        InputStream inputStream = new FileInputStream(filename);
        HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inputStream));
        ArrayList<String> ExcelContent = new ArrayList<String>();
        Sheet sheet = wb.getSheetAt(0);
        int columnIndex = getColumnIndexByString(sheet, columnTitle);
        if (columnIndex==-1){
            throw new NoSuchColumnEsception();
        }
        for (Row row : sheet){
            Cell cell = row.getCell(columnIndex);
            if (cell!=null && row.getRowNum()>=from){
                ExcelContent.add(cell.getStringCellValue());
            }
        }
        wb.close();
        return ExcelContent;
    }

    public static int getColumnIndexByStringByFilename(String filename, String columnTitle) throws IOException {
        int columnIndex;
        InputStream inputStream = new FileInputStream(filename);
        HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inputStream));
        Sheet sheet = wb.getSheetAt(0);
        Row titleRow = sheet.getRow(0);
        Iterator<Cell> cellIterator = titleRow.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if(cell.getCellType()==Cell.CELL_TYPE_STRING){
                String text = cell.getStringCellValue();
                if (columnTitle.equals(text)) {
                    columnIndex=cell.getColumnIndex();
                    return columnIndex;
                    //break;
                }
            }
        }
        wb.close();
        //return new NoSuchColumnEsception();
        return -1;
    }

    public static ArrayList<String> readFromExcelByColumnTitleFromToWithValidationByChildID(String filename, String columnForValidation, String columnTitle, int from, int to, ArrayList<String> childIdList) throws IOException, NoSuchColumnEsception {
        InputStream inputStream = new FileInputStream(filename);
        HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inputStream));
        ArrayList<String> ExcelContent = new ArrayList<String>();
        Sheet sheet = wb.getSheetAt(0);
        int columnIndex = getColumnIndexByString(sheet, columnTitle);
        int columnForValidationIndex = getColumnIndexByString(sheet, columnForValidation);
        if (columnIndex==-1){
            throw new NoSuchColumnEsception();
        }
        for (Row row : sheet){
            Cell cell = row.getCell(columnIndex);
            Cell cellForValidation = row.getCell(columnForValidationIndex);
            if (cell!=null && row.getRowNum()>=from && row.getRowNum()<to && childIdList.contains(cellForValidation.getStringCellValue()) ){
                ExcelContent.add(cell.getStringCellValue());
            }
        }
        wb.close();
        return ExcelContent;
    }
    public static ArrayList<String> readFromExcelByColumnTitleFromWithValidationByChildID(String filename, String columnForValidation, String columnTitle, int from, ArrayList<String> childIdList) throws IOException, NoSuchColumnEsception {
        InputStream inputStream = new FileInputStream(filename);
        HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inputStream));
        ArrayList<String> ExcelContent = new ArrayList<String>();
        Sheet sheet = wb.getSheetAt(0);
        int columnIndex = getColumnIndexByString(sheet, columnTitle);
        int columnForValidationIndex = getColumnIndexByString(sheet, columnForValidation);
        if (columnIndex==-1){
            throw new NoSuchColumnEsception();
        }
        for (Row row : sheet){
            Cell cell = row.getCell(columnIndex);
            Cell cellForValidation = row.getCell(columnForValidationIndex);
            if (cell!=null && row.getRowNum()>=from   && childIdList.contains(cellForValidation.getStringCellValue()) ){
                ExcelContent.add(cell.getStringCellValue());
            }
        }
        wb.close();
        return ExcelContent;
    }
}
