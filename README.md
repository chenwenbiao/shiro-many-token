#### <center><font color="blue">写在前边</font></center>
- 现在的时间：2019-1-30
- 所用技术：springboot、shiro、mysql、mybatis
---
#### <center><font color="blue">问题引出</font></center>
&ensp;&ensp;&ensp;&ensp;一个网站中一般都会有多种角色，比如普通用户和管理员。不同的角色的用户有可以会存在不同的用户表中，比如普通用户存一张表，管理员存一张表。
&ensp;&ensp;&ensp;&ensp;有多张用户表的话，每张表的登录逻辑就有可以不同，所有的查询接口也不一样，这样就需要分开登录。

---
#### <center><font color="blue">使用多Token使不同的用户分开登录</font></center>
- 一般的登录，控制器接收到用户登录的数据后将用户输入的用户名和密码封装到`UsernamePasswordToken`的对象中，然后在自写定义的Realm类的`doGetAuthenticationInfo`方法中执行认证逻辑。
- 可以为每一张用户表创建一个`UsernamePasswordToken`的子类，在控制器中接受到用户登录的数据后使用子类封装，进入`doGetAuthenticationInfo`方法后判断Token的类型，从而进行不同的认证逻辑。
- 示例代码
```java
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
    else if(authenticationToken instanceof AdminUserToken) //管理员的认证逻辑
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
```
