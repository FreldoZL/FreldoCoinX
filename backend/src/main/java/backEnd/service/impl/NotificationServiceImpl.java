package backEnd.service.impl;

import backEnd.AppPropertiesConfig;
import backEnd.model.User;
import backEnd.service.NotificationService;
import backEnd.service.VelocityEmailSender;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private VelocityEmailSender velocityEmailSender;

    @Autowired
    private AppPropertiesConfig config;

    @Override
    public void sendSubscribeGreeting(String email, String language) {
        VelocityContext context = new VelocityContext();
        context.put("year", Calendar.getInstance().get(Calendar.YEAR));
        if (language.toLowerCase().equals("ru")) {
            velocityEmailSender.send(email, "notification.subscribe.greeting.template.ru", "notification.subscribe.greeting.subject.ru", context);
        } else if (language.toLowerCase().equals("cn")) {
            velocityEmailSender.send(email, "notification.subscribe.greeting.template.cn", "notification.subscribe.greeting.subject.cn", context);
        } else {
            velocityEmailSender.send(email, "notification.subscribe.greeting.template.en", "notification.subscribe.greeting.subject.en", context);
        }
    }

    @Override
    public void sendInvestorGreeting(String email, String language) {
        VelocityContext context = new VelocityContext();
        context.put("year", Calendar.getInstance().get(Calendar.YEAR));
        if (language.toUpperCase().equals("RU")) {
            velocityEmailSender.send(email, "notification.user.greeting.template.ru", "notification.user.greeting.subject.ru", context);
        } else if (language.toUpperCase().equals("CN")) {
            velocityEmailSender.send(email, "notification.user.greeting.template.cn", "notification.user.greeting.subject.cn", context);
        } else {
            velocityEmailSender.send(email, "notification.user.greeting.template.en", "notification.user.greeting.subject.en", context);
        }
    }

    @Override
    public void sendAdminKYCCheck(File attachment, User user, String fileName) {
        VelocityContext context = new VelocityContext();
        context.put("user", user);
        String trueLink = config.backendServerAddress + "/user/kyc_validation?id=" + user.getId() + "&isValid=true";
        context.put("trueLink", trueLink);
        String falseLink = config.backendServerAddress + "/user/kyc_validation?id=" + user.getId() + "&isValid=false";
        context.put("falseLink", falseLink);

        velocityEmailSender.sendWithAttachments(config.emailAdmin, "notification.kyc.check.template.en", "notification.kyc.check.subject.en", context, attachment, fileName);
    }
}
