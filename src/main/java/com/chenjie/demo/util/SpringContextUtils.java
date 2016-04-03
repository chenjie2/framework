package com.chenjie.demo.util;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ClassUtils;
 
/**
 * ��ȡApplicationContext��Object�Ĺ�����
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
     * ��ȡapplicationContext����
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
     
    /**
     * ����bean��id�����Ҷ���
     * @param id
     * @return
     */
    public static Object getBeanById(String id){
        return applicationContext.getBean(id);
    }
     
    /**
     * ����bean��class�����Ҷ���
     * @param c
     * @return
     */
    public static Object getBeanByClass(Class c){
        return applicationContext.getBean(c);
    }
     
    /**
     * ����bean��class���������еĶ���(��������)
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
	 * ����springע���´���������������(��������������ע��)
	 * 
	 * @param bean
	 * @param appContext
	 */
	public static void autowireBean(Object bean, ApplicationContext appContext) {
		autowireBean(bean, appContext, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME);
	}

	/**
	 * ����springע���´���������������
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