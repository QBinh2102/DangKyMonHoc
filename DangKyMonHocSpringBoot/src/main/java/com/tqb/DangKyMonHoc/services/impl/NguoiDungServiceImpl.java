/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.services.impl;

import com.tqb.DangKyMonHoc.pojo.NguoiDung;
import com.tqb.DangKyMonHoc.repositories.NguoiDungRepository;
import com.tqb.DangKyMonHoc.services.NguoiDungService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class NguoiDungServiceImpl implements NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public NguoiDung findById(int id) {
        return this.nguoiDungRepo.findById(id);
    }

    @Override
    public NguoiDung login(String email, String matKhau) {
        NguoiDung existing = this.nguoiDungRepo.findByEmail(email);
        if (existing != null && passwordEncoder.matches(matKhau, existing.getMatKhau())) {
            return existing;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        NguoiDung nguoiDung = this.nguoiDungRepo.findByEmail(email);
        if (nguoiDung == null) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(nguoiDung.getVaiTro()));

        return new org.springframework.security.core.userdetails.User(
                nguoiDung.getEmail(), nguoiDung.getMatKhau(), authorities);
    }

    @Override
    public NguoiDung findByEmail(String email) {
        return this.nguoiDungRepo.findByEmail(email);
    }

    @Override
    public boolean changePassword(int nguoiDungId, String oldPassword, String newPassword) {
        NguoiDung nguoiDung = nguoiDungRepo.findById(nguoiDungId);
        if (nguoiDung == null) {
            return false; //Không tìm thấy user
        }

        //Kiểm tra mật khẩu cũ có khớp không
        if (!passwordEncoder.matches(oldPassword, nguoiDung.getMatKhau())) {
            return false; // Sai mật khẩu cũ
        }

        //Đổi mật khẩu mới
        nguoiDung.setMatKhau(passwordEncoder.encode(newPassword));
        nguoiDungRepo.save(nguoiDung);

        return true;
    }

}
