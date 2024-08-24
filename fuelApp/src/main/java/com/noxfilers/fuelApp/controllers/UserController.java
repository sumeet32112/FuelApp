package com.noxfilers.fuelApp.controllers;

import com.noxfilers.fuelApp.apiRes.ApiRes;
import com.noxfilers.fuelApp.apiRes.JwtRes;
import com.noxfilers.fuelApp.dtos.LoginDTO;
import com.noxfilers.fuelApp.dtos.OtpDTO;
import com.noxfilers.fuelApp.dtos.RegisterUserDTO;
import com.noxfilers.fuelApp.entities.RegisteredUser;
import com.noxfilers.fuelApp.security.JwtHelper;
import com.noxfilers.fuelApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


// user Registration//
    @PostMapping("/register")
    public ResponseEntity<ApiRes<String>> registerUser(@RequestBody RegisterUserDTO user ){
        ApiRes<String> response = new ApiRes<>();

        if( userService.findByDisplayName( user.getDisplayName() ) ){
            response.setSuccess(false);
            response.setMessage("Display name already exists.");
            response.setData("User registration failed");
            return ResponseEntity.ok( response );
        }
        if( userService.findByNumber( user.getMobile() ) ){
            response.setSuccess(false);
            response.setMessage("Mobile already exists.");
            response.setData("User registration failed.");
            return ResponseEntity.ok( response );
        }

        userService.SaveUser( user );

        response.setSuccess(true);
        response.setMessage("User registered successfully.");
        response.setData("User registered successfully.");
        return ResponseEntity.ok(response);

    }

    @GetMapping("/username/{mobileNo}")
    public ResponseEntity<ApiRes<String>> getUser(@PathVariable String mobileNo) {
        ApiRes<String> response = new ApiRes<>();

        if (userService.mobileNoExist(mobileNo)) {
            String username = userService.loadUserByNumber(mobileNo).getDisplayName();
            response.setSuccess(true);
            response.setMessage(" Welcome " + username);
            response.setData(username);
            return ResponseEntity.ok(response);
        }

        response.setSuccess(false);
        response.setMessage(" user not found ");
        response.setData("username");
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getAllBunkProvider")
    public List<String> getAllBunkProvider(){
        return  this.userService.getAllBunkProvider();
    }
}
