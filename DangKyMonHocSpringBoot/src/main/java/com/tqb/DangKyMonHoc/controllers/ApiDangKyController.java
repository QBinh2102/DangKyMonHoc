/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.DangKy;
import com.tqb.DangKyMonHoc.pojo.NguoiDung;
import com.tqb.DangKyMonHoc.services.DangKyService;
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
public class ApiDangKyController {

    @Autowired
    private DangKyService dangKyService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("/dangky/{dangKyId}")
    public ResponseEntity<DangKy> getDangKyById(@PathVariable(value = "dangKyId") int id) {
        DangKy existing = this.dangKyService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(existing, HttpStatus.OK);
        }
    }

    @GetMapping("/secure/me/dangky")
    public ResponseEntity<List<DangKy>> getDangKy(Principal principal, @RequestParam Map<String, String> params) {
        NguoiDung nd = this.nguoiDungService.findByEmail(principal.getName());
        params.put("sinhVienId", nd.getId().toString());
        return new ResponseEntity<>(this.dangKyService.findDangKy(params), HttpStatus.OK);
    }

    @PostMapping("/secure/me/dangky")
    public ResponseEntity<?> create(@RequestBody DangKy dangKy) {
        if (dangKy.getId() != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }

        try {
            DangKy newDangKy = this.dangKyService.add(dangKy);
            return new ResponseEntity<>(newDangKy, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo: " + e.getMessage());
        }
    }

    @DeleteMapping("/secure/me/dangky/{dangKyId}")
    public ResponseEntity<DangKy> delete(@PathVariable(value = "dangKyId") int id) {
        DangKy existing = this.dangKyService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(this.dangKyService.delete(existing), HttpStatus.OK);
        }
    }

}
