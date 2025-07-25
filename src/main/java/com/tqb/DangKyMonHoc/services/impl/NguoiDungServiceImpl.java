/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.NguoiDung;
import com.tqb.DangKyMonHoc.repositories.NguoiDungRepository;
import com.tqb.DangKyMonHoc.services.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class NguoiDungServiceImpl implements NguoiDungService{
    
    @Autowired
    private NguoiDungRepository nguoiDungRepo;

    @Override
    public NguoiDung login(String email, String matKhau) {
        return this.nguoiDungRepo.findByEmailAndMatKhau(email, matKhau);
    }
    
}
