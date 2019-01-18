package com.anleafworld.springcloud.springclouddemoclient.service;

import com.anleafworld.springcloud.springclouddemoclient.apimodel.WidelyResult;
import com.anleafworld.springcloud.springclouddemoclient.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(Integer id);

    List<User> getAllUser( Integer page, Integer row );

    WidelyResult modifyUser(User user);

    WidelyResult delUserById(Integer id);
 }
