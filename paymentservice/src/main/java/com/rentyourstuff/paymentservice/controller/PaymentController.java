package com.rentyourstuff.paymentservice.controller;

import com.rentyourstuff.paymentservice.entity.Payment;
import com.rentyourstuff.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Endpoint to process a payment
    @PostMapping
    public Payment processPayment(@RequestBody Payment payment) {
        return paymentService.processPaymentService(payment.getOrderId(), payment.getAmount());
    }

    // Endpoint to get the payment status
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentStatus(@PathVariable Long paymentId) {
        return paymentService.getPaymentStatus(paymentId);
    }
}
