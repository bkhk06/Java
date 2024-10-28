package com.adcc.aopredis.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    @Cache(name = "get method")
    //@Cacheable(cacheNames = {"get"})
    public Result get(@PathVariable("id") Integer id){
        return userService.get(id);
    }

    @PostMapping("/updateData")
    @ClearAndReloadCache(name = "get method")
    public Result updateData(@RequestBody User user){
        return userService.update(user);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody User user){
        return userService.insert(user);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        return userService.delete(id);
    }
}
