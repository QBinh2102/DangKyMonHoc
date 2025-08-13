/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.pojo;

/**
 *
 * @author toquocbinh2102
 */
public class ThongKeDTO {
    private Long hoanThanh;
    private Long truot;

    public ThongKeDTO(Long hoanThanh, Long truot) {
        this.hoanThanh = hoanThanh;
        this.truot = truot;
    }

    public Long getHoanThanh(){
        return this.hoanThanh;
    }
    
    public void setHoanThanh(Long hoanThanh){
        this.hoanThanh = hoanThanh;
    }
    
    public Long getTruot(){
        return this.truot;
    }
    
    public void setTruot(Long truot){
        this.truot = truot;
    }
}

