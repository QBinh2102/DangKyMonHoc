/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.MonHocLienQuan;
import com.tqb.DangKyMonHoc.pojo.MonHocLienQuanPK;
import com.tqb.DangKyMonHoc.services.MonHocLienQuanService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ApiMonHocLienQuanController {

    @Autowired
    private MonHocLienQuanService monHocLienQuanService;

    @GetMapping("/monhoclienquan")
    public ResponseEntity<List<MonHocLienQuan>> getMonHocLienQuan(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.monHocLienQuanService.findMonHocLienQuan(params), HttpStatus.OK);
    }

    @PostMapping("/secure/admin/monhoclienquan")
    public ResponseEntity<?> create(@RequestBody MonHocLienQuan monHocLienQuan) {
        try {
            MonHocLienQuan newMonHocLienQuan = monHocLienQuanService.add(monHocLienQuan);
            return new ResponseEntity<>(newMonHocLienQuan, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Lỗi: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi server: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/secure/admin/monhoclienquan")
    public ResponseEntity<?> delete(@RequestBody MonHocLienQuanPK id) {
        boolean deleted = monHocLienQuanService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Đã xóa thành công");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy bản ghi để xóa");
        }
    }

}
