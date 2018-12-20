package com.anleafworld.springclouddemoeurekafeignconsumer.service;

import com.anleafworld.springclouddemoeurekafeignconsumer.entity.User;
import com.anleafworld.springclouddemoeurekafeignconsumer.service.impl.FallbackUserServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@FeignClient(value = "provide-hello", configuration = DisableHystrixConfiguration.class)针对指定服务客户端关闭hystrix服务 */

@FeignClient("provide-hello")
public interface UserService {
    @RequestMapping(value = "/user/getAllUserPaging",method = RequestMethod.POST)
    List getAllUser();

    @RequestMapping(value = "/user/getUser", method = RequestMethod.POST)
    User getUser(@RequestParam("id") Integer id);

}
