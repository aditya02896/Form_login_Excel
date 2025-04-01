package com.example.controller;

import com.example.model.UserForm;
import com.example.service.ExcelExportService;
import com.example.service.UserFormService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/userform")
public class UserFormController {


    private final UserFormService userFormService;
    private final ExcelExportService excelExportService;

    public UserFormController(UserFormService userFormService, ExcelExportService excelExportService) {
        this.userFormService = userFormService;
        this.excelExportService = excelExportService;
    }

    @PostMapping("/submit")
    public String submitUserForm(
            @ModelAttribute UserForm userForm,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            userFormService.saveUserForm(userForm, file);
            return "Form Submitted Successfully! File Path: " + userForm.getIdProofPath();
        } catch (IOException e) {
            return "File upload failed: " + e.getMessage();
        }
    }

    @GetMapping("/all")
    public List<UserForm> getAllUserForms() {
        return userFormService.getAllUserForms();
    }
//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return "File is empty!";
//        }
//
//        if (file.getSize() > 1024 * 1024) {
//            return "File size exceeds 1MB!";
//        }
//
//        try {
//            // File save karne ka path
//            String uploadDir = "E:\\Adi";
//            File directory = new File(uploadDir);
//            if (!directory.exists()) {
//                directory.mkdirs(); // Agar folder nahi hai to create karein
//            }
//
//            // Original filename lena
//            String filePath = uploadDir + file.getOriginalFilename();
//
//            // File save karna
//            file.transferTo(new File(filePath));
//
//            return "File uploaded successfully! Stored at: " + filePath;
//        } catch (IOException e) {
//            return "File upload failed: " + e.getMessage();
//        }
//    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExcel() throws IOException {
        List<UserForm> users = userFormService.getAllUserForms();
        byte[] excelData = excelExportService.generateExcel(users);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.xlsx")
                .body(excelData);
    }


}
