package com.newcoder.community.service;

import com.newcoder.community.config.AlphaConfig;
import com.newcoder.community.dao.AlphaDao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
//@Scope("prototype")
//这个注解是为了能保证每次getbean都可以创建一个新的实例
public class AlphaService {
    //调用dao
    @Autowired
    private AlphaDao alphaDao;
    public String find(){
        return alphaDao.select();
    }

    //通过容器管理这个bean的初始化和销毁的方法
    //1、首先给这个bean增加初始化方法
    //2、让容器管理 实际是让容器在合适的时候自动调用这个方法----在这个方法之前加上 注解@PostConstruct
    public AlphaService()
    {
        System.out.println("实例化AlphaService");
    }

    @PostConstruct
    //注解器：表明这个初始化方法会在构造（实例化）之后调用
    public void init(){
        System.out.println("初始化AlphaService");
    }

    @PreDestroy
    //注解器：在销毁对象之前调用  可以释放资源
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

}
