/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.MonHoc;
import com.tqb.DangKyMonHoc.services.MonHocService;
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
@RequestMapping("/api")
public class ApiMonHocController {

    @Autowired
    private MonHocService monHocService;

    @GetMapping("/monhoc/{monHocId}")
    public ResponseEntity<MonHoc> getMonHocById(@PathVariable(value = "monHocId") int id) {
        MonHoc existing = this.monHocService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(existing, HttpStatus.OK);
        }
    }

    @GetMapping("/monhoc")
    public ResponseEntity<List<MonHoc>> getMonHoc(@RequestParam Map<String,String> params) {
        return new ResponseEntity<>(this.monHocService.findMonHoc(params), HttpStatus.OK);
    }

    @PostMapping("/monhoc")
    public ResponseEntity<?> create(@RequestBody MonHoc monHoc) {
        if (monHoc.getId() != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }

        try {
            MonHoc newMonHoc = this.monHocService.addOrUpdate(monHoc);
            return new ResponseEntity<>(newMonHoc, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo khoa: " + e.getMessage());
        }
    }

    @PutMapping("/monhoc/{monHocId}")
    public ResponseEntity<MonHoc> update(@PathVariable(value = "monHocId") int id, @RequestBody MonHoc monHoc) {
        MonHoc existing = this.monHocService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            monHoc.setId(id);
            return new ResponseEntity<>(this.monHocService.addOrUpdate(monHoc), HttpStatus.OK);
        }
    }

}
