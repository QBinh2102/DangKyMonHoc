/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tqb.DangKyMonHoc.services;

import jakarta.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface VNPayService {

    String createPayment(String hocPhiId, long amount, String bankCode, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    String hmacSHA512(String key, String data) throws NoSuchAlgorithmException;

    public boolean validatePaymentResponse(Map<String, String> params);

}
