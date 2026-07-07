package com.example.SwiftCheckOut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwiftCheckOut.entity.Payment;
import com.example.SwiftCheckOut.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    
    @Autowired
    PaymentService paymentService;

    @PostMapping("/addpay")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {
        Payment saved = paymentService.addPayment(payment);
        return ResponseEntity.ok(saved);
    }
}
