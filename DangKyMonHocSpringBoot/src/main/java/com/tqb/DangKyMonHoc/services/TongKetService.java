/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.dto.TongKetHocKyDTO;
import java.util.List;

/**
 *
 * @author toquocbinh2102
 */
public interface TongKetService {
    List<TongKetHocKyDTO> getTongKetHocKy(int sinhVienId);
}
