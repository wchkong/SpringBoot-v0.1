package com.wck.controller;

import com.wck.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//controller里的方法都以json格式输出
public class HelloWorldController {

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
}
