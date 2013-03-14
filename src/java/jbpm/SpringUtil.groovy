package jbpm

/**
 * Created with IntelliJ IDEA.
 * User: FZHANG
 * Date: 12/20/12
 * Time: 5:49 PM
 * To change this template use File | Settings | File Templates.
 */
import org.springframework.context.ApplicationContext;
import org.codehaus.groovy.grails.web.context.ServletContextHolder;
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes;

class SpringUtil {

    /**
     * 实例化ApplicationContext容器
     * @return
     */
    static ApplicationContext getCtx() {
        return getApplicationContext();
    }

    /**
     * 实例化ApplicationContext容器
     * @return
     */
    static ApplicationContext getApplicationContext() {
        return (ApplicationContext) ServletContextHolder.getServletContext().getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT);
    }

    /**
     * 实例化bean对象
     * @param beanName
     * @return
     */
    @SuppressWarnings("unchecked")
    static <T> T getBean(String beanName) {
        return (T) getApplicationContext().getBean(beanName);
    }


    static org.codehaus.groovy.grails.commons.DefaultGrailsApplication getGrailsApplication(){
        return getBean("grailsApplication");
    }

    static groovy.util.ConfigObject getConfiguration() {
        return getGrailsApplication().config;
    }

}