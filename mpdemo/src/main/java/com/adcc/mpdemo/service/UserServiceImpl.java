package com.adcc.mpdemo.service;



import com.adcc.mpdemo.entity.User;
import com.adcc.mpdemo.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * @author ld
 * @date 2024-04-28 15:42
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}

