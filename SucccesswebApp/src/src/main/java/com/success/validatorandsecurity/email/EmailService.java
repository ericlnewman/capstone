package com.success.validatorandsecurity.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Service
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        super();
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void send(String to, String email) throws jakarta.mail.MessagingException {
        jakarta.mail.internet.MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		helper.setText(email, true);
		helper.setTo(to);
		helper.setSubject("Confirm your email");
		helper.setFrom("welcome@sfa.com");
		mailSender.send(mimeMessage);
    }
}
