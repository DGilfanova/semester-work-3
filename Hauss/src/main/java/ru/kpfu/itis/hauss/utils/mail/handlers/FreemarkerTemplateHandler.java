package ru.kpfu.itis.hauss.utils.mail.handlers;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

@Component
public class FreemarkerTemplateHandler implements TemplateHandler {

    private final static String SUFFIX = ".ftlh";

    @Autowired
    private Configuration configuration;

    @Override
    public String getContentFromTemplate(String templateName, Map<String, String> data) {

        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    configuration.getTemplate(templateName + SUFFIX), data));
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }

        return content.toString();
    }

}
