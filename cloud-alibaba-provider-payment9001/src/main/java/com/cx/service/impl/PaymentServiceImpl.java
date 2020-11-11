package com.cx.service.impl;

import com.cx.dao.PaymentDao;
import com.cx.entities.Payment;
import com.cx.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author cx
 * @Date 2020/11/11 10:58
 * @Version 1.0
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
