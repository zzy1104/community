package com.newcoder.community.dao;


import org.springframework.stereotype.Repository;
// implement 实现一个接口
//扫描bean 必须要在controller包下
//需要添加4个注解当中的1个 才能满足自动扫描
@Repository("alphaHibernate")
public class AlphaDaoHibernatelmpl implements AlphaDao{
    @Override
    public String select() {
        return "Hibernate";
    }

}
