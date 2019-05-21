package backEnd.service;

import backEnd.model.User;

import java.io.File;

public interface NotificationService {

    void sendSubscribeGreeting(String email, String language);

    void sendInvestorGreeting(String email, String language);

    void sendAdminKYCCheck(File attachment, User user, String extension);
}
