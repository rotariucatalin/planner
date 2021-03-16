package com.example.Planner.excel;

import com.example.Planner.models.Activity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ActivityExcel {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Activity> activityList;

    public ActivityExcel(List<Activity> activityList) {

        this.activityList = activityList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {

        sheet = workbook.createSheet("Activities");

        Row row = sheet.createRow(0);

        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont xssfFont = workbook.createFont();
        xssfFont.setBold(true);
        xssfFont.setFontHeight(16);
        cellStyle.setFont(xssfFont);

        createCell(row, 0, "ID", cellStyle);
        createCell(row, 1, "Subject", cellStyle);
        createCell(row, 2, "Reference", cellStyle);
        createCell(row, 3, "Activity", cellStyle);
        createCell(row, 4, "Company", cellStyle);
        createCell(row, 5, "Contact", cellStyle);
    }

    private void createCell(Row row, int columnCount, String value, CellStyle cellStyle) {

        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }

    private void writeDataLines() {

        AtomicInteger rowCount = new AtomicInteger(1);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();

        font.setFontHeight(14);
        style.setFont(font);

        if(activityList.size() > 0) {
            activityList.forEach(activity -> {
                Row row = sheet.createRow(rowCount.getAndIncrement());
                int columnCount = 0;

                createCell(row, columnCount++, String.valueOf(activity.getId()), style);
                createCell(row, columnCount++, activity.getSubject(), style);
                createCell(row, columnCount++, activity.getReference(), style);
                createCell(row, columnCount++, activity.getType(), style);
                createCell(row, columnCount++, activity.getCompany().getName(), style);
                createCell(row, columnCount++, activity.getContact().getName(), style);

            });

        } else {

            Row row = sheet.createRow(1);
            createCell(row, 0, "No activities", style);
            sheet.addMergedRegion(new CellRangeAddress(1,1,0,5));

        }
    }


    public void export(HttpServletResponse response) throws IOException {

        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
