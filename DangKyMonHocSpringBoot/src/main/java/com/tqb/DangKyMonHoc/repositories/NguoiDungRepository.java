/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {

    NguoiDung findById(int id);

    NguoiDung findByEmailAndMatKhau(String email, String matKhau);

    NguoiDung findByEmail(String email);

}
