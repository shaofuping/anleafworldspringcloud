package com.anleafworld.springclouddemoeurekafeignconsumer.controller;

import com.anleafworld.springclouddemoeurekafeignconsumer.service.HelloService;
import com.anleafworld.springclouddemoeurekafeignconsumer.service.UserService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserConsumerController {
    @Autowired
    private UserService userService;
    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public List getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public Object getUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() throws Exception{
        return helloService.sayHello();
    }
}
