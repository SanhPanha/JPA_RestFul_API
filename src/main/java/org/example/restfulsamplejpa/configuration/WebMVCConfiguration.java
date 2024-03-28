package org.example.restfulsamplejpa.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMVCConfiguration implements WebMvcConfigurer {

    @Value("${file_storage.image_location}")
    String serverPath;

    @Value("${file_storage.client_path}")
    String clientPath;


//    This is the config for image
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(clientPath)
                .addResourceLocations("file:" + serverPath);
    }
//    This is the CORs
}
