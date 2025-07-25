/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.pojo.NguoiDung;

/**
 *
 * @author toquocbinh2102
 */
public interface NguoiDungService {
    
    NguoiDung login(String email, String matKhau);
    
}
