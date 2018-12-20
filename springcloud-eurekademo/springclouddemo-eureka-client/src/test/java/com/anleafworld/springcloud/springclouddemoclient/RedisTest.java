package com.anleafworld.springcloud.springclouddemoclient;

import com.anleafworld.springcloud.springclouddemoclient.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringclouddemoclientApplication.class)
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testRedis(){
        //得到一个连接
        redisUtil.set("test", "hello redis");
        System.out.println(redisUtil.get("test"));
    }

}
