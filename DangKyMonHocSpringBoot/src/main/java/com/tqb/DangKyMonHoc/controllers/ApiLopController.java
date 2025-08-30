/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.Lop;
import com.tqb.DangKyMonHoc.services.LopService;
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
public class ApiLopController {

    @Autowired
    private LopService lopService;

    @GetMapping("/lop/{lopId}")
    public ResponseEntity<Lop> getLopById(@PathVariable(value = "lopId") int id) {
        Lop existing = this.lopService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(existing, HttpStatus.OK);
        }
    }

    @GetMapping("/lop")
    public ResponseEntity<List<Lop>> getLop(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.lopService.findLop(params), HttpStatus.OK);
    }
    
    @GetMapping("/lop-page")
    public ResponseEntity<Page<Lop>> getLopPage(@RequestParam Map<String, String> params) {
        Page<Lop> lopPage = this.lopService.findLopPage(params);
        return new ResponseEntity<>(lopPage, HttpStatus.OK);
    }

    @PostMapping("/secure/admin/lop")
    public ResponseEntity<?> create(@RequestBody Lop lop){
        if (lop.getId() != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }

        try {
            Lop newLop = this.lopService.addOrUpdate(lop);
            return new ResponseEntity<>(newLop, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo: " + e.getMessage());
        }
    }
    
    @PutMapping("/secure/admin/lop/{lopId}")
    public ResponseEntity<Lop> update(@RequestBody Lop lop, @PathVariable(value = "lopId") int id){
        Lop existing = this.lopService.findById(id);
        if(existing==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            lop.setId(id);
            return new ResponseEntity<>(this.lopService.addOrUpdate(lop), HttpStatus.OK);
        }
    }
    
}
