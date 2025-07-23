/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.SinhVien;
import com.tqb.DangKyMonHoc.repositories.SinhVienRepository;
import com.tqb.DangKyMonHoc.services.SinhVienService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class SinhVienServiceImpl implements SinhVienService{
    
    @Autowired
    private SinhVienRepository sinhVienRepo;

    @Override
    public SinhVien findById(int id) {
        return this.sinhVienRepo.findById(id);
    }

    @Override
    public List<SinhVien> findAll() {
        return this.sinhVienRepo.findAllByOrderByIdAsc();
    }

    @Override
    public List<SinhVien> findByHoTenContaining(String hoTen) {
        return this.sinhVienRepo.findByHoTenContainingIgnoreCaseOrderByIdAsc(hoTen);
    }

    @Override
    public SinhVien addOrUpdate(SinhVien sinhVien) {
        return this.sinhVienRepo.save(sinhVien);
    }
    
}
