package com.house.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {


    /**
     *
     * @return
     */
    @GetMapping("/admin/center")
    public String adminCenterPage(){
        return "admin/center";
    }

    /**
     * 欢迎界面
     * @return
     */
    @GetMapping("/admin/welcome")
    public String welcomePage(){
        return "admin/welcome";
    }

    /**
     * 登录界面
     * @return
     */
    @GetMapping("/admin/login")
    public String loginPage(){
        return "admin/login";
    }
}
