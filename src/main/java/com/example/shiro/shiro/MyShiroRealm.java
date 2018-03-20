package com.example.shiro.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.shiro.dao.UUserMapper;
import com.example.shiro.entity.UUser;
import com.example.shiro.service.IUUserService;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: TODO
 * Package: com.example.shiro.shiro
 *
 * @author Leeves
 * @date 2018-03-18
 */

public class MyShiroRealm extends AuthorizingRealm {

    private static Logger log = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private IUUserService mIUUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        String username = (String) authenticationToken.getPrincipal();
        log.info("username:"+username);
        EntityWrapper<UUser> ew = new EntityWrapper<>();
        ew.eq("nickname", username);

        UUser uUser = mIUUserService.selectOne(ew);

        if (uUser == null) {
            throw new AccountException("帐号或密码不正确！");
        } else if (uUser.getStatus() == 0) {

            // 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
            throw new DisabledAccountException("帐号已经禁止登录！");
        }else {
            //更新登录时间 last login time
            uUser.setLastLoginTime(new Date());
            mIUUserService.updateById(uUser);
        }

        return new SimpleAuthenticationInfo(uUser, uUser.getPswd(), getName());

/*        ShiroToken token = (ShiroToken) authenticationToken;
        Map<String, Object> map = new HashMap<>();
        map.put("nickname", token.getUsername());
        map.put("pswd", token.getPswd());
        UUser user = null;
        // 从数据库获取对应用户名密码的用户
        List<UUser> userList = UUserMapper.selectByMap(map);
        if (userList.size() != 0) {
            user = userList.get(0);
        }
        if (null == user) {
            throw new AccountException("帐号或密码不正确！");
        } else if (user.getStatus() == 0) {
            *//**
         * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
         *//*
            throw new DisabledAccountException("帐号已经禁止登录！");
        } else {
            //更新登录时间 last login time
            user.setLastLoginTime(new Date());
            sysUserService.updateById(user);
        }
        return new SimpleAuthenticationInfo(user, user.getPswd(), getName());*/
    }
}