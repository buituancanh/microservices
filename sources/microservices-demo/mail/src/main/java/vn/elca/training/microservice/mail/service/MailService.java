/*
 * Project: ISC-FDJP-IRC
 * ELCA Informatique SA
 */
package vn.elca.training.microservice.mail.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author tcb
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Getter
public class MailService {
    private final JavaMailSender mailSender;

    public void sendMail(String email, String message) {
        send(email, message);
    }

    private void send(String email, String message) {
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mailSender.createMimeMessage(), true, "UTF-8");
            helper.setBcc(email);
            helper.setText(message);
            helper.setFrom("microservices@elca.vn");
            helper.setSentDate(new Date());
            helper.setSubject("Kafka Elca Training Project");
            mailSender.send(helper.getMimeMessage());
        } catch (Exception ex) {
            log.error("error when send mail", ex);
        }
    }
}
