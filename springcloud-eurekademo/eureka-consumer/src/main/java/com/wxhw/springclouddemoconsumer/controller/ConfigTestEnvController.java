package com.wxhw.springclouddemoconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigTestEnvController {
    @Autowired
    private Environment env;

    @RequestMapping("/envfrom")
    public String envGetConfig() {
        return env.getProperty("from");
    }
}
