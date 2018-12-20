package com.anleafworld.springclouddemoeurekafeignconsumer.service.impl;

import com.anleafworld.springclouddemoeurekafeignconsumer.entity.User;
import com.anleafworld.springclouddemoeurekafeignconsumer.service.UserService;

import java.util.Collections;
import java.util.List;

public class FallbackUserServiceImpl implements UserService {
    @Override
    public List getAllUser() {
        return Collections.emptyList();
    }

    @Override
    public User getUser(Integer id) {
        User us = new User();
        us.setUserName("断路器熔断");
        us.setLoginAccount("请稍后重试");
        return us;
    }
}
