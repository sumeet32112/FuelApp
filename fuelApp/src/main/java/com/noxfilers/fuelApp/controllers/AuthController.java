package com.noxfilers.fuelApp.controllers;

import com.noxfilers.fuelApp.apiRes.ApiRes;
import com.noxfilers.fuelApp.apiRes.JwtRes;
import com.noxfilers.fuelApp.dtos.LoginDTO;
import com.noxfilers.fuelApp.dtos.OtpDTO;
import com.noxfilers.fuelApp.security.JwtHelper;
import com.noxfilers.fuelApp.services.OtpService;
import com.noxfilers.fuelApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private OtpService otpService;

    @Autowired
    JwtHelper helper;

  // verify otp
    @PostMapping("/otpVerification")
    public ResponseEntity<JwtRes> otpCheck(@RequestBody OtpDTO param){

        try {
//            if(param.getOtp() != this.otp){
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            }
            if(!this.userService.findByNumber(param.getNumber()) ){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            if(otpService.verifyOtp(param)){

                helper.initialize();
                String token = this.helper.createJWT(param.getNumber());
                JwtRes res = new JwtRes();
                res.setToken(token);
                return ResponseEntity.ok(res);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }

        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    // to login user
    @PostMapping("/login")
    public ResponseEntity<ApiRes<String>> loginUser(@RequestBody LoginDTO param) {

        try {
            ApiRes<String> response = new ApiRes<>();
            if(!this.userService.findByNumber(param.getNumber()) ){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            otpService.generateAndStoreOtp(param);
            response.setSuccess(true);
            response.setMessage("OTP sent successfully");
            response.setData("OTP sent successfully");
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

//        try {
//            // Construct data
//            String apiKey = "apikey=" + "NzI1OTRhNTI0MTc2MzM2NzMxNzQ2NzMxMzk1NTY0NTA=";
//            String message = "&message=" + "hiiii";
//            String sender = "&sender=" + "TXTLCL";
//            String numbers = "&numbers=" + ("91"+num);
//
//            // Send data
//            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
//            String data = apiKey + numbers + message + sender;
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
//            conn.getOutputStream().write(data.getBytes("UTF-8"));
//            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            final StringBuffer stringBuffer = new StringBuffer();
//            String line;
//            while ((line = rd.readLine()) != null) {
//                stringBuffer.append(line);
//            }
//            rd.close();
//
//            return stringBuffer.toString();
//        } catch (Exception e) {
//            System.out.println("Error SMS "+e);
//            return "error " +e;
//        }
    }

    @GetMapping("/noop")
    public void Noop(){

    }
}
