package edu.wildbody.imgserver.Config;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class Config implements WebMvcConfigurer {

    private final String uploadImagePath;

    public Config(@Value("${custom.path.upload-images}") String uploadImagePath) {
        this.uploadImagePath = uploadImagePath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        List<String> imagesFolders = Arrays.asList("logo", "profile", "thumbnail");

        for (String imageFolder : imagesFolders) {
            registry.addResourceHandler("/img/" + imageFolder + "/**")
                .addResourceLocations("file:///" + uploadImagePath +"/"+ imageFolder + "/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

        }
    }
}
