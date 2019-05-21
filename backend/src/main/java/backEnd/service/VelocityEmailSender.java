package backEnd.service;

import org.apache.velocity.VelocityContext;

import java.io.File;

public interface VelocityEmailSender {

    void send(final String toEmail, String templateNameKey, final String subjectKey,
              final VelocityContext templateVariables);

    void sendWithAttachments(final String toEmail, String templateNameKey, final String subjectKey,
                             final VelocityContext templateVariables, final File attachment, String fileName);
}
