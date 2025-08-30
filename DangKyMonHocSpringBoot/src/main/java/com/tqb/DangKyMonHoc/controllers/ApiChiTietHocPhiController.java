/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.ChiTietHocPhi;
import com.tqb.DangKyMonHoc.pojo.NguoiDung;
import com.tqb.DangKyMonHoc.services.ChiTietHocPhiService;
import com.tqb.DangKyMonHoc.services.NguoiDungService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class ApiChiTietHocPhiController {

    @Autowired
    private ChiTietHocPhiService chiTietHocPhiService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("/secure/me/chitiethocphi")
    public ResponseEntity<List<ChiTietHocPhi>> getChiTietHocPhi(Principal principal, @RequestParam(value = "hocKyId") int hocKyId) {
        NguoiDung nd = this.nguoiDungService.findByEmail(principal.getName());
        return new ResponseEntity<>(this.chiTietHocPhiService.findChiTietHocPhiBySinhVienAndHocKy(nd.getId(), hocKyId), HttpStatus.OK);
    }
    
    @GetMapping("/secure/admin/chitiethocphi")
    public ResponseEntity<List<ChiTietHocPhi>> getChiTietHocPhi(@RequestParam(value = "hocPhiId") int hocPhiId) {
        return new ResponseEntity<>(this.chiTietHocPhiService.findChiTietHocPhiByHocPhiId(hocPhiId), HttpStatus.OK);
    }
    
    @PostMapping("/secure/admin/chitiethocphi")
    public ResponseEntity<?> createForSinhVien(@RequestParam Map<String,String> params) {
        return new ResponseEntity<>(this.chiTietHocPhiService.add(params), HttpStatus.CREATED);
    }

    @PostMapping("/secure/me/chitiethocphi")
    public ResponseEntity<?> create(@RequestParam Map<String,String> params) {
        return new ResponseEntity<>(this.chiTietHocPhiService.add(params), HttpStatus.CREATED);
    }

    @DeleteMapping("/secure/me/chitiethocphi")
    public ResponseEntity<ChiTietHocPhi> delete(Principal principal, @RequestParam(value = "buoiHocId") int buoiHocId) {
        NguoiDung nd = this.nguoiDungService.findByEmail(principal.getName());
        this.chiTietHocPhiService.delete(nd.getId(), buoiHocId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
