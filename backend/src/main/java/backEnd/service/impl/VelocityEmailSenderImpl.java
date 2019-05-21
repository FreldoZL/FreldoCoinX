package backEnd.service.impl;

import backEnd.AppPropertiesConfig;
import backEnd.service.VelocityEmailSender;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.StringWriter;
import java.util.Locale;

@Service
public class VelocityEmailSenderImpl implements VelocityEmailSender{

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private JavaMailSenderImpl mailSender;

    private String fromEmail;
    private String fromName;

    @Autowired
    private AppPropertiesConfig config;

    @Autowired
    public void init() throws Exception {
        fromEmail = config.emailFrom;
        fromName = config.emailName;
    }

    @Override
    public void send(String toEmail, String templateNameKey, String subjectKey, VelocityContext templateVariables) {
        final Locale locale = LocaleContextHolder.getLocale();
        String templateName = messageSource.getMessage(templateNameKey, null, locale);
        String subject = messageSource.getMessage(subjectKey, null, locale);

        mailSender.send(new VelocityPreparator(toEmail, subject, templateName, templateVariables));
    }

    @Override
    public void sendWithAttachments(String toEmail, String templateNameKey, String subjectKey, VelocityContext templateVariables, File attachment, String fileName) {
        final Locale locale = LocaleContextHolder.getLocale();
        String templateName = messageSource.getMessage(templateNameKey, null, locale);
        String subject = messageSource.getMessage(subjectKey, null, locale);

        mailSender.send(new VelocityPreparator(toEmail, subject, templateName, templateVariables, attachment, fileName));
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    private class VelocityPreparator implements MimeMessagePreparator {

        private String toEmail;
        private String subject;
        private String templateName;
        private VelocityContext templateVariables;
        private File attachment;
        private String fileName;

        public VelocityPreparator(String toEmail, String subject,
                                  String templateName, VelocityContext templateVariables) {
            this.toEmail = toEmail;
            this.subject = subject;
            this.templateName = templateName;
            this.templateVariables = templateVariables;

        }

        public VelocityPreparator(String toEmail, String subject,
                                  String templateName, VelocityContext templateVariables,
                                  File attachment, String fileName) {
            this.toEmail = toEmail;
            this.subject = subject;
            this.templateName = templateName;
            this.templateVariables = templateVariables;
            this.attachment = attachment;
            this.fileName = fileName;
        }

        public void prepare(MimeMessage mimeMessage) throws Exception {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setTo(toEmail);
            message.setFrom(fromEmail, fromName);
            message.setSubject(subject);

            StringWriter stringWriter = new StringWriter();
            velocityEngine.mergeTemplate(templateName, "UTF-8", templateVariables, stringWriter);
            message.setText(stringWriter.toString(), true);
            if (attachment != null) {
                message.addAttachment(fileName, attachment);
            }
        }
    }
}
