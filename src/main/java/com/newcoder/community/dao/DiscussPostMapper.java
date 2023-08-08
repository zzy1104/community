package com.newcoder.community.dao;

import com.newcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
//写了注解 容器才能自动扫描这个接口 装配
public interface DiscussPostMapper {
    //传入userid 传入offset（是指每一页起始行号）limit（每一页最多显示多少数据），为后续分页做准备
    //后面可以拼sql
    List<DiscussPost> selectDiscussPosts( int userId, int offset, int limit);
    //计算一共多少页 首先需要查询出表里一共有多少条数据
    //这个@Param是为了给 字段起一个别名
    //如果只有一个参数，并且在<if>(sql)中使用，就必须加别名
    // 比方说需要动态的拼一个条件，并且这个方法有且只有一个条件，就必须起别名。这里有userID、offset、limit有三个参数，可以不区别名
    int selectDiscussPostRows(@Param("userId") int userId);




}
