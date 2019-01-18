package com.anleafworld.springcloud.springclouddemoclient;

import com.anleafworld.springcloud.springclouddemoclient.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaClientApplicationTests {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void contextLoads() {
        //得到一个连接
        redisUtil.set("test", "hello redis");
        System.out.println(redisUtil.get("test"));
    }

}
