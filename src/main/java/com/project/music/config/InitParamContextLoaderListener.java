package com.project.music.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Slf4j
public class InitParamContextLoaderListener implements ServletContextListener {

    public InitParamContextLoaderListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        // 获取系统配置信息
        ServletContext servletContext = sce.getServletContext();

        WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
                .autowireBean(this);

        servletContext.setAttribute("ctx", servletContext.getContextPath());

    }

    public void contextDestroyed(ServletContextEvent arg0) {

    }

}