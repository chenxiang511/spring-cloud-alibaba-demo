package com.cx.controller;

import com.cx.entities.CommonResult;
import com.cx.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author cx
 * @Date 2020/11/12 14:41
 * @Version 1.0
 */

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    public static final String PAYMENT_URL= "http://cloud-alibaba-payment-service";

    @GetMapping("payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){

        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResult.class);
    }

    @GetMapping("payment/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id){

        return  restTemplate.getForObject(PAYMENT_URL+"/payment/getPaymentById/"+id,CommonResult.class);
    }
}
