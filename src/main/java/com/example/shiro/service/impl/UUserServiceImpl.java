package com.example.shiro.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.shiro.dao.UUserMapper;
import com.example.shiro.entity.UUser;
import com.example.shiro.service.IUUserService;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leeves123
 * @since 2018-03-18
 */
@Service
public class UUserServiceImpl extends ServiceImpl<UUserMapper, UUser> implements IUUserService {

}
