package com.liyanxing.shiro;

import com.liyanxing.mapper.AdminUserMapper;
import com.liyanxing.mapper.CommonUserMapper;
import com.liyanxing.pojo.AdminUser;
import com.liyanxing.pojo.CommonUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MyRealm extends AuthorizingRealm
{
    @Autowired
    @Qualifier("commonUserMapper")
    private CommonUserMapper commonUserMapper;

    @Autowired
    @Qualifier("adminUserMapper")
    private AdminUserMapper adminUserMapper;

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        if(authenticationToken instanceof CommonUserToken) //普通用户的认证逻辑
        {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            String inputName = token.getUsername();
            CommonUser selectUser = commonUserMapper.selectAbyName(inputName);

            if(selectUser == null)
            {
                return null;
            }

            return new SimpleAuthenticationInfo(selectUser, selectUser.getPassword(), "");
        }
        else if(authenticationToken instanceof AdminUserToken) //管理员的谁逻辑
        {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            String inputName = token.getUsername();
            AdminUser selectUser = adminUserMapper.selectAbyName(inputName);

            if(selectUser == null)
            {
                return null;
            }

            return new SimpleAuthenticationInfo(selectUser, selectUser.getPassword(), "");
        }
        else
        {
            return null;
        }
    }

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        System.out.println("执行授权逻辑");
        return null;
    }
}