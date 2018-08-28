package com.wck.controller;

import com.wck.domain.User;
import com.wck.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//controller里的方法都以json格式输出
public class HelloWorldController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/hello")
//    @ResponseBody
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setUserName("xiaoming");
        user.setPassWord("aaa");
        return user;
    }

    @RequestMapping("/getUser2")
    @Cacheable(value = "user-key")
    public User getUser2() {
        User user = userRepository.findByUserName("aa");
        return user;
    }
}
