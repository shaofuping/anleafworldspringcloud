package com.wxhw.springclouddemo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/index")
    public String helloCloud() {
        return "welcom springcloud";
    }
}
