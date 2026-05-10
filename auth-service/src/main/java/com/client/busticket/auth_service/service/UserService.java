package com.client.busticket.auth_service.service;

import com.client.busticket.auth_service.entity.Users;
import com.client.busticket.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Users saveUser(Users user){
        try {
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
