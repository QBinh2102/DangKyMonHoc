/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.DangKy;
import com.tqb.DangKyMonHoc.services.DangKyService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ApiDangKyController {

    @Autowired
    private DangKyService dangKyService;

    @GetMapping("/dangky/{dangKyId}")
    public ResponseEntity<DangKy> getDangKyById(@PathVariable(value = "dangKyId") int id) {
        DangKy existing = this.dangKyService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(existing, HttpStatus.OK);
        }
    }

    @GetMapping("/dangky")
    public ResponseEntity<List<DangKy>> getDangKy(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.dangKyService.findDangKy(params), HttpStatus.OK);
    }

    @PostMapping("/dangky")
    public ResponseEntity<?> create(@RequestBody DangKy dangKy) {
        if (dangKy.getId() != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }

        try {
            DangKy newDangKy = this.dangKyService.addOrUpdate(dangKy);
            return new ResponseEntity<>(newDangKy, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo: " + e.getMessage());
        }
    }

    @PutMapping("/dangky/{dangKyId}")
    public ResponseEntity<DangKy> update(@PathVariable(value = "dangKyId") int id, @RequestBody DangKy dangKy) {
        DangKy existing = this.dangKyService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            dangKy.setId(id);
            return new ResponseEntity<>(this.dangKyService.addOrUpdate(dangKy), HttpStatus.OK);
        }
    }

    @DeleteMapping("/dangky/{dangKyId}")
    public ResponseEntity<DangKy> delete(@PathVariable(value="dangKyId") int id){
        DangKy existing = this.dangKyService.findById(id);
        if(existing==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(this.dangKyService.delete(existing), HttpStatus.OK);
        }
    }
    
}
