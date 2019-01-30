package com.liyanxing.mapper;

import com.liyanxing.pojo.CommonUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("commonUserMapper")
public interface CommonUserMapper
{
    @Select("select * from common_user")
    List<CommonUser> selectAllCommonUser();

    /**
     * 根据用户名查询一个普通用户
     * @param name
     * @return
     */
    @Select("select * from common_user where `name`=#{name}")
    CommonUser selectAbyName(String name);
}