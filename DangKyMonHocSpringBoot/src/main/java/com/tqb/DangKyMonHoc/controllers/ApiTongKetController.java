/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.dto.TongKetHocKyDTO;
import com.tqb.DangKyMonHoc.pojo.NguoiDung;
import com.tqb.DangKyMonHoc.services.NguoiDungService;
import com.tqb.DangKyMonHoc.services.TongKetService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author toquocbinh2102
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiTongKetController {
    
    @Autowired
    private TongKetService tongKetService;
    
    @Autowired
    private NguoiDungService nguoiDungService;
    
    @GetMapping("/secure/me/tongket")
    public ResponseEntity<List<TongKetHocKyDTO>> getTongKet(Principal principal){
        NguoiDung nd = this.nguoiDungService.findByEmail(principal.getName());
        if(nd!=null){
            return new ResponseEntity<>(this.tongKetService.getTongKetHocKy(nd.getId()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
