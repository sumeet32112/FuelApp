package com.noxfilers.fuelApp.controllers;

import com.noxfilers.fuelApp.apiRes.ApiRes;
import com.noxfilers.fuelApp.apiRes.FuelInfoRes;
import com.noxfilers.fuelApp.dtos.FuelInfoDTO;
import com.noxfilers.fuelApp.dtos.NumberDTO;
import com.noxfilers.fuelApp.entities.RegisteredUser;
import com.noxfilers.fuelApp.services.FuelService;
import com.noxfilers.fuelApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FuelController {
    @Autowired
    private final FuelService fuelService;
    private final UserService userService;

    @Autowired
    FuelController(FuelService fuelService, UserService userService) {
        this.fuelService = fuelService;
        this.userService = userService;
    }

    @PostMapping("/fueling")
    public ResponseEntity<ApiRes<String>> saveFuelInfo(@RequestBody FuelInfoDTO fuelInfoDTO) {
        ApiRes<String> res = new ApiRes<>();
        fuelService.saveFuelInfo(fuelInfoDTO);
        res.setSuccess(true);
        res.setMessage("Fueling information added successfully");
        res.setData("Fueling complete");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getAllAgency")
    public List<String> getAllAgency() {
        return this.fuelService.getAllAgency();
    }

    @GetMapping("/getAllRegistrationNumber")
    public List<String> getAllRegistrationNumber(@RequestParam(value = "userNumber") String userNumber ) {
        return this.fuelService.getAllRegistrationNumber(userNumber);
    }

    @GetMapping("/getUserFuelInfo")
    public ResponseEntity<List<FuelInfoRes>> getUserFuelInfo( @RequestParam(value = "userNumber") String userNumber){

        FuelInfoRes fuelInfoRes = new FuelInfoRes();
        List<FuelInfoRes> fuelInfoList = fuelService.getUserFuelInfo(userNumber, null );
        return ResponseEntity.ok(fuelInfoList);
    }
    @GetMapping("/getUserFuelInfo/{userNumber}/{agencyName}")
    public ResponseEntity<List<FuelInfoRes>> getUserFuelInfoAgency( @PathVariable String userNumber, @PathVariable String agencyName ){

        FuelInfoRes fuelInfoRes = new FuelInfoRes();
        List<FuelInfoRes> fuelInfoList = fuelService.getUserFuelInfo( userNumber, agencyName );
        return ResponseEntity.ok(fuelInfoList);
    }

}
