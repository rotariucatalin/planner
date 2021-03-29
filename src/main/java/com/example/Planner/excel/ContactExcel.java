package com.example.Planner.excel;

import com.example.Planner.models.Activity;
import com.example.Planner.models.Contact;
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

public class ContactExcel {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Contact> contactsList;

    public ContactExcel(List<Contact> contactsList) {

        this.contactsList = contactsList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {

        sheet = workbook.createSheet("Contacts");

        Row row = sheet.createRow(0);

        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont xssfFont = workbook.createFont();
        xssfFont.setBold(true);
        xssfFont.setFontHeight(16);
        cellStyle.setFont(xssfFont);

        createCell(row, 0, "ID", cellStyle);
        createCell(row, 1, "Name", cellStyle);
        createCell(row, 2, "Company", cellStyle);
        createCell(row, 3, "Department", cellStyle);
        createCell(row, 4, "Position", cellStyle);
        createCell(row, 5, "Country", cellStyle);
        createCell(row, 6, "Email", cellStyle);
        createCell(row, 7, "Phone", cellStyle);
        createCell(row, 8, "Status", cellStyle);
        createCell(row, 9, "Consent", cellStyle);
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

        if(contactsList.size() > 0) {
            contactsList.forEach(contact -> {
                Row row = sheet.createRow(rowCount.getAndIncrement());
                int columnCount = 0;
                String consent = "No";
                if(contact.isConsent()) consent = "Yes";
                createCell(row, columnCount++, String.valueOf(contact.getId()), style);
                createCell(row, columnCount++, contact.getName(), style);
                createCell(row, columnCount++, contact.getCompany().getName(), style);
                createCell(row, columnCount++, contact.getDepartment(), style);
                createCell(row, columnCount++, contact.getPosition(), style);
                createCell(row, columnCount++, contact.getCountry(), style);
                createCell(row, columnCount++, contact.getEmail(), style);
                createCell(row, columnCount++, contact.getPhone(), style);
                createCell(row, columnCount++, contact.getStatus(), style);
                createCell(row, columnCount++, consent, style);

            });

        } else {

            Row row = sheet.createRow(1);
            createCell(row, 0, "No contacts", style);
            sheet.addMergedRegion(new CellRangeAddress(1,1,0,9));

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
