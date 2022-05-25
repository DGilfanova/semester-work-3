package ru.kpfu.itis.hauss.utils.mail.config;

import freemarker.cache.FileTemplateLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.io.File;
import java.io.IOException;

@Configuration
public class TemplateConfig {

    @Value("${mail.template.path}")
    private String templatePath;

    @Bean
    public FreeMarkerConfigurationFactoryBean configurationFactoryBean() {
        FreeMarkerConfigurationFactoryBean configurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
        configurationFactoryBean.setTemplateLoaderPath(templatePath);
        return configurationFactoryBean;
    }

    @Bean
    public freemarker.template.Configuration configuration() {
        return configurationFactoryBean().getObject();
    }
}
