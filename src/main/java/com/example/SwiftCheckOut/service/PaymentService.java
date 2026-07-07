package com.example.SwiftCheckOut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SwiftCheckOut.entity.Payment;
import com.example.SwiftCheckOut.repository.PaymentRepository;

@Service
public class PaymentService {

@Autowired
PaymentRepository paymentRepository;

    public Payment addPayment(Payment payment) {
        if (payment.getPaymentMethod() == null) {
            payment.setPaymentMethod("CREDIT_CARD");
        }
        payment.setStatus("SUCCESS");
        return paymentRepository.save(payment);
    }

    
}
