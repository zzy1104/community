package com.newcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
//表示这个类是一个配置类
public class AlphaConfig {
    @Bean
    //定义第三方的bean
    //要装配的类是 SimpleDateFormat，那么返回类型就为SimpleDateFormat 方法名为simpleDateFormat
    //方法名就是bean的名字
    //下面这一段的意思：这个simpleDateFormat方法 的返回的对象，将被装配到容器里
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
