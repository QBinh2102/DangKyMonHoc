/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.NguoiDung;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author toquocbinh2102
 */
public interface NguoiDungService extends UserDetailsService{
    
    NguoiDung findById(int id);
    NguoiDung login(String email, String matKhau);
    NguoiDung findByEmail(String email);
    boolean changePassword(int nguoiDungId, String oldPassword, String newPassword);
    
}
