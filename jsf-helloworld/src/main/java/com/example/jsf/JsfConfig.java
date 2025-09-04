package com.example.jsf;

import org.apache.myfaces.webapp.StartupServletContextListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

import jakarta.faces.webapp.FacesServlet;
import jakarta.servlet.ServletContextListener;

@Configuration
public class JsfConfig {

    /**
     * Register the JSF FacesServlet for handling *.xhtml requests.
     * Set load-on-startup to ensure JSF initializes early.
     */
    @Bean
    public ServletRegistrationBean<FacesServlet> facesServletRegistration() {
        ServletRegistrationBean<FacesServlet> registration = new ServletRegistrationBean<>(new FacesServlet(),
                "*.xhtml");
        registration.setLoadOnStartup(1);
        return registration;
    }

    /**
     * Set JSF context parameters. The critical one is forceLoadConfiguration=true
     * to load JSF config without web.xml:contentReference[oaicite:1]{index=1}. We
     * also skip facelets comments.
     */
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            servletContext.setInitParameter(
                    "com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
            servletContext.setInitParameter(
                    "jakarta.faces.FACELETS_SKIP_COMMENTS", "true");
        };
    }

    /**
     * To use Spring-managed session/request-scoped beans outside of Spring MVC,
     * register the RequestContextListener. This allows @SessionScope to
     * work:contentReference[oaicite:2]{index=2}.
     */
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public ServletListenerRegistrationBean<ServletContextListener> myfacesStartupListener() {
        return new ServletListenerRegistrationBean<>(new StartupServletContextListener());
    }
}
