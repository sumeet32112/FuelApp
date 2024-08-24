package com.noxfilers.fuelApp.services;

import com.noxfilers.fuelApp.apiRes.FuelInfoRes;
import com.noxfilers.fuelApp.dtos.FuelInfoDTO;
import com.noxfilers.fuelApp.entities.CommonDetails;
import com.noxfilers.fuelApp.entities.FuelInfo;
import com.noxfilers.fuelApp.entities.RegisteredUser;
import com.noxfilers.fuelApp.entities.RegisteredVehicles;
import com.noxfilers.fuelApp.repositories.BunkMasterRepo;
import com.noxfilers.fuelApp.repositories.FuelInfoRepository;
import com.noxfilers.fuelApp.repositories.RegisteredUserRepository;
import com.noxfilers.fuelApp.repositories.RegisteredVehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FuelService {
    @Autowired
    private  final RegisteredUserRepository registeredUserRepository;
    @Autowired
    private final FuelInfoRepository fuelInfoRepository;
    @Autowired
    private final RegisteredVehiclesRepository registeredVehiclesRepository;
    @Autowired
    private final BunkMasterRepo bunkMasterRepo;
    @Autowired
    public FuelService(RegisteredUserRepository registeredUserRepository, FuelInfoRepository fuelInfoRepository, RegisteredVehiclesRepository registeredVehiclesRepository, BunkMasterRepo bunkMasterRepo) {
        this.registeredUserRepository = registeredUserRepository;
        this.fuelInfoRepository = fuelInfoRepository;
        this.registeredVehiclesRepository = registeredVehiclesRepository;
        this.bunkMasterRepo = bunkMasterRepo;
    }

    public void saveFuelInfo(FuelInfoDTO fuelInfoDTO){
        Double totalAmount = fuelInfoDTO.getPricePerLitre() * fuelInfoDTO.getFilledQuantity();
        Double engineEconomyUsage = fuelInfoDTO.getDistanceCovered() / fuelInfoDTO.getVehicleMileage();
        Double bunkEconomyUsage = fuelInfoDTO.getFilledQuantity();
        Double delta = engineEconomyUsage - bunkEconomyUsage;


        FuelInfo fuelInfo = new FuelInfo();
        fuelInfo.setVehicle(registeredVehiclesRepository.findRegisteredVehicle(fuelInfoDTO.getVehicle()));
        fuelInfo.setFuelDate(fuelInfoDTO.getDateOfFueling());
        fuelInfo.setBunk((bunkMasterRepo.findBunkMaster(fuelInfoDTO.getAgency())));
        fuelInfo.setFuelDensity(fuelInfoDTO.getFuelDensity());
        fuelInfo.setPricePerLitre(fuelInfoDTO.getPricePerLitre());
        fuelInfo.setFilledQuantity(fuelInfoDTO.getFilledQuantity());
        fuelInfo.setTotalAmount(totalAmount);
        fuelInfo.setDistanceCovered(fuelInfoDTO.getDistanceCovered());
        fuelInfo.setVehicleMileage(fuelInfoDTO.getVehicleMileage());
        fuelInfo.setTimeTravelled(fuelInfoDTO.getTimeTravelled());
        fuelInfo.setEngineEconomyUsage(engineEconomyUsage);
        fuelInfo.setBunkEconomyUsage(bunkEconomyUsage);
        fuelInfo.setDelta(delta);
        fuelInfo.setAllowedDelta(fuelInfoDTO.getAllowedDelta());
        fuelInfo.setComments(fuelInfoDTO.getComments());

        CommonDetails commonDetails = new CommonDetails();
       // commonDetails.setCreatedBy("Admin");
        commonDetails.setCreatedDate(new Date());
        fuelInfo.setCommonDetails(commonDetails);

       fuelInfoRepository.save(fuelInfo);
    }

    public List<String> getAllAgency(){
        return  this.bunkMasterRepo.findAllAgency();
    }

    public List<String> getAllRegistrationNumber(String userNumber){
        RegisteredUser registeredUser = registeredUserRepository.findRegisteredUserByMobile(userNumber);
        return registeredVehiclesRepository.findListByNumber(registeredUser);
    }

    public List<FuelInfoRes> getUserFuelInfo(String userNumber, String agencyName) {
        RegisteredUser registeredUser = registeredUserRepository.findRegisteredUserByMobile(userNumber);
        if (registeredUser == null) {
            return Collections.emptyList();
        }
        List<RegisteredVehicles> registeredVehiclesList = registeredVehiclesRepository.findByOwner(registeredUser);
        List<FuelInfoRes> fuelInfoResList = new ArrayList<>();

        for (RegisteredVehicles vehicle : registeredVehiclesList) {
            List<FuelInfo> vehicleFuelInfo = fuelInfoRepository.findByVehicle(vehicle);

            for (FuelInfo fuelInfo : vehicleFuelInfo) {
                if (agencyName == null || fuelInfo.getBunk().getAgencyName().equals(agencyName)) {
                    FuelInfoRes fuelInfoRes = new FuelInfoRes();

                    fuelInfoRes.setDate(fuelInfo.getFuelDate());
                    fuelInfoRes.setRegistrationNumber(vehicle.getRegistrationNumber());
                    fuelInfoRes.setAgency(fuelInfo.getBunk().getAgencyName());
                    fuelInfoRes.setFuelType(vehicle.getFuelType().getFuelType());
                    fuelInfoRes.setDensity(fuelInfo.getFuelDensity());
                    fuelInfoRes.setEngineEconomyUsage(fuelInfo.getEngineEconomyUsage());
                    fuelInfoRes.setDelta(fuelInfo.getDelta());
                    fuelInfoRes.setAllowedDelta(fuelInfo.getAllowedDelta());

                    fuelInfoResList.add(fuelInfoRes);
                }
            }
        }

        return fuelInfoResList;
    }

}
