/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.Nganh;
import com.tqb.DangKyMonHoc.services.NganhService;
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
@RequestMapping("/api/nganh")
public class ApiNganhController {

    @Autowired
    private NganhService nganhService;

    @GetMapping
    public ResponseEntity<List<Nganh>> getNganh(@RequestParam(value = "tenNganh", required = false) String tenNganh) {
        if (tenNganh == null || tenNganh.isEmpty()) {
            return new ResponseEntity<>(this.nganhService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(this.nganhService.findByTenNganhContaining(tenNganh), HttpStatus.OK);
        }
    }

    @GetMapping("/{nganhId}")
    public ResponseEntity<Nganh> getNganhById(@PathVariable(value = "nganhId") int id) {
        Nganh existing = this.nganhService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(existing, HttpStatus.OK);
        }
    }

    @GetMapping("/khoa/{khoaId}")
    public ResponseEntity<List<Nganh>> getNganhByKhoaId(@PathVariable(value = "khoaId") int id) {
        return new ResponseEntity<>(this.nganhService.findByKhoaId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Nganh nganh) {
        if (nganh.getId() != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }

        try {
            Nganh newNganh = this.nganhService.addOrUpdate(nganh);
            return new ResponseEntity<>(newNganh, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo khoa: " + e.getMessage());
        }
    }

    @PutMapping("/{nganhId}")
    public ResponseEntity<Nganh> update(@PathVariable(value = "nganhId") int id, @RequestBody Nganh nganh) {
        Nganh existing = this.nganhService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            nganh.setId(id);
            return new ResponseEntity<>(this.nganhService.addOrUpdate(nganh), HttpStatus.OK);
        }
    }

}
