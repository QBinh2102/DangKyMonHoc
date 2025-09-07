/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.controllers;

import com.tqb.DangKyMonHoc.services.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author toquocbinh2102
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiVNPayController {

    @Autowired
    private VNPayService vnpayService;

    @GetMapping("/create-payment")
    public ResponseEntity<Map<String, Object>> createPayment(
            @RequestParam("hocPhiId") String hocPhiId,
            @RequestParam("amount") long amount,
            @RequestParam("bankCode") String bankCode,
            HttpServletRequest request) {

        try {
            String paymentUrl = vnpayService.createPayment(hocPhiId, amount, bankCode, request);

            return ResponseEntity.ok(Map.of(
                    "code", "00",
                    "message", "Success",
                    "paymentUrl", paymentUrl
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "code", "99",
                    "message", e.getMessage()
            ));
        }
    }

    @GetMapping("/payment-result")
    public ResponseEntity<Map<String, String>> handlePaymentResult(
            @RequestParam Map<String, String> params, HttpServletRequest request) {
        try {
            boolean isValid = vnpayService.validatePaymentResponse(params);

            if (!isValid) {
                return ResponseEntity.badRequest().body(Map.of(
                        "code", "97",
                        "message", "Invalid signature"
                ));
            }

            String responseCode = params.get("vnp_ResponseCode");
            if ("00".equals(responseCode)) {
                return ResponseEntity.ok(Map.of(
                        "code", "00",
                        "message", "Payment successful",
                        "transactionId", params.get("vnp_TransactionNo")
                ));
            } else {
                return ResponseEntity.ok(Map.of(
                        "code", responseCode,
                        "message", "Payment failed: " + params.getOrDefault("vnp_ResponseMessage", "Unknown")
                ));
            }

        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "code", "99",
                    "message", "Exception: " + ex.getMessage()
            ));
        }
    }

}
