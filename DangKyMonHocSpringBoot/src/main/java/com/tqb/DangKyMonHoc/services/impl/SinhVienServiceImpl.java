/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tqb.DangKyMonHoc.pojo.NguoiDung;
import com.tqb.DangKyMonHoc.pojo.SinhVien;
import com.tqb.DangKyMonHoc.repositories.NguoiDungRepository;
import com.tqb.DangKyMonHoc.repositories.SinhVienRepository;
import com.tqb.DangKyMonHoc.services.SinhVienService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @Autowired
    private Cloudinary cloudinary;

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
            return this.sinhVienRepo.findAllByOrderByIdDesc();
        }
    }

    @Override
    public SinhVien findByEmail(String email) {
        return this.sinhVienRepo.findByNguoiDung_Email(email);
    }

    @Override
    public SinhVien addOrUpdate(SinhVien sinhVien) {
        if (sinhVien.getNguoiDung() != null) {
            sinhVien.getNguoiDung().setVaiTro("ROLE_SINH_VIEN");

            NguoiDung nguoiDung = sinhVien.getNguoiDung();

            if (nguoiDung.getId() != null) {
                NguoiDung existing = nguoiDungRepo.findById(nguoiDung.getId()).orElse(null);
                if (existing != null) {
                    existing.setSoDienThoai(nguoiDung.getSoDienThoai());

                    if (nguoiDung.getMatKhau() != null && !nguoiDung.getMatKhau().isEmpty()) {
                        existing.setMatKhau(this.passwordEncoder.encode(nguoiDung.getMatKhau()));
                    }

                    sinhVien.setNguoiDung(existing);
                }
            } else {
                if (nguoiDung.getAvatar() != null && !nguoiDung.getAvatar().isEmpty()) {
                    try {
                        String file = nguoiDung.getAvatar().split(",")[1]; // bỏ "data:image/png;base64,"
                        byte[] imageBytes = Base64.getDecoder().decode(file);

                        Map res = cloudinary.uploader().upload(imageBytes,
                                ObjectUtils.asMap("resource_type", "auto"));
                        nguoiDung.setAvatar(res.get("secure_url").toString());
                    } catch (IOException ex) {
                        Logger.getLogger(SinhVienServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                nguoiDung.setMatKhau(this.passwordEncoder.encode(nguoiDung.getCccd()));
                sinhVien.setNguoiDung(nguoiDung);
            }
        }
        if (sinhVien.getKhoaHoc() == null) {
            sinhVien.setKhoaHoc(LocalDate.now().getYear());
        }
        return this.sinhVienRepo.save(sinhVien);
    }

}
