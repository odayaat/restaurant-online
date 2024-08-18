package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for customizing Spring MVC settings.
 * <p>
 * This class implements the {@link WebMvcConfigurer} interface
 * and provides custom configurations for resource handling.
 *
 * @see WebMvcConfigurer
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Adds resource handlers to serve static resources.
     * <p>
     * This method maps all requests ("/**") to the classpath location
     * "/static/", allowing the application to serve static resources
     * from this directory.
     *
     * @param registry the {@link ResourceHandlerRegistry} used to register resource handlers
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}
