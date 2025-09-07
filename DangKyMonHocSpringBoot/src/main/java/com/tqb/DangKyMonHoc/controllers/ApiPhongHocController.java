/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.PhongHoc;
import com.tqb.DangKyMonHoc.services.PhongHocService;
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
public class ApiPhongHocController {

    @Autowired
    private PhongHocService phongHocService;

    @GetMapping("/phonghoc")
    public ResponseEntity<List<PhongHoc>> getPhongHoc(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.phongHocService.findPhongHoc(params), HttpStatus.OK);
    }

    @GetMapping("/phonghoc-page")
    public ResponseEntity<Page<PhongHoc>> getPhongHocPage(@RequestParam Map<String, String> params) {
        Page<PhongHoc> phongHocPage = this.phongHocService.findPhongHocPage(params);
        return new ResponseEntity<>(phongHocPage, HttpStatus.OK);
    }

    @GetMapping("/phonghoc/{phongHocId}")
    public ResponseEntity<PhongHoc> getPhongHocById(@PathVariable(value = "phongHocId") int id) {
        PhongHoc existing = this.phongHocService.findById(id);
        if (existing != null) {
            return new ResponseEntity<>(this.phongHocService.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/secure/admin/phonghoc")
    public ResponseEntity<?> createPhongHoc(@RequestBody PhongHoc phongHoc) {
        if (phongHoc.getId() != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }

        try {
            PhongHoc newPhongHoc = this.phongHocService.addOrUpdate(phongHoc);
            return new ResponseEntity<>(newPhongHoc, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo: " + e.getMessage());
        }
    }

    @PutMapping("/secure/admin/phonghoc/{phongHocId}")
    public ResponseEntity<PhongHoc> update(@PathVariable(value = "phongHocId") int id, @RequestBody PhongHoc phongHoc) {
        PhongHoc existing = this.phongHocService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            phongHoc.setId(id);
            return new ResponseEntity<>(this.phongHocService.addOrUpdate(phongHoc), HttpStatus.OK);
        }
    }

}
