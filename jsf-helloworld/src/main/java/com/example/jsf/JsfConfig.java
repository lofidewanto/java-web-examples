package com.example.jsf;

import org.apache.myfaces.webapp.MyFacesContainerInitializer;
import org.apache.myfaces.webapp.StartupServletContextListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.faces.application.ProjectStage;
import jakarta.faces.component.UIInput;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;

@Configuration
public class JsfConfig {
    @Bean
    public ServletContextInitializer jsfServletContextInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext context) throws ServletException {
                // Optional JSF init params
                context.setInitParameter(UIInput.EMPTY_STRING_AS_NULL_PARAM_NAME,
                        Boolean.TRUE.toString());
                context.setInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME,
                        ProjectStage.Development.name());

                // Start MyFaces (FacesServlet) container
                ServletContainerInitializer myFacesInit = new MyFacesContainerInitializer();
                myFacesInit.onStartup(null, context);
            }
        };
    }

    @Bean
    public ServletListenerRegistrationBean<ServletContextListener> facesStartupListener() {
        // Register the MyFaces StartupServletContextListener
        ServletListenerRegistrationBean<ServletContextListener> bean = new ServletListenerRegistrationBean<>();
        bean.setListener(new StartupServletContextListener());
        return bean;
    }
}
