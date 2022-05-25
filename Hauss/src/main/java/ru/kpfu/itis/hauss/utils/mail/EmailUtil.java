package ru.kpfu.itis.hauss.utils.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.hauss.utils.mail.handlers.TemplateHandler;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class EmailUtil {

    private final TemplateHandler templateHandler;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    public void sendMail(String to, String subject, String templateName, Map<String, String> data) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(templateHandler.getContentFromTemplate(templateName, data), true);

            mailSender.send(mimeMessageHelper.getMimeMessage());

            logger.info("Successfully send email to " + to);
        } catch (MessagingException e) {
            logger.error("Error sending mail to " + to);

            throw new IllegalStateException(e);
        }
    }
}

