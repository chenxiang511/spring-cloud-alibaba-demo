package com.cx.service;

import com.cx.entities.Payment;

/**
 * @Author cx
 * @Date 2020/11/11 10:57
 * @Version 1.0
 */

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
