package com.wxhw.springclouddemoconsumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigTestController {
    @Value("${from}")
    private String from;

    @RequestMapping("/from")
    public String getOuterConfig() {
        return this.from;
    }
}
