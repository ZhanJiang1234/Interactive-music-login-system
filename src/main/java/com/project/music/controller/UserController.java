package com.project.music.controller;


import com.project.music.common.Result;
import com.project.music.entity.User;
import com.project.music.service.IUserService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author test
 * @since 2023-11-19
 */
@Controller
public class UserController {

    @Resource
    private IUserService iUserService;

    @GetMapping(value = "/register")
    public String register(){
        return "register";
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public Result register(@RequestBody User user){
        iUserService.save(user);
        return Result.success();
    }
}
