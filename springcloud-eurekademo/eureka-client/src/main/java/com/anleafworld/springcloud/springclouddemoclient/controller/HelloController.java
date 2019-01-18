package com.anleafworld.springcloud.springclouddemoclient.controller;

import com.netflix.appinfo.EurekaInstanceConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@Api(value = "入门测试", description = "入门测试调用")
public class HelloController {
    protected Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Value("${account.name}")
    private String name;
    @Value("${account.description}")
    private String description;
    @Value("${server.port}")
    private int port = 0;

    @Autowired
    private EurekaInstanceConfig eurekaInstanceConfig;

    @GetMapping("/hello")
    @ApiOperation(value = "hello", notes = "hello")
    public String sayHello() throws InterruptedException {
        long sleepTime = new Random().nextInt(3000);
        logger.info("sleepTime:  "+ sleepTime);
        Thread.sleep(sleepTime);
        this.logger.info("/hello, instanceId:{}, host:{}", eurekaInstanceConfig.getInstanceId(), eurekaInstanceConfig.getHostName(false));
        return name + " " + description + "   服务的端口号是：" + port;
    }

}
