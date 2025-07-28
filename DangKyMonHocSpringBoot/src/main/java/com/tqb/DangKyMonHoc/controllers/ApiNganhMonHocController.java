/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.NganhMonHoc;
import com.tqb.DangKyMonHoc.pojo.NganhMonHocPK;
import com.tqb.DangKyMonHoc.services.NganhMonHocService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class ApiNganhMonHocController {

    @Autowired
    private NganhMonHocService nganhMonHocService;

    @GetMapping("/nganhmonhoc")
    public ResponseEntity<List<NganhMonHoc>> getNganhMonHoc(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.nganhMonHocService.findNganhMonHoc(params), HttpStatus.OK);
    }

    @PostMapping("/nganhmonhoc")
    public ResponseEntity<?> create(@RequestBody NganhMonHoc nganhMonHoc) {
        try {
            NganhMonHoc newNganhMonHoc = this.nganhMonHocService.add(nganhMonHoc);
            return new ResponseEntity<>(newNganhMonHoc, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Không thể tạo: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo: " + e.getMessage());
        }
    }

    @DeleteMapping("/nganhmonhoc")
    public ResponseEntity<?> delete(@RequestBody NganhMonHocPK id) {
        try {
            NganhMonHoc deleted = this.nganhMonHocService.delete(id);
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
