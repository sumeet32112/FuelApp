package com.noxfilers.fuelApp.controllers;

import com.noxfilers.fuelApp.apiRes.ApiRes;
import com.noxfilers.fuelApp.apiRes.FuelInfoRes;
import com.noxfilers.fuelApp.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ComplaintController {

    private final EmailService emailService;
    @Autowired
    public ComplaintController( EmailService emailService ) {
        this.emailService = emailService;
    }

    @PostMapping("/complain/{escalationLevel}")
    public ResponseEntity<ApiRes<String>> submitComplaint(
            @RequestPart("fuels") List<FuelInfoRes> fuels,
            @RequestParam( value = "attachments" , required = false ) MultipartFile[] attachments,
            @PathVariable String escalationLevel ) {
        ApiRes<String> response = new ApiRes<>();

        boolean res = emailService.sendEmail( escalationLevel ,  "Complaining about Fueling Agency" , fuels, attachments );
        if( !res ){
            response.setSuccess( false );
            response.setMessage("Something went Wrong Cannot put the complain");
            response.setData("Check your EmailMail");
            return ResponseEntity.ok( response );
        }
        response.setSuccess(true);
        response.setMessage("Complaint registered successfully.");
        response.setData("Complaint registered successfully.");
        return ResponseEntity.ok( response );
    }
}
