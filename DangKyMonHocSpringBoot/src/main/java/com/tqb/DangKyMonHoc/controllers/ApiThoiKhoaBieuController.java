/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.NguoiDung;
import com.tqb.DangKyMonHoc.pojo.ThoiKhoaBieu;
import com.tqb.DangKyMonHoc.services.NguoiDungService;
import com.tqb.DangKyMonHoc.services.ThoiKhoaBieuService;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@CrossOrigin
public class ApiThoiKhoaBieuController {

    @Autowired
    private ThoiKhoaBieuService thoiKhoaBieuService;
    
    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("/thoikhoabieu/{thoiKhoaBieuId}")
    public ResponseEntity<ThoiKhoaBieu> getThoiKhoaBieuById(@PathVariable(value = "thoiKhoaBieuId") int id) {
        ThoiKhoaBieu existing = this.thoiKhoaBieuService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(existing, HttpStatus.OK);
        }
    }

    @GetMapping("/secure/thoikhoabieu")
    public ResponseEntity<List<ThoiKhoaBieu>> getThoiKhoaBieu(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.thoiKhoaBieuService.findThoiKhoaBieu(params), HttpStatus.OK);
    }
    
    @GetMapping("/secure/me/thoikhoabieu")
    public ResponseEntity<List<ThoiKhoaBieu>> getThoiKhoaBieuSinhVien(
            Principal principal, 
            @RequestParam int hocKyId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date ngayBatDau,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date ngayKetThuc){
        
        NguoiDung nd = this.nguoiDungService.findByEmail(principal.getName());
        if(nd!=null){
            return new ResponseEntity<>(this.thoiKhoaBieuService.findBySinhVienAndHocKy(nd.getId(), hocKyId, ngayBatDau, ngayKetThuc), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/secure/me/thoikhoabieu")
    public ResponseEntity<?> create(@RequestBody ThoiKhoaBieu thoiKhoaBieu) {
        if (thoiKhoaBieu.getId() != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }

        try {
            ThoiKhoaBieu newThoiKhoaBieu = this.thoiKhoaBieuService.add(thoiKhoaBieu);
            return new ResponseEntity<>(newThoiKhoaBieu, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo: " + e.getMessage());
        }
    }

    @DeleteMapping("/secure/me/thoikhoabieu")
    public ResponseEntity<Void> delete(@RequestParam(value="dangKyId") int dangKyId) {
        this.thoiKhoaBieuService.deleteByDangKyId(dangKyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
