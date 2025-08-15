/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.TietHoc;
import com.tqb.DangKyMonHoc.services.TietHocService;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author toquocbinh2102
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiTietHocController {

    @Autowired
    private TietHocService tietHocService;
    
    @GetMapping("/tiethoc/{tietHocId}")
    public ResponseEntity<TietHoc> getTietHocById(@PathVariable(value = "tietHocId") int id){
        TietHoc existing = this.tietHocService.findById(id);
        if(existing==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(existing, HttpStatus.OK);
        }
    }
    
    @GetMapping("/tiethoc")
    public ResponseEntity<List<TietHoc>> getTietHoc(){
        return new ResponseEntity<>(this.tietHocService.findTietHoc(), HttpStatus.OK);
    }
    
    @PostMapping("/secure/admin/tiethoc")
    public ResponseEntity<?> create(@RequestBody TietHoc tietHoc){
        if(tietHoc.getId()!=null){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }
        
        try{
            TietHoc newTietHoc = this.tietHocService.addOrUpdate(tietHoc);
            return new ResponseEntity<>(newTietHoc, HttpStatus.CREATED);
        }catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo: " + e.getMessage());
        }
    }
    
    @PutMapping("/secure/admin/tiethoc/{tietHocId}")
    public ResponseEntity<TietHoc> update(@PathVariable(value="tietHocId") int id, @RequestBody TietHoc tietHoc){
        TietHoc existing = this.tietHocService.findById(id);
        if(existing==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            tietHoc.setId(id);
            return new ResponseEntity<>(this.tietHocService.addOrUpdate(tietHoc), HttpStatus.OK);
        }
    }
    
}
