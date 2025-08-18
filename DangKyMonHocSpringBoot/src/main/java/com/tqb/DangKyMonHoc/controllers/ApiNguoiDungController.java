/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.pojo.NguoiDung;
import com.tqb.DangKyMonHoc.services.NguoiDungService;
import com.tqb.DangKyMonHoc.utils.JwtUtils;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author toquocbinh2102
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiNguoiDungController {
    
    @Autowired
    private NguoiDungService nguoiDungService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> loginInfo){
        String email = loginInfo.get("email");
        String matKhau = loginInfo.get("matKhau");
        
        NguoiDung existing = this.nguoiDungService.login(email, matKhau);
        
        if (existing!=null) {
            try {
                String token = JwtUtils.generateToken(existing.getEmail(), existing.getVaiTro());
                return ResponseEntity.ok().body(Collections.singletonMap("token", token));
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Lỗi khi tạo JWT");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin đăng nhập");
        
    }
    
    @RequestMapping("/secure/profile")
    @ResponseBody
    public ResponseEntity<NguoiDung> getProfile(Principal principal) {
        return new ResponseEntity<>(this.nguoiDungService.findByEmail(principal.getName()), HttpStatus.OK);
    }
    
    @PostMapping("/secure/profile/changepassword")
    public ResponseEntity<?> changePassword(Principal principal, @RequestBody Map<String, String> payload) {
        String email = principal.getName();
        NguoiDung nd = nguoiDungService.findByEmail(email);

        if (nd == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy người dùng");
        }

        String oldPassword = payload.get("oldPassword");
        String newPassword = payload.get("newPassword");

        boolean success = nguoiDungService.changePassword(nd.getId(), oldPassword, newPassword);
        if (!success) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mật khẩu cũ không đúng");
        }
        return ResponseEntity.ok("Đổi mật khẩu thành công");
    }
    
}
