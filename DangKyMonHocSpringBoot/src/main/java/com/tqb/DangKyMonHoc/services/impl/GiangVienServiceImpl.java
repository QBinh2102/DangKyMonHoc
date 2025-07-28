/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.GiangVien;
import com.tqb.DangKyMonHoc.repositories.GiangVienRepository;
import com.tqb.DangKyMonHoc.services.GiangVienService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class GiangVienServiceImpl implements GiangVienService {

    @Autowired
    private GiangVienRepository giangVienRepo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public GiangVien findById(int id) {
        return this.giangVienRepo.findById(id);
    }

    @Override
    public List<GiangVien> findGiangVien(Map<String, String> params) {
        String hoTen = params.get("hoTen");
        boolean hasHoTen = hoTen != null && !hoTen.isEmpty();
        if (hasHoTen) {
            return this.giangVienRepo.findByNguoiDung_HoTenContainingIgnoreCaseOrderByIdAsc(hoTen);
        } else {
            return this.giangVienRepo.findAllByOrderByIdAsc();
        }
    }

    @Override
    public GiangVien addOrUpdate(GiangVien giangVien) {
        if (giangVien.getNguoiDung() != null
                && (giangVien.getNguoiDung().getMatKhau() == null || giangVien.getNguoiDung().getMatKhau().isEmpty())) {
            giangVien.getNguoiDung().setMatKhau(this.passwordEncoder.encode("123456"));
        }
        return this.giangVienRepo.save(giangVien);
    }

}
