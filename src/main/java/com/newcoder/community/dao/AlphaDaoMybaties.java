package com.newcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
//加下面这个注解 是为了当两个bean公用一个接口时，加了之后，当前bean具有优先级
@Primary

public class AlphaDaoMybaties implements AlphaDao{
    @Override
    public String select() {
        return "Mybaties";
    }
}
