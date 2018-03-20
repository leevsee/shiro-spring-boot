package com.example.shiro.redis;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.shiro.entity.UUser;
import com.example.shiro.service.IUUserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Description: TODO
 * Package: com.example.shiro.redis
 *
 * @author Leeves
 * @date 2018-03-20
 */
@Component
public class RedisCache {

    @Autowired
    IUUserService mIUUserService;

    @Cacheable(value = "user")
    public UUser getUerInfo(String nickName){
        EntityWrapper<UUser> ew = new EntityWrapper<>();
        ew.eq("nickname", nickName);
        return mIUUserService.selectOne(ew);
    }

    public UUser getUerInfo1(String nickName){
        EntityWrapper<UUser> ew = new EntityWrapper<>();
        ew.eq("nickname", nickName);
        return mIUUserService.selectOne(ew);
    }

    @CachePut(value = "user")
    public void updateUserInfo(String nickName){
        EntityWrapper<UUser> ew = new EntityWrapper<>();
        ew.eq("nickname", nickName);
        UUser uUser = mIUUserService.selectOne(ew);
        uUser.setEmail("12223");
        uUser.updateById();
    }
}
