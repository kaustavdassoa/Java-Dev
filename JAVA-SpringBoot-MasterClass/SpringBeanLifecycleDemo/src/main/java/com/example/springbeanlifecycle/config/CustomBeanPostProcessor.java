package com.example.springbeanlifecycle.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof BeanLifeCycleConfiguration){
	            ((BeanLifeCycleConfiguration) bean).beforeInit();
	     }
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof BeanLifeCycleConfiguration){
	            ((BeanLifeCycleConfiguration) bean).afterInit();
	     }
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}

}
