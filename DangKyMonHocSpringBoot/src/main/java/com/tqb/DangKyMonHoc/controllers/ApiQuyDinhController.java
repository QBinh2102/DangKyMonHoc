/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.QuyDinh;
import com.tqb.DangKyMonHoc.services.QuyDinhService;
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
public class ApiQuyDinhController {
    
    @Autowired
    private QuyDinhService quyDinhService;
    
    @GetMapping("/quydinh/{quyDinhId}")
    public ResponseEntity<QuyDinh> getQuyDinhById(@PathVariable(value="quyDinhId") int id){
        QuyDinh existing = this.quyDinhService.findById(id);
        if(existing==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(existing, HttpStatus.OK);
        }
    }
    
    @GetMapping("/quydinh")
    public ResponseEntity<List<QuyDinh>> getQuyDinh(@RequestParam Map<String,String> params){
        return new ResponseEntity<>(this.quyDinhService.findQuyDinh(params), HttpStatus.OK);
    }
    
    @PostMapping("/secure/admin/quydinh")
    public ResponseEntity<?> create(@RequestBody QuyDinh quyDinh){
        if(quyDinh.getId()!=null){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }
        
        try{
            QuyDinh newQuyDinh = this.quyDinhService.addOrUpdate(quyDinh);
            return new ResponseEntity<>(newQuyDinh, HttpStatus.CREATED);
        }catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo: " + e.getMessage());
        }
    }
    
    @PutMapping("/secure/admin/quydinh/{quyDinhId}")
    public ResponseEntity<QuyDinh> update(@PathVariable(value="quyDinhId") int id, @RequestBody QuyDinh quyDinh){
        QuyDinh existing = this.quyDinhService.findById(id);
        if(existing==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            quyDinh.setId(id);
            return new ResponseEntity<>(this.quyDinhService.addOrUpdate(quyDinh), HttpStatus.OK);
        }
    }
    
    @DeleteMapping("/secure/admin/quydinh/{quyDinhId}")
    public ResponseEntity<QuyDinh> delete(@PathVariable(value="quyDinhId") int id){
        QuyDinh existing = this.quyDinhService.findById(id);
        if(existing == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(this.quyDinhService.delete(existing), HttpStatus.OK);
        }
    }
    
}
