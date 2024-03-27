package com.project.music.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.music.common.Globals;
import com.project.music.common.Result;
import com.project.music.entity.User;
import com.project.music.model.LoginDto;
import com.project.music.service.IUserService;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LoginController {

    @Resource
    private IUserService iUserService;

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody LoginDto loginDto, HttpSession session){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount, loginDto.getAccount());
        User one = iUserService.getOne(queryWrapper, false);
        if(Objects.nonNull(one)) {
            String password = one.getPassword();
            if(StrUtil.equals(password, loginDto.getPassword())){
                session.setAttribute(Globals.USER_SESSION, one);
                return Result.success("登录成功");
            }
        }
        return Result.fail("登录失败:账号或者密码不正确");
    }


    @GetMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute(Globals.USER_SESSION);
        return "redirect:/index";
    }
}
