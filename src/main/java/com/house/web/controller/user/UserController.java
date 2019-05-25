package com.house.web.controller.user;

import com.house.entity.User;
import com.house.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/findUser")
    @ResponseBody
    public User findUserByName(String name){
        return userService.findUserByName(name);
    }
}
