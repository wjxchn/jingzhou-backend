package com.example.jingzhou.Controller;

import com.example.jingzhou.Service.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @RequestMapping("/test")
    public Object test(){

        return  userService.allUser();
    }
}
