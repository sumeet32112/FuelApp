package com.noxfilers.fuelApp.controllers;

import com.noxfilers.fuelApp.apiRes.ApiRes;
import com.noxfilers.fuelApp.apiRes.VehiclesOfUserRes;
import com.noxfilers.fuelApp.dtos.RegisterVehicleDTO;
import com.noxfilers.fuelApp.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class VehicleController {
    private final VehicleService vehicleService;
    @Autowired
    VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping("/vehicles")
    public ResponseEntity<ApiRes<String>> saveVehicle(@RequestBody RegisterVehicleDTO registerVehicleDTO){
        ApiRes<String> response = new ApiRes<>();
        //response if owner does not exist
        if( vehicleService.ownerDoesNotExist( registerVehicleDTO.getOwner()) ){
            response.setSuccess(false);
            response.setMessage("Owner does not exit.");
            response.setData("Vehicle Registration failed");
            return ResponseEntity.ok( response );
        }
        //Response if registration number is not unique
        else if( vehicleService.existsByRegistrationNumber(registerVehicleDTO.getRegistrationNumber())){
            response.setSuccess(false);
            response.setMessage("Registration Number already exist.");
            response.setData("vehicle registration failed.");
            return ResponseEntity.ok( response );

        }

        vehicleService.saveVehicle(registerVehicleDTO);
        response.setSuccess(true);
        response.setMessage("Vehicle registered successfully");
        response.setData("Vehicle registration complete");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllVehicleBrand")
    public ResponseEntity<ApiRes<List<String>>> getAllVehicleBrand(){
        ApiRes<List<String>> response = new ApiRes<>();
        response.setSuccess(true);
        response.setMessage("success");
        response.setData(this.vehicleService.getAllVehicleBrand());
        return ResponseEntity.ok(response);
    }

    //display vehicles list of the user
    @GetMapping("/getVehiclesOfUser/{userNumber}")
    public ResponseEntity<List<VehiclesOfUserRes>> getVehiclesOfUser(@PathVariable String userNumber) {
        List<VehiclesOfUserRes> vehiclesOfUserResList = vehicleService.getVehiclesOfUser(userNumber);
        return ResponseEntity.ok(vehiclesOfUserResList);
    }
}


