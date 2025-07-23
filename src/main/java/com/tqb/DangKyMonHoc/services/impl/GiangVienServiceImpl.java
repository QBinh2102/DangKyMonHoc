/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.GiangVien;
import com.tqb.DangKyMonHoc.repositories.GiangVienRepository;
import com.tqb.DangKyMonHoc.services.GiangVienService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class GiangVienServiceImpl implements GiangVienService{
    
    @Autowired
    private GiangVienRepository giangVienRepo;

    @Override
    public GiangVien findById(int id) {
        return this.giangVienRepo.findById(id);
    }

    @Override
    public List<GiangVien> findAll() {
        return this.giangVienRepo.findAllByOrderByIdAsc();
    }

    @Override
    public List<GiangVien> findByHoTenContaining(String hoTen) {
        return this.giangVienRepo.findByHoTenContainingIgnoreCaseOrderByIdAsc(hoTen);
    }

    @Override
    public GiangVien addOrUpdate(GiangVien giangVien) {
        return this.giangVienRepo.save(giangVien);
    }
    
}
