/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.ThongKeDTO;
import com.tqb.DangKyMonHoc.services.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ApiThongKeController {
    
    @Autowired
    private ThongKeService thongKeService;
    
    @GetMapping("/secure/admin/thongke")
    public ThongKeDTO thongKe(
            @RequestParam int hocKyId,
            @RequestParam int khoaId,
            @RequestParam int monHocId
    ) {
        return this.thongKeService.thongKe(hocKyId, khoaId, monHocId);
    }
    
}
