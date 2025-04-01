package com.example.repository;

import com.example.model.UserForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFormRepository extends JpaRepository<UserForm, Long> {
    boolean existsByUtrNo(String utrNo);
}
