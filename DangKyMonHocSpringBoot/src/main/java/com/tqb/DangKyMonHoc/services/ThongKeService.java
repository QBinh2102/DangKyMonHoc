/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import com.tqb.DangKyMonHoc.dto.ThongKeDTO;
import com.tqb.DangKyMonHoc.dto.ThongKeTheoLopDTO;
import java.util.List;

/**
 *
 * @author toquocbinh2102
 */
public interface ThongKeService {
    ThongKeDTO thongKe(int hocKyId, int khoaId, int monHocId);
    List<ThongKeTheoLopDTO> thongKeTheoLop(int hocKyId, int monHocId);
}
