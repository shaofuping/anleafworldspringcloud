package com.anleafworld.springclouddemoeurekafeignconsumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("provide-hello")
public interface HelloService {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() throws InterruptedException;
}
