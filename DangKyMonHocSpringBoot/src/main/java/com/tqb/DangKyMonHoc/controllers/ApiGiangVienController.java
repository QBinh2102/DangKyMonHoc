/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.GiangVien;
import com.tqb.DangKyMonHoc.services.GiangVienService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api")
@CrossOrigin
public class ApiGiangVienController {

    @Autowired
    private GiangVienService giangVienService;

    @GetMapping("/secure/admin/giangvien/{giangVienId}")
    public ResponseEntity<GiangVien> getGiangVienById(@PathVariable(value = "giangVienId") int id) {
        GiangVien existing = this.giangVienService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(existing, HttpStatus.OK);
        }
    }

    @GetMapping("/secure/admin/giangvien")
    public ResponseEntity<List<GiangVien>> getGiangVien(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.giangVienService.findGiangVien(params), HttpStatus.OK);
    }

    @GetMapping("/secure/admin/giangvien-page")
    public ResponseEntity<Page<GiangVien>> getGiangVienPage(@RequestParam Map<String, String> params) {
        Page<GiangVien> giangVienPage = this.giangVienService.findGiangVienPage(params);
        return new ResponseEntity<>(giangVienPage, HttpStatus.OK);
    }

    @PostMapping("/secure/admin/giangvien")
    public ResponseEntity<?> create(@RequestBody GiangVien giangVien) {
        if (giangVien.getId() != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }

        try {
            GiangVien newGiangVien = this.giangVienService.addOrUpdate(giangVien);
            return new ResponseEntity<>(newGiangVien, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo: " + e.getMessage());
        }
    }

    @PutMapping("/secure/admin/giangvien/{giangVienId}")
    public ResponseEntity<GiangVien> update(@PathVariable(value = "giangVienId") int id, @RequestBody GiangVien giangVien) {
        GiangVien existing = this.giangVienService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            giangVien.setId(id);
            return new ResponseEntity<>(this.giangVienService.addOrUpdate(giangVien), HttpStatus.OK);
        }
    }

}
