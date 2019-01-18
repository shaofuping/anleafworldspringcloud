package com.wxhw.springclouddemoconsumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class InvokeServiceController {
    protected Logger logger = LoggerFactory.getLogger(InvokeServiceController.class);

    //一、应用在RestTemplate上添加@LoadBalanced方式实现负载均衡
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/invoke")
    public String invokeServiceFromEureka() throws Exception{
        return restTemplate.getForEntity("http://PROVIDE-HELLO/hello", String.class).getBody();
    }

    //二、应用Ribbon的api实现的负载均衡
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("invokeApi")
    public String invokeServiceBalance() {
        ServiceInstance instance = this.loadBalancerClient.choose("provide-hello");
        URI uri = URI.create(String.format("http://%s:%s/hello",instance.getHost(), instance.getPort()));
        logger.info("Target service uri{}." + uri.toString());
        return new RestTemplate().getForEntity(uri,String.class).getBody();
    }

}
