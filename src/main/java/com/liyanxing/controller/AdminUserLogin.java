package com.liyanxing.controller;

import com.liyanxing.pojo.AdminUser;
import com.liyanxing.shiro.AdminUserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminUserLogin
{
    /**
     * 管理员登录
     * @param user
     * @return
     */
    @PostMapping("/adminUserLogin")
    public String adminUserLogin(AdminUser user)
    {
        UsernamePasswordToken token = new AdminUserToken(user.getName(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return "index";
        }
        catch (UnknownAccountException e)
        {
            System.out.println("用户不存在");
            return "adminUserLogin";
        }
        catch (IncorrectCredentialsException e)
        {
            System.out.println("密码错误");
            return "adminUserLogin";
        }
    }
}