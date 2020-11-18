package com.cx.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cx.entities.CommonResult;
import com.cx.entities.Payment;
import com.cx.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author cx
 * @Date 2020/11/11 11:06
 * @Version 1.0
 */

@RestController
@Slf4j
@RefreshScope
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${config.info}")
    private String info;

    @Value("${server.port}")
    private  String serverPort;

    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        try{
            int result=paymentService.create(payment);
            if(result>0){
                return new CommonResult<>(200,"数据插入成功"+serverPort, result);
            }else {
                return new CommonResult<>(444,"数据插入失败",0);
            }
        }catch (Exception e){
            e.printStackTrace();
            return  new CommonResult<>(500,"系统内部异常，请联系管理员",0);
        }
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        try {
            Payment payment=paymentService.getPaymentById(id);
            return new CommonResult<>(200,"数据查询成功"+serverPort,payment);
        }catch (Exception e){
            e.printStackTrace();
            return  new CommonResult<>(500,"系统内部异常，请联系管理员",null);
        }
    }

    @GetMapping("/getDiscovery")
    public Object getDiscovery(){
        List<String> services = discoveryClient.getServices();
        for(String service : services){
            log.info("*********服务名："+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance : instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    /**
     * 读取nacos配置中心的配置文件
     * @return config.info
     */
    @GetMapping("/getConfigInfo")
    public String getConfigInfo(){ return info; }

    /**
     * 热点规则限流
     * @param a 参数0
     * @param b 参数1
     * @return String
     * ps:参数例外项配置值时，http://localhost:9001/payment/testHotKey?b=abc，访问url，b=abc，不要加引号
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "testHotKeyHandler")
    public String testHotKey(@RequestParam(value = "a",required = false) String a,@RequestParam(value = "b",required = false)String b){
        return "testHotKey:正常访问!"+"\t"+serverPort;
    }

    public String testHotKeyHandler(String a, String b, BlockException e){
        return "testHotKey:限流处理返回"+"\t"+serverPort;
    }

}
