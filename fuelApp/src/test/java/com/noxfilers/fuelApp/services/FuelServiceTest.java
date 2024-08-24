package com.noxfilers.fuelApp.services;

import com.noxfilers.fuelApp.apiRes.FuelInfoRes;
import com.noxfilers.fuelApp.dtos.FuelInfoDTO;
import com.noxfilers.fuelApp.entities.*;
import com.noxfilers.fuelApp.repositories.BunkMasterRepo;
import com.noxfilers.fuelApp.repositories.FuelInfoRepository;
import com.noxfilers.fuelApp.repositories.RegisteredUserRepository;
import com.noxfilers.fuelApp.repositories.RegisteredVehiclesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class FuelServiceTest {


    @Mock
    private RegisteredUserRepository registeredUserRepository;
    @Mock
    private FuelInfoRepository fuelInfoRepository;
    @Mock
    private RegisteredVehiclesRepository registeredVehiclesRepository;
    @Mock
    private BunkMasterRepo bunkMasterRepo;

    private FuelService fuelService;

    Date date = new Date();

    CommonDetails commonDetails = new CommonDetails(2L,true,"Admin",date,null,null,1L);
    BunkProvider bunkProvider = new BunkProvider(4L,"HPCL" , "Hindusthan Petroleum",commonDetails);
    BunkMaster bunkMaster = new BunkMaster(1L,bunkProvider,"IOCL Agencies","Suresh","ioclagenciesthpkmbunk@gmail.com","9876543211","12.92791","80.23157","Thoraipakkam","OMR Main Road,Near Nilgiris,Thoraipakkam",commonDetails);
    FuelType fuelType1 = new FuelType(1L,"Petrol",commonDetails);
    FuelType fuelType2 =new FuelType(2L,"Diesel",commonDetails);
    VehicleClass vehicleClass = new VehicleClass(1L,"LMV",commonDetails);
    VehicleBrand vehicleBrand = new VehicleBrand(1L,"Hyundai",vehicleClass,commonDetails);
    RegisteredUser registeredUser = new RegisteredUser(5L, "Doctor Strange", "Doctor", "Strange",bunkProvider,null,"9334725007","Mr.",commonDetails);
    RegisteredVehicles registeredVehicles1 = new RegisteredVehicles(1L,vehicleClass,"registrationNumber1",registeredUser,vehicleBrand,"alto200i",fuelType1,null,2000,date,500,"white",commonDetails);
    RegisteredVehicles registeredVehicles2 = new RegisteredVehicles(2L,vehicleClass,"registrationNumber2",registeredUser,vehicleBrand,"alto200i",fuelType2,null,2001,date,500,"grey",commonDetails);

    FuelInfo fuelInfo1 = new FuelInfo(1L,registeredVehicles1,date,bunkMaster,22.00,102.43,45.00,100.00,20.5,13.85,"20",49.0975,45.00,4.09747,1.5,null,commonDetails);
    FuelInfo fuelInfo2 = new FuelInfo(2L,registeredVehicles2,date,bunkMaster,23.00,102.43,45.00,100.00,20.5,13.85,"20",48.0975,45.00,4.09737,1.4,null,commonDetails);
    FuelInfo fuelInfo3 = new FuelInfo(3L,registeredVehicles2,date,bunkMaster,23.00,102.43,45.00,100.00,20.5,13.85,"20",48.0975,45.00,4.09737,1.5,null,commonDetails);


    @BeforeEach
    void setUp() {
        this.fuelService = new FuelService(registeredUserRepository,fuelInfoRepository,registeredVehiclesRepository,bunkMasterRepo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveFuelInfo() {

        //given
        FuelInfoDTO fuelInfoDTO =  FuelInfoDTO.builder().fuelDensity(22.00).dateOfFueling(date).agency("IOCL Agencies").allowedDelta(1.5).pricePerLitre(102.43)
                .filledQuantity(45.00).vehicleMileage(13.85).distanceCovered(20.5).timeTravelled("20").vehicle("registerationNumber1").comments(null).build();
        FuelInfo fuelInfo= fuelInfo1;

        //when
        Mockito.when(fuelInfoRepository.save(any(FuelInfo.class))).thenReturn(fuelInfo);
        fuelService.saveFuelInfo(fuelInfoDTO);

    }

    @Test
    void getAllAgency() {

        //when
        Mockito.when(bunkMasterRepo.findAllAgency()).thenReturn(List.of("agency1","agency2","agency3"));
        List<String> agencyFromDB = fuelService.getAllAgency();

        //then
        assertTrue(agencyFromDB.size() == 3);
        assertEquals("agency1",agencyFromDB.get(0));
        assertLinesMatch(List.of("agency1","agency2","agency3"),agencyFromDB);
    }

    @Test
    void getAllRegistrationNumber() {
        //given
        String userNumber = "9334725007";

        //when
        Mockito.when(registeredUserRepository.findRegisteredUserByMobile(userNumber)).thenReturn(registeredUser);
        Mockito.when(registeredVehiclesRepository.findListByNumber(any(RegisteredUser.class))).thenReturn(List.of("registrationNumber1","registrationNumber2","registrationNumber3"));
        List<String> actualRegistrationNumber = fuelService.getAllRegistrationNumber(userNumber);

        //then
        assertTrue(actualRegistrationNumber.size() == 3);
        assertLinesMatch(List.of("registrationNumber1","registrationNumber2","registrationNumber3"), actualRegistrationNumber);
    }

    @Test
    void getUserFuelInfo() {
        //given
        String userNumber = "9334725007";
        String agencyName = "IOCL Agencies";
        FuelInfoRes fuelInfoRes1 = new FuelInfoRes(date,"registrationNumber1","IOCL Agencies","Petrol",22.00,49.0975,4.09747,1.5);
        FuelInfoRes fuelInfoRes2 = new FuelInfoRes(date,"registrationNumber2","IOCL Agencies","Diesel",23.00,48.0975,4.09737,1.4);
        FuelInfoRes fuelInfoRes3 = new FuelInfoRes(date,"registrationNumber2","IOCL Agencies","Diesel",23.00,48.0975,4.09737,1.5);

        //when
        Mockito.when(registeredUserRepository.findRegisteredUserByMobile(userNumber)).thenReturn(registeredUser);
        Mockito.when(registeredVehiclesRepository.findByOwner(any((RegisteredUser.class)))).thenReturn(List.of(registeredVehicles1,registeredVehicles2));
        Mockito.when(fuelInfoRepository.findByVehicle(registeredVehicles1)).thenReturn(List.of(fuelInfo1));
        Mockito.when(fuelInfoRepository.findByVehicle(registeredVehicles2)).thenReturn(List.of(fuelInfo2,fuelInfo3));
        List<FuelInfoRes> fuelInfoResList = fuelService.getUserFuelInfo(userNumber,agencyName);


        List<FuelInfoRes> expectedFuelInfoResList = new ArrayList<>(List.of(fuelInfoRes1,fuelInfoRes2,fuelInfoRes3));

       //then
        assertTrue(fuelInfoResList.size() == 3);
        for (int i=0; i< 3 ; i++) {
            assertEquals(fuelInfoResList.get(i).getDate(),expectedFuelInfoResList.get(i).getDate());
            assertEquals(fuelInfoResList.get(i).getFuelType(),expectedFuelInfoResList.get(i).getFuelType());
            assertEquals(fuelInfoResList.get(i).getAgency(),expectedFuelInfoResList.get(i).getAgency());
            assertEquals(fuelInfoResList.get(i).getRegistrationNumber(),expectedFuelInfoResList.get(i).getRegistrationNumber());
            assertEquals(fuelInfoResList.get(i).getDelta(),expectedFuelInfoResList.get(i).getDelta());
            assertEquals(fuelInfoResList.get(i).getAllowedDelta(),expectedFuelInfoResList.get(i).getAllowedDelta());
            assertEquals(fuelInfoResList.get(i).getDensity(),expectedFuelInfoResList.get(i).getDensity());
            assertEquals(fuelInfoResList.get(i).getEngineEconomyUsage(),expectedFuelInfoResList.get(i).getEngineEconomyUsage());
        }

    }
}