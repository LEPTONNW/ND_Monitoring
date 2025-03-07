package com.nd_monitoring.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

@Configuration
public class MimeConfig {

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.getMimeType("ttf"); // MIME 타입 강제 등록
                servletContext.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
            }
        };
    }
}
