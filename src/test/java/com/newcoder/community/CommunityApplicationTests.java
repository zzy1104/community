package com.newcoder.community;

import com.newcoder.community.dao.AlphaDao;
import com.newcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
//加这句是为了也用刚刚的communication配置类
public class CommunityApplicationTests implements ApplicationContextAware {
//
//	@Test
//	void contextLoads() {
//	}
	private ApplicationContext applicationContext;
	@Override
//	这里面的 ApplicationContext是接口，用了这个的set 方法
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext= applicationContext;
	}
	@Test
	public void testApplicationContext() {
		//运行结果说明这容器可用
		System.out.println(applicationContext);

		//这个applicationContext容器怎么管理bean呢
		// getBean是获取bean的方法  下面是按照（接口）类型获取
		//比方说我们要增加bean的时候，不需要动main中的代码，因为这部分依赖的是接口类型，不是bean本身
		//增加的时候只需要往需要的bean里面添加 @Primary
		AlphaDao alphaDao =applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());

		//在我添加@Primary之后，如果我想访问另一个bean呢？
		alphaDao = applicationContext.getBean("alphaHibernate",AlphaDao.class);
		System.out.println(alphaDao.select());
	}

	@Test
	public void testBeanManagement(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat =
				applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired
	//依赖注入注解 希望 Spring容器能够吧AlphaDao 直接注入给alphaDao，不需要用getBean（）
	@Qualifier("alphaHibernate")
	//这句是因为不想要AlphaDao中的默认bean
	private AlphaDao alphaDao;


	@Autowired
	private AlphaService alphaService;

	@Test
	public void teatDI(){
		System.out.println(alphaDao);
		System.out.println(alphaService);
	}
}
