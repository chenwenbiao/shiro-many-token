package com.liyanxing.mapper;

import com.liyanxing.pojo.AdminUser;
import com.liyanxing.pojo.CommonUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("adminUserMapper")
public interface AdminUserMapper
{
    @Select("select * from admin_user")
    List<AdminUser> selectAllAdminUser();

    /**
     * 根据用户名查询一个管理员
     * @param name
     * @return
     */
    @Select("select * from admin_user where `name`=#{name}")
    AdminUser selectAbyName(String name);
}