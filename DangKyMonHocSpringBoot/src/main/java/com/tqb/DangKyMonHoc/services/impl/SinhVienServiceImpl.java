/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.NguoiDung;
import com.tqb.DangKyMonHoc.pojo.SinhVien;
import com.tqb.DangKyMonHoc.repositories.NguoiDungRepository;
import com.tqb.DangKyMonHoc.repositories.SinhVienRepository;
import com.tqb.DangKyMonHoc.services.SinhVienService;
import java.time.LocalDate;
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
public class SinhVienServiceImpl implements SinhVienService {

    @Autowired
    private SinhVienRepository sinhVienRepo;
    
    @Autowired
    private NguoiDungRepository nguoiDungRepo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public SinhVien findById(int id) {
        return this.sinhVienRepo.findById(id);
    }

    @Override
    public List<SinhVien> findSinhVien(Map<String, String> params) {
        String hoTen = params.get("hoTen");
        boolean hasHoTen = hoTen != null && !hoTen.isEmpty();
        if (hasHoTen) {
            return this.sinhVienRepo.findByNguoiDung_HoTenContainingIgnoreCaseOrderByIdAsc(hoTen);
        } else {
            return this.sinhVienRepo.findAllByOrderByIdAsc();
        }
    }

    @Override
    public SinhVien addOrUpdate(SinhVien sinhVien) {
        if (sinhVien.getNguoiDung() != null) {
            sinhVien.getNguoiDung().setVaiTro("ROLE_SINH_VIEN");

            NguoiDung nguoiDung = sinhVien.getNguoiDung();

            if (nguoiDung.getId() != null) {
                NguoiDung existing = nguoiDungRepo.findById(nguoiDung.getId()).orElse(null);
                if (existing != null) {
                    existing.setHoTen(nguoiDung.getHoTen());
                    existing.setEmail(nguoiDung.getEmail());

                    if (nguoiDung.getMatKhau() != null && !nguoiDung.getMatKhau().isEmpty()) {
                        existing.setMatKhau(this.passwordEncoder.encode(nguoiDung.getMatKhau()));
                    }

                    sinhVien.setNguoiDung(existing);
                }
            } else {
                nguoiDung.setMatKhau(this.passwordEncoder.encode("123456"));
                sinhVien.setNguoiDung(nguoiDung);
            }
        }
        if(sinhVien.getKhoaHoc()==null){
            sinhVien.setKhoaHoc(LocalDate.now().getYear());
        }
        return this.sinhVienRepo.save(sinhVien);
    }

}
