package com.anleafworld.springcloud.springclouddemoclient.controller;

import com.anleafworld.springcloud.springclouddemoclient.apimodel.WidelyResult;
import com.anleafworld.springcloud.springclouddemoclient.entity.User;
import com.anleafworld.springcloud.springclouddemoclient.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("user")
@Api(value = "用户相关接口", description = "用户相关")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("getUser")
    @ApiOperation(value = "获取用户", notes = "获取用户")
    public User getUser(Integer id) {
       /* //该线程调用底层查询方法   测试并发情况下缓存穿透
        Runnable run = new Runnable() {
            @Override
            public void run() {
                userService.getUserById(id);
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 1000; i++) {
            executorService.submit(run);

        }
*/

        return userService.getUserById(id);
    }

    @RequestMapping(value = "getAllUserPaging",method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "获取所有用户分页显示", notes = "获取所有用户")
    public List<User> getAllUser(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer row) {
        long sleepTime = new Random().nextInt(3000);
        logger.info("sleepTime:  "+ sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userService.getAllUser(page, row);
    }

    @PostMapping("delUser")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    public WidelyResult delUser(Integer id) {
        return userService.delUserById(id);
    }

    @PostMapping("modifyUser")
    @ApiOperation(value = "修改用户", notes = "修改用户")
    public WidelyResult modifyUser(User user) {
        return userService.modifyUser(user);
    }


}


