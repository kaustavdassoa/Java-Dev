package com.example.springbeanlifecycle.config;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class BeanLifeCycleConfiguration implements InitializingBean, DisposableBean, BeanNameAware,
BeanFactoryAware, ApplicationContextAware{

	private final static Logger logger = LoggerFactory.getLogger(BeanLifeCycleConfiguration.class);
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.info("inside BeanLifeCycleConfiguration.setApplicationContext() method");
		
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		logger.info("inside BeanLifeCycleConfiguration.setBeanFactory() method");
		
	}

	@Override
	public void setBeanName(String name) {
		logger.info("inside BeanLifeCycleConfiguration.setBeanName() method");
		
	}

	@Override
	public void destroy() throws Exception {
		logger.info("inside BeanLifeCycleConfiguration.destroy() method");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("inside BeanLifeCycleConfiguration.afterPropertiesSet() method");
		
	}
	
	@PreDestroy
	public void preDistory()
	{
		logger.info("inside BeanLifeCycleConfiguration.preDistory() method");
	}
	
	
	public void beforeInit(){
		logger.info("inside BeanLifeCycleConfiguration.beforeInit() method Before Init - Called by Bean Post Processor");
	}

	public void afterInit(){
		logger.info("inside BeanLifeCycleConfiguration.beforeInit() method after Init - Called by Bean Post Processor");
	}

}
