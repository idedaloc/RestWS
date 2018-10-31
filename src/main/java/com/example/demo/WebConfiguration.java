package com.example.demo;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.h2.server.web.WebServlet;

@Configuration
public class WebConfiguration {

    private static final String mapping = "/console/*";

    @Bean
    public ServletRegistrationBean<WebServlet>  h2servletRegistration(){
        ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<WebServlet> ( new WebServlet());
        registrationBean.addUrlMappings(mapping);
        return registrationBean;
    }
    
}