package com.anleafworld.springcloudzuulapigateway.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocalForwardController {

    @GetMapping("/local/hello")
    public String localHello() {
        return "一沙一世界，一叶一菩提。 双手握无限， 刹那是永恒。";
    }
}
