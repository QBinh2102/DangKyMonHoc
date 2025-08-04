/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.BuoiHoc;
import com.tqb.DangKyMonHoc.services.BuoiHocService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class ApiBuoiHocController {

    @Autowired
    private BuoiHocService buoiHocService;

    @GetMapping("/secure/admin/buoihoc/{buoiHocId}")
    public ResponseEntity<BuoiHoc> getBuoiHocById(@PathVariable(value = "buoiHocId") int id) {
        BuoiHoc existing = this.buoiHocService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(existing, HttpStatus.OK);
        }
    }

    @GetMapping("/secure/admin/buoihoc")
    public ResponseEntity<List<BuoiHoc>> getBuoiHoc(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.buoiHocService.findBuoiHoc(params), HttpStatus.OK);
    }

    @PostMapping("/secure/admin/buoihoc")
    public ResponseEntity<?> create(@RequestBody BuoiHoc buoiHoc) {
        if (buoiHoc.getId() != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }

        try {
            BuoiHoc newBuoiHoc = this.buoiHocService.addOrUpdate(buoiHoc);
            return new ResponseEntity<>(newBuoiHoc, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo: " + e.getMessage());
        }
    }

    @PutMapping("/secure/admin/buoihoc/{buoiHocId}")
    public ResponseEntity<BuoiHoc> update(@PathVariable(value = "buoiHocId") int id, @RequestBody BuoiHoc buoiHoc) {
        BuoiHoc existing = this.buoiHocService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            buoiHoc.setId(id);
            return new ResponseEntity<>(this.buoiHocService.addOrUpdate(buoiHoc), HttpStatus.OK);
        }
    }

}
