package com.noxfilers.fuelApp.services;

import com.noxfilers.fuelApp.entities.RegisteredUser;
import com.noxfilers.fuelApp.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    RegisteredUserRepository registeredUserRepository;
    @Override
    public UserDetails loadUserByUsername(String number) throws UsernameNotFoundException {
        UserDetails user = this.registeredUserRepository.findByNumber(number);
        if(user == null){
            throw new RuntimeException("User Not Found!!");
        }
        return user;
    }
    }
