package com.liyanxing.controller;

import com.liyanxing.mapper.CommonUserMapper;
import com.liyanxing.pojo.CommonUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController
{
    @Autowired
    @Qualifier("commonUserMapper")
    private CommonUserMapper mapper;

    @GetMapping("/test")
    @ResponseBody
    public CommonUser Test()
    {
        return mapper.selectAbyName("李艳兴");
    }

    @GetMapping("/toCommonUserLogin")
    public String toCommonUserLogin()
    {
        return "commonUserLogin";
    }

    @GetMapping("/toAdminUserLogin")
    public String toAdminUserLogin()
    {
        return "adminUserLogin";
    }
}