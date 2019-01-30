package com.liyanxing.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class ShiroConfig
{
    /**
     * 这个方法关联一个安全管理器
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager defaultWebSecurityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager); //关联安全管理器

        return  shiroFilterFactoryBean;
    }

    /**
     * 获得一个安全管理器
     * 这个方法关联一个realm类
     * @param commonUserRealm
     * @return
     */
    @Bean(name = "manager")
    @Autowired
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realm") MyRealm myRealm)
    {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);

        return manager;
    }

    /**
     * 获得realm类
     * @return
     */
    @Bean(name = "realm")
    public MyRealm getRealm()
    {
        return new MyRealm();
    }
    @Bean(name = "shiroDialect")
    public ShiroDialect getShiroDialect()
    {
        return new ShiroDialect();
    }
}