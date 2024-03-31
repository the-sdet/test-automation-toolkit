package io.github.the_sdet.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.the_sdet.common.CommonUtils.EMPTY_STRING;

/**
 * This class handles all Excel related Utilities and Helper methods
 *
 * @author Pabitra Swain (contact.the.sdet@gmail.com)
 */
@SuppressWarnings("unused")
public class ExcelUtils {
  /**
   * Retrieves the headers from Excel.
   *
   * @param filepath
   *            filename with path
   * @param sheetName
   *            name of the sheet
   * @return List of header strings
   * @throws IOException
   *             if an I/O error occurs
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static List<String> getHeaderList(String filepath, String sheetName) throws IOException {
    ArrayList<String> dataList = new ArrayList<>();
    FileInputStream fis = new FileInputStream(filepath);
    XSSFWorkbook wb = new XSSFWorkbook(fis);
    XSSFSheet sheet = wb.getSheet(sheetName);
    for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
      dataList.add(getStringValue(sheet, 0, i));
    }
    return dataList;
  }

  /**
   * This method fetches entries present in a particular column by column index.
   *
   * @param filepath
   *            filename with path
   * @param sheetName
   *            name of the sheet
   * @param column
   *            column index
   * @param skipHeader
   *            true if header to be excluded
   * @return List of entries present in the specified column
   * @throws IOException
   *             if an I/O error occurs
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static ArrayList<String> getValuesOfColumn(String filepath, String sheetName, int column, boolean skipHeader)
      throws IOException {
    ArrayList<String> dataList = new ArrayList<>();
    FileInputStream fis = new FileInputStream(filepath);
    XSSFWorkbook wb = new XSSFWorkbook(fis);
    XSSFSheet sheet = wb.getSheet(sheetName);
    int start = skipHeader ? 1 : 0;
    for (int i = start; i <= sheet.getLastRowNum(); i++) {
      dataList.add(getStringValue(sheet, i, column));
    }
    return dataList;
  }

  /**
   * This method fetches entries present in a particular column by column header.
   *
   * @param filepath
   *            filename with path
   * @param sheetName
   *            name of the sheet
   * @param columnHeader
   *            header name
   * @param skipHeader
   *            true if header to be excluded
   * @return List of entries present in the specified column
   * @throws IOException
   *             if an I/O error occurs
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static ArrayList<String> getValuesOfColumn(String filepath, String sheetName, String columnHeader,
      boolean skipHeader) throws IOException {
    ArrayList<String> dataList = new ArrayList<>();
    FileInputStream fis = new FileInputStream(filepath);
    XSSFWorkbook wb = new XSSFWorkbook(fis);
    XSSFSheet sheet = wb.getSheet(sheetName);
    int column = 0;
    for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
      if (getStringValue(sheet, 0, i).equalsIgnoreCase(columnHeader.trim())) {
        column = i;
        break;
      }
    }
    int start = skipHeader ? 1 : 0;
    for (int i = start; i <= sheet.getLastRowNum(); i++) {
      dataList.add(getStringValue(sheet, i, column));
    }
    return dataList;
  }

  /**
   * This method fetches entries present in a particular row by row index.
   *
   * @param filepath
   *            filename with path
   * @param sheetName
   *            name of the sheet
   * @param row
   *            row index
   * @param skipFirstColumn
   *            true if the first column value to be excluded in case there is a
   *            row header
   * @return List of entries present in the specified row
   * @throws IOException
   *             if an I/O error occurs
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static ArrayList<String> getValuesOfRow(String filepath, String sheetName, int row, boolean skipFirstColumn)
      throws IOException {
    ArrayList<String> dataList = new ArrayList<>();
    FileInputStream fis = new FileInputStream(filepath);
    XSSFWorkbook wb = new XSSFWorkbook(fis);
    XSSFSheet sheet = wb.getSheet(sheetName);
    int start = skipFirstColumn ? 1 : 0;
    for (int i = start; i < sheet.getRow(row).getLastCellNum(); i++) {
      dataList.add(getStringValue(sheet, row, i));
    }
    return dataList;
  }

  /**
   * This method fetches entries present in a particular row by row header.
   *
   * @param filepath
   *            filename with path
   * @param sheetName
   *            name of the sheet
   * @param rowHeader
   *            row header or 1st value of row
   * @param skipFirstColumn
   *            true if the first column value to be excluded in case there is a
   *            row header
   * @return List of entries present in the specified row
   * @throws IOException
   *             if an I/O error occurs
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static ArrayList<String> getValuesOfRow(String filepath, String sheetName, String rowHeader,
      boolean skipFirstColumn) throws IOException {
    ArrayList<String> dataList = new ArrayList<>();
    FileInputStream fis = new FileInputStream(filepath);
    XSSFWorkbook wb = new XSSFWorkbook(fis);
    XSSFSheet sheet = wb.getSheet(sheetName);
    int row = 0;
    for (int i = 0; i <= sheet.getLastRowNum(); i++) {
      if (getStringValue(sheet, i, 0).equalsIgnoreCase(rowHeader.trim())) {
        row = i;
        break;
      }
    }
    int start = skipFirstColumn ? 1 : 0;
    for (int i = start; i < sheet.getRow(row).getLastCellNum(); i++) {
      dataList.add(getStringValue(sheet, row, i));
    }
    return dataList;
  }

  /**
   * Reads an Excel sheet and returns a list of maps containing the data. List
   * represents each row and each row contains a map with header as key and value
   * for that header and row
   *
   * @param filepath
   *            filename with path
   * @param sheetName
   *            name of the sheet
   * @return List of maps representing the data from the Excel sheet
   * @throws IOException
   *             if an I/O error occurs
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static List<Map<String, String>> readExcelSheet(String filepath, String sheetName) throws IOException {
    ArrayList<String> dataList = new ArrayList<>();
    FileInputStream fis = new FileInputStream(filepath);
    XSSFWorkbook wb = new XSSFWorkbook(fis);
    XSSFSheet sheet = wb.getSheet(sheetName);
    List<Map<String, String>> dataSet = new ArrayList<>();
    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
      Map<String, String> data = new HashMap<>();
      for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
        String header = getStringValue(sheet, 0, j);
        String value = getStringValue(sheet, i, j);
        data.put(header, value);
      }
      dataSet.add(data);
    }
    return dataSet;
  }

  /**
   * Reads an Excel sheet and returns a list of maps containing the data,
   * optionally skipping the first row. List represents each row and each row
   * contains a map with header as key and value for that header and row
   *
   * @param filepath
   *            filename with path
   * @param sheetName
   *            name of the sheet
   * @param skipFirstRow
   *            true to skip the first row, false otherwise
   * @return List of maps representing the data from the Excel sheet
   * @throws IOException
   *             if an I/O error occurs
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static List<Map<String, String>> readExcelSheet(String filepath, String sheetName, boolean skipFirstRow)
      throws IOException {
    ArrayList<String> dataList = new ArrayList<>();
    FileInputStream fis = new FileInputStream(filepath);
    XSSFWorkbook wb = new XSSFWorkbook(fis);
    XSSFSheet sheet = wb.getSheet(sheetName);
    List<Map<String, String>> dataSet = new ArrayList<>();
    int rowValueStart = skipFirstRow ? 1 : 0;

    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
      Map<String, String> data = new HashMap<>();
      for (int j = rowValueStart; j < sheet.getRow(0).getLastCellNum(); j++) {
        String header = getStringValue(sheet, 0, j);
        String value = getStringValue(sheet, i, j);
        data.put(header, value);
      }
      dataSet.add(data);
    }
    return dataSet;
  }

  /**
   * Retrieve the string value from a given cell in the Excel sheet.
   *
   * @param sheet
   *            Excel sheet from which to retrieve the value
   * @param row
   *            Row index of the cell
   * @param column
   *            Column index of the cell
   * @return The string value of the cell
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  private static String getStringValue(XSSFSheet sheet, int row, int column) {
    Cell cell = sheet.getRow(row).getCell(column);
    if (cell == null)
      return EMPTY_STRING;
    switch (cell.getCellType()) {
      case NUMERIC :
        return String.valueOf(cell.getNumericCellValue());
      case BLANK :
      case _NONE :
        return EMPTY_STRING;
      default :
        return cell.getStringCellValue().trim();
    }
  }
}