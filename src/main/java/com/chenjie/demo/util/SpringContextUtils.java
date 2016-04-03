package com.chenjie.demo.util;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ClassUtils;
 
/**
 * 获取ApplicationContext和Object的工具类
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
 
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        applicationContext = arg0;
    }
 
    /**
     * 获取applicationContext对象
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
     
    /**
     * 根据bean的id来查找对象
     * @param id
     * @return
     */
    public static Object getBeanById(String id){
        return applicationContext.getBean(id);
    }
     
    /**
     * 根据bean的class来查找对象
     * @param c
     * @return
     */
    public static Object getBeanByClass(Class c){
        return applicationContext.getBean(c);
    }
     
    /**
     * 根据bean的class来查找所有的对象(包括子类)
     * @param c
     * @return
     */
    public static Map getBeansByClass(Class c){
        return applicationContext.getBeansOfType(c);
    }
    
    
	
    public static void autowire(Object bean) {
        getApplicationContext().getAutowireCapableBeanFactory().autowireBean(bean);
    }
    
    public static void autowireBeanProperties(Object existingBean,
            int autowireMode, boolean dependencyCheck) {
    	getApplicationContext().getAutowireCapableBeanFactory()
                .autowireBeanProperties(existingBean,
                    autowireMode, dependencyCheck);
    }
    
    /**
	 * 调用spring注入新创建对象的相关属性(根据属性名进行注入)
	 * 
	 * @param bean
	 * @param appContext
	 */
	public static void autowireBean(Object bean, ApplicationContext appContext) {
		autowireBean(bean, appContext, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME);
	}

	/**
	 * 调用spring注入新创建对象的相关属性
	 * 
	 * @param bean
	 * @param appContext
	 * @param autowireMode
	 */
	public static void autowireBean(Object bean, ApplicationContext appContext, int autowireMode) {
		if (bean == null || appContext == null) {
			return;
		}

		AutowireCapableBeanFactory factory = appContext.getAutowireCapableBeanFactory();
		factory.autowireBeanProperties(bean, autowireMode, false);

		String beanName = ClassUtils.getUserClass(bean).getName();
		factory.initializeBean(bean, beanName);
	}
}