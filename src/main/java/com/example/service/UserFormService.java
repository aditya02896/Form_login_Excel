package com.example.service;

import com.example.model.UserForm;
import com.example.repository.UserFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class UserFormService {

    @Autowired
    private UserFormRepository userFormRepository;

    public UserForm saveUserForm(UserForm userForm, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {

            // File Save Karne Ka Path

            String uploadDir = "E:/Adi/";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();

                // Agar folder nahi hai to create karein
            }

            // File ka original name lena

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + fileName;

            // File ko system me save karna

            file.transferTo(new File(filePath));

            // File ka path database me store karna

            userForm.setIdProofPath(filePath);
        }
        return userFormRepository.save(userForm);
    }

    public List<UserForm> getAllUserForms() {
        return userFormRepository.findAll();
    }

    public boolean isUtrNoExists(String utrNo) {
        return userFormRepository.existsByUtrNo(utrNo);
    }
}
