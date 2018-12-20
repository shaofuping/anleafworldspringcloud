package com.anleafworld.springcloud.springclouddemoclient.service.impl;

import com.anleafworld.springcloud.springclouddemoclient.apimodel.WidelyResult;
import com.anleafworld.springcloud.springclouddemoclient.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.anleafworld.springcloud.springclouddemoclient.dao.UserMapper;
import com.anleafworld.springcloud.springclouddemoclient.entity.User;
import com.anleafworld.springcloud.springclouddemoclient.entity.UserExample;
import com.anleafworld.springcloud.springclouddemoclient.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper mapper;
    @Autowired
    private RedisUtil util;

    @Override
    public User getUserById(Integer id) {
        User user = (User) util.get("user:" + id);
        /*if (null == user) {
            user = mapper.selectByPrimaryKey(id);
            util.set("user:" + id, user);
            System.out.println("查询数据库》》》》》》》》》》》》》》》");

            return user;
        }*/
        //双重检测锁，解决高并发情况下，缓存穿透的问题。
        if (null == user) {
            synchronized (this) {
                user = (User) util.get("user:" + id);
                if (null == user) {
                    user = mapper.selectByPrimaryKey(id);
                    if ( user != null )
                    util.set("user:" + id, user);
                    System.out.println("查询数据库。。。。。。。");
                }
                System.out.println("查询缓存。。。。。。。");

            }
        }
        System.out.println("查询缓存》》》》》》》》》》》》》");
        return user;
    }

    @Override
    public List<User> getAllUser(Integer page, Integer row) {
        PageHelper.startPage(page, row);
        UserExample abc = new UserExample();
        List<User> list = mapper.selectByExample(abc);
        PageInfo info = new PageInfo(list);
        return info.getList();
    }

    @Transactional
    @Override
    public WidelyResult modifyUser(User user) {
        UserExample example = new UserExample();
        mapper.updateByExampleSelective(user, example);
        return WidelyResult.ok();
    }

    @Override
    public WidelyResult delUserById(Integer id) {

        mapper.deleteByPrimaryKey(id);
        return WidelyResult.ok();
    }
}
