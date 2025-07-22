/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.Khoa;
import com.tqb.DangKyMonHoc.services.KhoaService;
import java.util.List;
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
@RequestMapping("/api/khoa")
public class ApiKhoaController {

    @Autowired
    private KhoaService khoaService;

    @GetMapping
    public ResponseEntity<List<Khoa>> getKhoa(@RequestParam(value = "tenKhoa", required = false) String tenKhoa) {
        if (tenKhoa == null || tenKhoa.trim().isEmpty()) {
            return new ResponseEntity<>(this.khoaService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(this.khoaService.findByTenKhoaContaining(tenKhoa), HttpStatus.OK);
        }
    }

    @GetMapping("/{khoaId}")
    public ResponseEntity<Khoa> getKhoaById(@PathVariable(value = "khoaId") int id) {
        return new ResponseEntity<>(this.khoaService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Khoa khoa) {
        if (khoa.getId() != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }

        try {
            Khoa saved = this.khoaService.addOrUpdate(khoa);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            // Trả về lỗi cụ thể nếu xảy ra Exception
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo khoa: " + e.getMessage());
        }
    }

    @PutMapping("/{khoaId}")
    public ResponseEntity<Khoa> update(@PathVariable(value="khoaId") int id, @RequestBody Khoa khoa) {
        Khoa existing = this.khoaService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        khoa.setId(id); // đảm bảo id đúng
        return new ResponseEntity<>(this.khoaService.addOrUpdate(khoa), HttpStatus.OK);
    }

}
