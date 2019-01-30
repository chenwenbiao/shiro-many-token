package com.liyanxing.controller;

import com.liyanxing.pojo.CommonUser;
import com.liyanxing.shiro.CommonUserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommonUserLogin
{
    /**
     * 普通用户登录
     * @param user
     * @return
     */
    @PostMapping("/commonUserLogin")
    public String commonUserLogin(CommonUser user)
    {
        UsernamePasswordToken token = new CommonUserToken(user.getName(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return "index";
        }
        catch (UnknownAccountException e)
        {
            System.out.println("用户不存在");
            return "commonUserLogin";
        }
        catch (IncorrectCredentialsException e)
        {
            System.out.println("密码错误");
            return "commonUserLogin";
        }
    }
}