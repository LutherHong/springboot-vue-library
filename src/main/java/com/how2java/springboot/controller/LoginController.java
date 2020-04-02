package com.how2java.springboot.controller;

import com.how2java.springboot.pojo.User;
import com.how2java.springboot.result.Result;
import com.how2java.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    public Result login(@RequestBody User requestUser, HttpSession session) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = HtmlUtils.htmlEscape(requestUser.getUsername());

        User user = userService.get(username,requestUser.getPassword());
        if(null==user) {
            return new Result(400);
        }else{
            session.setAttribute("user",user);
            return new Result(200);
        }
    }

}
