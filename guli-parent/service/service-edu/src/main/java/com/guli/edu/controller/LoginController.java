package com.guli.edu.controller;

import com.guli.utils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: gali
 * @Date: 2022-09-02 15:26
 * @Description:
 */
@RestController
@RequestMapping("/edu/user")
@CrossOrigin
public class LoginController {
    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(){
        return Result.ok().data("token","admin");
    }
    /**
     * 登录信息
     */
    @GetMapping("info")
    public Result getInfo(){
        return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
