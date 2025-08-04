/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.repositories;

import com.tqb.DangKyMonHoc.pojo.NganhMonHoc;
import com.tqb.DangKyMonHoc.pojo.NganhMonHocPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author toquocbinh2102
 */
public interface NganhMonHocRepository extends JpaRepository<NganhMonHoc, NganhMonHocPK> {
    
    List<NganhMonHoc> findByNganhId_Id(int nganhId);
    List<NganhMonHoc> findByMonHocId_Id(int monHocId);
    List<NganhMonHoc> findByNganhId_IdAndMonHocId_Id(int nganhId, int monHocId);
    List<NganhMonHoc> findAllByOrderByNganhId_IdAscMonHocId_IdAsc();
    
}
