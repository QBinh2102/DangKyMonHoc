/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.HocKy;
import com.tqb.DangKyMonHoc.services.HocKyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author toquocbinh2102
 */
@RestController
@RequestMapping("/api/hocky")
public class ApiHocKyController {
    
    @Autowired
    private HocKyService hocKyService;
    
    @GetMapping
    public ResponseEntity<List<HocKy>> getAllHocKy(){
        return new ResponseEntity<>(this.hocKyService.findAllByOrderByIdAsc(), HttpStatus.OK);
    }
    
    @GetMapping("/latest")
    public ResponseEntity<HocKy> getHocKyMoiNhat(){
        return new ResponseEntity<>(this.hocKyService.findTopByOrderByIdDesc(), HttpStatus.OK);
    }
    
    @GetMapping("/{hocKyId}")
    public ResponseEntity<HocKy> getHocKyById(@PathVariable(value="hocKyId") int id){
        return new ResponseEntity<>(this.hocKyService.findById(id), HttpStatus.OK);
    }
}
