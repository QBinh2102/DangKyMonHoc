/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.HocPhi;
import com.tqb.DangKyMonHoc.pojo.NguoiDung;
import com.tqb.DangKyMonHoc.services.HocPhiService;
import com.tqb.DangKyMonHoc.services.NguoiDungService;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ApiHocPhiController {

    @Autowired
    private HocPhiService hocPhiService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("/secure/me/hocphi/latest")
    public ResponseEntity<HocPhi> getHocPhiMoiNhat(Principal principal) {
        NguoiDung nd = this.nguoiDungService.findByEmail(principal.getName());
        return new ResponseEntity<>(this.hocPhiService.findHocPhiMoiNhatTheoSinhVien(nd.getId()), HttpStatus.OK);
    }

    @GetMapping("/secure/admin/hocphi/latest")
    public ResponseEntity<HocPhi> getHocPhiMoiNhatCuaSinhVien(@RequestParam(value = "sinhVienId") int sinhVienId) {
        NguoiDung nd = this.nguoiDungService.findById(sinhVienId);
        return new ResponseEntity<>(this.hocPhiService.findHocPhiMoiNhatTheoSinhVien(nd.getId()), HttpStatus.OK);
    }

    @GetMapping("/secure/me/hocphi")
    public ResponseEntity<List<HocPhi>> getHocPhi(Principal principal) {
        NguoiDung nd = this.nguoiDungService.findByEmail(principal.getName());
        Map<String, String> params = new HashMap<>();
        params.put("sinhVienId", nd.getId().toString());
        return new ResponseEntity<>(this.hocPhiService.findHocPhi(params), HttpStatus.OK);
    }

    @GetMapping("/secure/hocphi/{hocPhiId}")
    public ResponseEntity<HocPhi> getHocPhiById(@PathVariable(value = "hocPhiId") int id) {
        HocPhi existing = this.hocPhiService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(existing, HttpStatus.OK);
        }
    }

    @GetMapping("/secure/admin/hocphi-page")
    public ResponseEntity<Page<HocPhi>> getHocPhiPage(@RequestParam Map<String, String> params) {
        Page<HocPhi> hocPhiPage = this.hocPhiService.findHocPhiPage(params);
        return new ResponseEntity<>(hocPhiPage, HttpStatus.OK);
    }

    @PutMapping("/secure/hocphi/{hocPhiId}")
    public ResponseEntity<HocPhi> update(@PathVariable(value = "hocPhiId") int id, @RequestBody HocPhi hocPhi) {
        HocPhi existing = this.hocPhiService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            hocPhi.setId(id);
            return new ResponseEntity<>(this.hocPhiService.addOrUpdate(hocPhi), HttpStatus.OK);
        }
    }

}
