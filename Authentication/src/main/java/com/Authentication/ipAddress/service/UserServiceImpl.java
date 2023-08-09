package com.Authentication.ipAddress.service;

import com.Authentication.ipAddress.entity.User;
import com.Authentication.ipAddress.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository uRepo;


    @Override
    public User signup(User user) {
        return uRepo.save(user);
    }

    @Override
    public String homepage() {
        return "This is home page";
    }

    @Override
    public User getUser(Integer id) {
        return uRepo.findById(id);
    }

    @Override
    public User addUser(User user) {
        return uRepo.save(user);
    }


}
