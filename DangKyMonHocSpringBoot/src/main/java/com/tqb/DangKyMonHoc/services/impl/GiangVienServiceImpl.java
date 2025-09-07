/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tqb.DangKyMonHoc.pojo.GiangVien;
import com.tqb.DangKyMonHoc.pojo.NguoiDung;
import com.tqb.DangKyMonHoc.repositories.GiangVienRepository;
import com.tqb.DangKyMonHoc.repositories.NguoiDungRepository;
import com.tqb.DangKyMonHoc.services.GiangVienService;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private NguoiDungRepository nguoiDungRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public GiangVien findById(int id) {
        return this.giangVienRepo.findById(id);
    }

    @Override
    public List<GiangVien> findGiangVien(Map<String, String> params) {
        String khoaId = params.get("khoaId");
        boolean hasKhoaId = khoaId != null && !khoaId.isEmpty();

        if (hasKhoaId) {
            return this.giangVienRepo.findByKhoaId_IdOrderByIdAsc(Integer.parseInt(khoaId));
        } else {
            return this.giangVienRepo.findAllByOrderByIdAsc();
        }
    }

    @Override
    public GiangVien addOrUpdate(GiangVien giangVien) {
        if (giangVien.getNguoiDung() != null) {
            giangVien.getNguoiDung().setVaiTro("ROLE_GIANG_VIEN");

            NguoiDung nguoiDung = giangVien.getNguoiDung();

            if (nguoiDung.getId() != null) {
                NguoiDung existing = nguoiDungRepo.findById(nguoiDung.getId()).orElse(null);
                if (existing != null) {
                    existing.setSoDienThoai(nguoiDung.getSoDienThoai());

                    if (nguoiDung.getMatKhau() != null && !nguoiDung.getMatKhau().isEmpty()) {
                        existing.setMatKhau(this.passwordEncoder.encode(nguoiDung.getMatKhau()));
                    }

                    giangVien.setNguoiDung(existing);
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
                giangVien.setNguoiDung(nguoiDung);
            }
        }

        return this.giangVienRepo.save(giangVien);
    }

    @Override
    public Page<GiangVien> findGiangVienPage(Map<String, String> params) {
        String page = params.get("page");
        String hoTen = params.get("hoTen");
        boolean hasHoTen = hoTen != null && !hoTen.isEmpty();

        int size = 10;
        Pageable pageable = PageRequest.of(Integer.parseInt(page), size);

        if (hasHoTen) {
            return this.giangVienRepo.findByNguoiDung_HoTenContainingIgnoreCaseOrderByIdAsc(hoTen, pageable);
        } else {
            return this.giangVienRepo.findAllByOrderByIdAsc(pageable);
        }
    }

}
