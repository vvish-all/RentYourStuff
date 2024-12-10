package com.rentyourstuff.paymentservice.repository;

import com.rentyourstuff.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Add custom queries if needed
}
