package com.cyj.piano_backend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;
import java.util.Objects;

/**
 * @author changyingjie
 */
public class WebAppConfig extends WebMvcConfigurerAdapter {

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${cyj.filePath}")
    private String filePath;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize("1024MB");
        //设置总上传数据总大小
        factory.setMaxRequestSize("1024MB");
        return factory.createMultipartConfig();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (filePath.equals("") || filePath.equals("${cbs.imagesPath}")) {
            String imagesPath = Objects.requireNonNull(WebAppConfig.class.getClassLoader().getResource("")).getPath();
            System.out.print("1.上传配置类imagesPath==" + imagesPath + "\n");
            if (imagesPath.indexOf(".jar") > 0) {
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            } else if (imagesPath.indexOf("classes") > 0) {
                imagesPath = "file:" + imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/")) + "/images/";
            filePath = imagesPath;
        }
        System.out.print("imagesPath=============" + filePath + "\n");
        //LoggerFactory.getLogger(WebAppConfig.class).info("imagesPath============="+filePath+"\n");
        registry.addResourceHandler("/images/**").addResourceLocations(filePath);
        // TODO Auto-generated method stub
        System.out.print("2.上传配置类filePath==" + filePath + "\n");
        super.addResourceHandlers(registry);
    }

}
