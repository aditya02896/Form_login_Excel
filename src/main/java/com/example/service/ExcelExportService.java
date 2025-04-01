package com.example.service;

import com.example.model.UserForm;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {

    public byte[] generateExcel(List<UserForm> users) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        // ✅ Header Row
        Row headerRow = sheet.createRow(0);
        String[] columns = {"ID", "Name", "Address", "District", "Tehsil", "Village", "Email", "Mobile", "Gender",
                "Bank Account", "IFSC", "Beneficiary Name", "Amount", "ID Proof Path", "UTR No"};

        for (int i = 0; i < columns.length; i++) {
            headerRow.createCell(i).setCellValue(columns[i]);
        }

        // ✅ Data Rows
        int rowIdx = 1;
        for (UserForm user : users) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getAddress());
            row.createCell(3).setCellValue(user.getDistrict());
            row.createCell(4).setCellValue(user.getTehsil());
            row.createCell(5).setCellValue(user.getVillage());
            row.createCell(6).setCellValue(user.getEmail());
            row.createCell(7).setCellValue(user.getMobile());
            row.createCell(8).setCellValue(user.getGender());
            row.createCell(9).setCellValue(user.getBankAccount());
            row.createCell(10).setCellValue(user.getIfsc());
            row.createCell(11).setCellValue(user.getBeneficiaryName());

            // ✅ Null check for Amount
            row.createCell(12).setCellValue(user.getAmount() != null ? user.getAmount() : 0.0);

            row.createCell(13).setCellValue(user.getIdProofPath());
            row.createCell(14).setCellValue(user.getUtrNo());
        }

        // ✅ Writing to Output Stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
