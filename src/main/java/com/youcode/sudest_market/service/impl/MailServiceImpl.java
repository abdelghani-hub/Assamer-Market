package com.youcode.sudest_market.service.impl;

import com.youcode.sudest_market.domain.AppUser;
import com.youcode.sudest_market.service.MailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    private final JavaMailSender javaMailSender;

    @Value("${app.frontend.domain}")
    private String frontDomain;

    @Override
    public boolean sellerRequestAccepted(AppUser newSeller) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom("aaittamghart8@gmail.com");
            helper.setTo(newSeller.getEmail());
            helper.setSubject("🤩 Request accepted 🤩");

            // Read HTML template
            String htmlContent;
            try (var inputStream = Objects.requireNonNull(
                    SellerServiceImpl.class.getResourceAsStream("/templates/seller-request-accepted.html"))) {
                htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            }

            // Replace placeholders
            String storeUrl = new StringBuilder(this.frontDomain)
                    .append("/store/")
                    .append(newSeller.getUsername())
                    .toString();

            htmlContent = htmlContent
                    .replace("{sellerName}", newSeller.getUsername())
                    .replace("{storeUrl}", storeUrl)
                    .replace("{sellerEmail}", newSeller.getEmail());

            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            logger.error("Error while sending email to {}: {}", newSeller.getEmail(), e.getMessage(), e);
            throw new RuntimeException("Error while sending email", e);
        }
    }
}