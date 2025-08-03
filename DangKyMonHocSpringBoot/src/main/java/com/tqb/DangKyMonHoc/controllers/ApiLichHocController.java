/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.LichHoc;
import com.tqb.DangKyMonHoc.services.LichHocService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ApiLichHocController {

    @Autowired
    private LichHocService lichHocService;

    @GetMapping("/lichhoc/{lichHocId}")
    public ResponseEntity<LichHoc> getLichHocById(@PathVariable(value = "lichHocId") int id) {
        LichHoc existing = this.lichHocService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(existing, HttpStatus.OK);
        }
    }

    @GetMapping("/lichhoc")
    public ResponseEntity<List<LichHoc>> getLichHoc(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.lichHocService.findLichHoc(params), HttpStatus.OK);
    }

    @PostMapping("/lichhoc")
    public ResponseEntity<?> create(@RequestBody LichHoc lichHoc) {
        if (lichHoc.getId() != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }

        try {
            LichHoc newLichHoc = this.lichHocService.addOrUpdate(lichHoc);
            return new ResponseEntity<>(newLichHoc, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo: " + e.getMessage());
        }
    }

    @PutMapping("/lichhoc/{lichHocId}")
    public ResponseEntity<LichHoc> update(@PathVariable(value = "lichHocId") int id, @RequestBody LichHoc lichHoc) {
        LichHoc existing = this.lichHocService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            lichHoc.setId(id);
            return new ResponseEntity<>(this.lichHocService.addOrUpdate(lichHoc), HttpStatus.OK);
        }
    }

}
