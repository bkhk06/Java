package com.adcc.aopredis.service;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * service层
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public Result get(Integer id){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId,id);
        User user = userMapper.selectOne(wrapper);
        return Result.success(user);
    }

    public Result insert(User user){
        int line = userMapper.insert(user);
        if(line > 0)
            return Result.success(line);
        return Result.fail(888,"操作数据库失败");
    }

    public Result delete(Integer id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, id);
        int line = userMapper.delete(wrapper);
        if (line > 0)
            return Result.success(line);
        return Result.fail(888, "操作数据库失败");
    }

    public Result update(User user){
        int i = userMapper.updateById(user);
        if(i > 0)
            return Result.success(i);
        return Result.fail(888,"操作数据库失败");
    }
}
