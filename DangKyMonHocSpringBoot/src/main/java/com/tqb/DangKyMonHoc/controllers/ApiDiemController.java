/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.Diem;
import com.tqb.DangKyMonHoc.services.DiemService;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author toquocbinh2102
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiDiemController {

    @Autowired
    private DiemService diemService;

    @GetMapping("/diem/{diemId}")
    public ResponseEntity<Diem> getDiemById(@PathVariable(value = "diemId") int id) {
        return new ResponseEntity<>(this.diemService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/diem")
    public ResponseEntity<List<Diem>> getDiem(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.diemService.findDiem(params), HttpStatus.OK);
    }

    @PostMapping("//diem")
    public ResponseEntity<?> create(@RequestBody Diem diem) {
        if (diem.getId() != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("ID phải để trống khi tạo mới.");
        }

        try {
            Diem newDiem = this.diemService.addOrUpdate(diem);
            return new ResponseEntity<>(newDiem, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo: " + e.getMessage());
        }
    }
    
    @PutMapping("/diem/{diemId}")
    public ResponseEntity<Diem> update(@PathVariable(value="diemId") int id, @RequestBody Diem diem){
        Diem existing = this.diemService.findById(id);
        if(existing == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            diem.setId(id);
            return new ResponseEntity<>(this.diemService.addOrUpdate(diem), HttpStatus.OK);
        }
    }

}
