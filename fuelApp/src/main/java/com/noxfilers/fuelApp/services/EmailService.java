package com.noxfilers.fuelApp.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.noxfilers.fuelApp.apiRes.FuelInfoRes;
import com.noxfilers.fuelApp.repositories.BunkMasterRepo;
import com.noxfilers.fuelApp.repositories.BunkProviderEscalation;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    private BunkProviderEscalation bunkProviderEscalation;
    private BunkMasterRepo bunkMasterRepo;

    public EmailService(JavaMailSender javaMailSender, BunkProviderEscalation bunkProviderEscalation, BunkMasterRepo bunkMasterRepo) {
        this.javaMailSender = javaMailSender;
        this.bunkProviderEscalation = bunkProviderEscalation;
        this.bunkMasterRepo = bunkMasterRepo;
    }
    public String generateEmailTemplate(List<FuelInfoRes> fuels) {
        StringBuilder template = new StringBuilder();
        template.append("<html><body>");
        template.append("<p>Hey there, I've filled up my tank a few times at your station. If you take a look at the stats below, you might spot a few odd numbers. Do you mind giving it a quick check for any discrepancies? Thanks a bunch!</p>");
        template.append("<h2>Fuel Entries Report</h2>");
        template.append("<table border=\"1\">");

        template.append("<tr><th>Date</th><th>Registration Number</th><th>Agency</th><th>Fuel Type</th><th>Density</th><th>Engine Economy Usage</th><th>Delta</th><th>Allowed Delta</th></tr>");

        for (FuelInfoRes fuel : fuels) {
            template.append("<tr>");
            template.append("<td>").append(fuel.getDate()).append("</td>");
            template.append("<td>").append(fuel.getRegistrationNumber()).append("</td>");
            template.append("<td>").append(fuel.getAgency()).append("</td>");
            template.append("<td>").append(fuel.getFuelType()).append("</td>");
            template.append("<td>").append(fuel.getDensity()).append("</td>");
            template.append("<td>").append(fuel.getEngineEconomyUsage()).append("</td>");
            template.append("<td>").append(fuel.getDelta()).append("</td>");
            template.append("<td>").append(fuel.getAllowedDelta()).append("</td>");
            template.append("</tr>");
        }

        template.append("</table>");
        template.append("</body></html>");

        return template.toString();
    }
    public boolean sendEmail(String escalationLevel, String subject,List<FuelInfoRes> fuels, MultipartFile[] attachments) {
            MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String text = generateEmailTemplate(fuels) ;
            String to = (fuels.size()>0)?bunkProviderEscalation.findEscalationMail( bunkMasterRepo.findBunkMaster(fuels.get(0).getAgency()).getBunkProvider(), Integer.valueOf(escalationLevel)):"rockybalboa2798@gmail.com" ;
            helper.setTo(to);
            helper.setFrom("ronakdeora27@gmail.com");
            helper.setSubject(subject);
            helper.setText(text, true);
            if (attachments != null && attachments.length > 0) {
                for (MultipartFile attachment : attachments) {
                    String filename = attachment.getOriginalFilename();
                    InputStreamSource inputStreamSource = new InputStreamSource() {
                        @Override
                        public InputStream getInputStream() throws IOException {
                            return attachment.getInputStream();
                        }
                    };

                    helper.addAttachment(filename, inputStreamSource);
                }
            }
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}