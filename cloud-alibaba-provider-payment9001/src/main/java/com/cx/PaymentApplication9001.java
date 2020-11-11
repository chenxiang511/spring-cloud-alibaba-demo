package com.cx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author cx
 * @Date 2020/11/11 9:35
 * @Version 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentApplication9001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication9001.class,args);
    }
}
