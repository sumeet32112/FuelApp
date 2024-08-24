package com.noxfilers.fuelApp.services;

import com.noxfilers.fuelApp.apiRes.FuelInfoRes;
import com.noxfilers.fuelApp.services.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    public void checkEmailService() throws MessagingException, IOException {
        List<FuelInfoRes> fuels = new ArrayList<FuelInfoRes>();
        FuelInfoRes fuel1 = new FuelInfoRes(new Date(), "ABC123", "IOCL Agencies", "Gasoline",
                0.75, 8.5, 15.2, 12.0);

        FuelInfoRes fuel2 = new FuelInfoRes(new Date(), "XYZ456", "IOCL Agencies", "Diesel",
                0.82, 10.2, 20.7, 18.5);

        FuelInfoRes fuel3 = new FuelInfoRes(new Date(), "LMN789", "IOCL Agencies", "Electric",
                0.0, 0.0, 0.0, 0.0);
        MultipartFile[] attachments = new MultipartFile[0];
        boolean result = emailService.sendEmail("1", "Testing", fuels, attachments );
        assertTrue(result);
    }
}
