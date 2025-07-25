/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.SinhVien;
import com.tqb.DangKyMonHoc.services.SinhVienService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author toquocbinh2102
 */
@RestController
@RequestMapping("/api/sinhvien")
public class ApiSinhVienController {

    @Autowired
    private SinhVienService sinhVienService;

    @GetMapping("/{sinhVienId}")
    public ResponseEntity<SinhVien> getSinhVienById(@PathVariable(value = "sinhVienId") int id) {
        SinhVien existing = this.sinhVienService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(existing, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<List<SinhVien>> getSinhVien(@RequestParam Map<String,String> params) {
        return new ResponseEntity<>(this.sinhVienService.findSinhVien(params), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SinhVien sinhVien) {
        if (sinhVien.getId() != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }

        try {
            SinhVien newSinhVien = this.sinhVienService.addOrUpdate(sinhVien);
            return new ResponseEntity<>(newSinhVien, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo: " + e.getMessage());
        }
    }

    @PutMapping("/{sinhVienId}")
    public ResponseEntity<SinhVien> update(@PathVariable(value = "sinhVienId") int id, @RequestBody SinhVien sinhVien) {
        SinhVien existing = this.sinhVienService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            sinhVien.setId(id);
            return new ResponseEntity<>(this.sinhVienService.addOrUpdate(sinhVien), HttpStatus.OK);
        }
    }

}
