package com.logicbig.example.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import java.util.Arrays;

@Configuration
public class ServletConfiguration {

    @Bean
    public ServletRegistrationBean jsfServletRegistration (ServletContext servletContext) {
        //Устанавливаем параметры контекста, по которому контейнер сервлетов будет настраивать веб-приложение
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

        //Регистрируем  JSF сервлет
        ServletRegistrationBean srb = new ServletRegistrationBean();
        srb.setServlet(new FacesServlet());
        srb.setUrlMappings(Arrays.asList("*.xhtml"));
        srb.setLoadOnStartup(1);
        return srb;
    }

}
