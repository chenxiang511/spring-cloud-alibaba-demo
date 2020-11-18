package com.cx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author cx
 * @Date 2020/11/12 14:36
 * @Version 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
public class OrderApplication90 {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication90.class,args);
    }
}
