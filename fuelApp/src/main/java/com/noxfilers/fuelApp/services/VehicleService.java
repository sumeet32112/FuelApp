package com.noxfilers.fuelApp.services;


import com.noxfilers.fuelApp.apiRes.FuelInfoRes;
import com.noxfilers.fuelApp.apiRes.VehiclesOfUserRes;
import com.noxfilers.fuelApp.dtos.RegisterVehicleDTO;
import com.noxfilers.fuelApp.entities.CommonDetails;
import com.noxfilers.fuelApp.entities.FuelInfo;
import com.noxfilers.fuelApp.entities.RegisteredUser;
import com.noxfilers.fuelApp.entities.RegisteredVehicles;
import com.noxfilers.fuelApp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    public final RegisteredVehiclesRepository registeredVehiclesRepository;
    @Autowired
    public final RegisteredUserRepository registeredUserRepository;
    @Autowired
    public final VehicleClassRepository vehicleClassRepository;
    @Autowired
    public final VehicleBrandRepository vehicleBrandRepository;
    @Autowired
    public final FuelTypeRepository fuelTypeRepository;
    @Autowired
    VehicleService(RegisteredVehiclesRepository registeredVehiclesRepository, RegisteredVehiclesRepository registeredVehicleRepository1, RegisteredUserRepository registeredUserRepository, VehicleClassRepository vehicleClassRepository, VehicleBrandRepository vehicleBrandRepository, FuelTypeRepository fuelTypeRepository){
        this.registeredVehiclesRepository = registeredVehiclesRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.vehicleClassRepository = vehicleClassRepository;
        this.vehicleBrandRepository = vehicleBrandRepository;
        this.fuelTypeRepository = fuelTypeRepository;
    }

    public boolean ownerDoesNotExist(String owner) {
        return !(registeredUserRepository.existsByDisplayName (owner));
    }

    public boolean existsByRegistrationNumber(String registrationNumber) {
        return registeredVehiclesRepository.existsByRegistrationNumber(registrationNumber);
    }
    public void saveVehicle(RegisterVehicleDTO registerVehicleDTO){
        RegisteredVehicles vehicle = new RegisteredVehicles();
        //Function to find the vehicle class id;
        vehicle.setVehicleClass(vehicleClassRepository.findVehicleClassByVehicleClass(registerVehicleDTO.getVehicleClass()));
        vehicle.setRegistrationNumber(registerVehicleDTO.getRegistrationNumber());
        vehicle.setOwner(registeredUserRepository.findRegisteredUserByDisplayName(registerVehicleDTO.getOwner()));
        vehicle.setVehicleMaker(vehicleBrandRepository.findBrandByVehicleBrandName(registerVehicleDTO.getBrand()));
        vehicle.setVehicleModelName(registerVehicleDTO.getModel());
        vehicle.setFuelType(fuelTypeRepository.findFuelByFuelType(registerVehicleDTO.getFuelType()));
        vehicle.setMonthManufactured(registerVehicleDTO.getMonthManufactured());
        vehicle.setYearManufactured(registerVehicleDTO.getYearManufactured());
        vehicle.setDateOfPurchase(registerVehicleDTO.getDateOfPurchase());
        vehicle.setEngineCapacity(registerVehicleDTO.getEngineCapacity());
        vehicle.setColour(registerVehicleDTO.getColour());





        CommonDetails commonDetails = new CommonDetails();
        commonDetails.setCreatedBy(registerVehicleDTO.getOwner());
        commonDetails.setCreatedDate(new Date());
        vehicle.setCommonDetails(commonDetails);
//        vehicle.setCreatedBy(registerVehicleDTO.getOwner());
       // vehicle.setCreatedDate(new Date());
        registeredVehiclesRepository.save(vehicle);

    }

    public List<String> getAllVehicleBrand(){
       return  this.vehicleBrandRepository.findAllVehicleBrand();
    }

    public List<VehiclesOfUserRes> getVehiclesOfUser(String userNumber) {
        RegisteredUser registeredUser = registeredUserRepository.findRegisteredUserByMobile(userNumber);
        if(registeredUser == null){
            return Collections.emptyList();
        }
        List<RegisteredVehicles> registeredVehiclesList = registeredVehiclesRepository.findByOwner(registeredUser);
        List<VehiclesOfUserRes> vehiclesOfUserResList = new ArrayList<>();
        for(RegisteredVehicles vehicle : registeredVehiclesList){
            VehiclesOfUserRes vehiclesOfUserRes = new VehiclesOfUserRes();
            vehiclesOfUserRes.setRegistrationNumber(vehicle.getRegistrationNumber());
            vehiclesOfUserRes.setVehicleOwner(registeredUserRepository.findDisplayNameById(vehicle.getOwner().getId()));
            vehiclesOfUserRes.setVehicleBrand(vehicleBrandRepository.findVehicleBrandNameById(vehicle.getVehicleMaker().getId()));
            vehiclesOfUserRes.setFuelType(fuelTypeRepository.findFuelTypeById(vehicle.getFuelType().getId()));
            vehiclesOfUserRes.setMonthManufactured(vehicle.getMonthManufactured());
            vehiclesOfUserRes.setYearManufactured(vehicle.getYearManufactured());
            vehiclesOfUserRes.setEngineCapacity(vehicle.getEngineCapacity());
            vehiclesOfUserRes.setColour(vehicle.getColour());

            vehiclesOfUserResList.add(vehiclesOfUserRes);

        }
        return vehiclesOfUserResList;
    }

}


