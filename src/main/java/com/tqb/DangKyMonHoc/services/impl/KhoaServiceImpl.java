/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.Khoa;
import com.tqb.DangKyMonHoc.repositories.KhoaRepository;
import com.tqb.DangKyMonHoc.services.KhoaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class KhoaServiceImpl implements KhoaService{
    
    @Autowired
    private KhoaRepository khoaRepo;

    @Override
    public Khoa findById(int id) {
        return this.khoaRepo.findById(id);
    }

    @Override
    public List<Khoa> findAll() {
        return this.khoaRepo.findAll();
    }

    @Override
    public List<Khoa> findByTenKhoaContaining(String tenKhoa) {
        return this.khoaRepo.findByTenKhoaContainingIgnoreCase(tenKhoa);
    }

    @Override
    public Khoa addOrUpdate(Khoa khoa) {
        return this.khoaRepo.save(khoa);
    }
    
}
