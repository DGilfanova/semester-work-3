package ru.kpfu.itis.hauss.utils.mail.handlers;

import java.util.Map;

public interface TemplateHandler {
    String getContentFromTemplate(String templateName, Map<String, String> data);
}
