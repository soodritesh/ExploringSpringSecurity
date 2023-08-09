package com.Authentication.ipAddress.repository;

import com.Authentication.ipAddress.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;


@Repository
public class UserRepository {

    private Set<User> users = new HashSet<>();

    public User save (User user){
        users.add(user);
        return user;
    }

    public User findById (Integer id){
        for (User user : users){
            if (user.getId() == id) return user;
        }

        return null;
    }

    public User findByEmail (String email){
        for (User user : users){
            if (user.getEmail().equals(email)) return user;
        }

        return null;
    }


}
