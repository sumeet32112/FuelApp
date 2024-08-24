package com.noxfilers.fuelApp.services;

import com.noxfilers.fuelApp.dtos.RegisterUserDTO;
import com.noxfilers.fuelApp.entities.BunkProvider;
import com.noxfilers.fuelApp.entities.CommonDetails;
import com.noxfilers.fuelApp.entities.RegisteredUser;
import com.noxfilers.fuelApp.repositories.BunkProviderRepo;
import com.noxfilers.fuelApp.repositories.RegisteredUserRepository;
import com.sun.net.httpserver.Authenticator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private RegisteredUserRepository registeredUserRepository;
    @Mock
    private BunkProviderRepo bunkProviderRepo;
    @Test
    public void findByNumber_Success(){
        Mockito.when(registeredUserRepository.existsByMobile("9265065447")).thenReturn(true);
        boolean result = userService.findByNumber("9265065447");
        assertTrue(result);
    }

    @Test
    public void findByNumber_Fail(){
        Mockito.when(registeredUserRepository.existsByMobile("9265065447")).thenReturn(false);
        boolean result = userService.findByNumber("9265065447");
        assertFalse(result);
    }

    @Test
    public void loadUserByNumber_Success(){
        RegisteredUser user = new RegisteredUser();
        Mockito.when(registeredUserRepository.findByNumber("9265065447")).thenReturn(user);
        RegisteredUser result = userService.loadUserByNumber("9265065447");
        assertNotNull(result);
        assertEquals(user,result);
    }

    @Test
    public void findByDisplayName_Success(){
        Mockito.when(registeredUserRepository.findIdByDisplayName("Avinash")).thenReturn(1L);
        boolean result = userService.findByDisplayName("Avinash");
        assertTrue(result);
    }
    @Test
    public void findByDisplayName_Fail(){
        Mockito.when(registeredUserRepository.findIdByDisplayName("Avinash")).thenReturn(null);
        boolean result = userService.findByDisplayName("Avinash");
        assertFalse(result);
    }
    Date date = new Date();
    CommonDetails commonDetails1 = new CommonDetails(1L,true,"Admin",date,null,null,1L);
    CommonDetails commonDetails2 = new CommonDetails(2L,true,"Admin",date,null,null,1L);
    BunkProvider bunkProvider = new BunkProvider(1L,"HPCL" , "Hindusthan Petroleum",commonDetails1);


    @Captor
    private ArgumentCaptor<RegisteredUser> registeredUserArgumentCaptor;
    @Test
    public void saveUser_Success(){
        RegisterUserDTO registerUserDTO = RegisterUserDTO.builder()
                .title("Mr")
                .firstName("Avinash")
                .middleName("")
                .lastName("Somesvari")
                .displayName("Avinash")
                .preferredBunkType(bunkProvider)
                .mobile("9265065447")
                .build();

        RegisteredUser savedUser = new RegisteredUser();
        when(registeredUserRepository.save(any(RegisteredUser.class))).thenReturn(savedUser);

        userService.SaveUser(registerUserDTO);

        verify(registeredUserRepository).save(registeredUserArgumentCaptor.capture());
        RegisteredUser capturedUser = registeredUserArgumentCaptor.getValue();

        assertEquals(registerUserDTO.getTitle(), capturedUser.getTitle());
        assertEquals(registerUserDTO.getFirstName(), capturedUser.getFirstName());
        assertEquals(registerUserDTO.getLastName(), capturedUser.getLastName());
        assertEquals(registerUserDTO.getPreferredBunkType(), capturedUser.getPreferredBunkType());
        assertEquals(registerUserDTO.getMobile(), capturedUser.getMobile());
        assertEquals(registerUserDTO.getDisplayName(), capturedUser.getDisplayName());
        assertNotNull(capturedUser.getCommonDetails());
        assertNotNull(capturedUser.getCommonDetails().getCreatedDate());
        assertEquals(registerUserDTO.getDisplayName(), capturedUser.getCommonDetails().getCreatedBy());

    }

    @Test
    public void getAllBunkProvider_Success(){
        List<String> providers = Arrays.asList("Provider1", "Provider2");
        Mockito.when(bunkProviderRepo.getAllBunkProvider()).thenReturn(providers);

        List<String> result = userService.getAllBunkProvider();

        assertNotNull(result);
        assertEquals(providers, result);
    }
}
