package com.adcc.mpdemo.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adcc.mpdemo.entity.User;
import com.adcc.mpdemo.service.UserService;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




/**
 * @author Eric
 * @date 2023-08-01 15:43
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> getUserList(){
        List<User> list = userService.list();
        return list;
    }

    @GetMapping("/findByid/{id}")
    public User findUserById(@PathVariable int id) {
        return userService.getById(id);
    }
    

    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) {
        //TODO: process POST request
              
        return userService.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delUser(@PathVariable int id){
        return userService.removeById(id);
    }

    @PutMapping("/update")
    public boolean uodateUser(@RequestBody User user) {
        //TODO: process PUT request
        
        return userService.saveOrUpdate(user);
    }
    
}



