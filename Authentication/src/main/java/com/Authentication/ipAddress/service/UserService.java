package com.Authentication.ipAddress.service;

import com.Authentication.ipAddress.entity.User;

public interface UserService {

    User signup (User user);

    String homepage ();

    User getUser (Integer id);

    User addUser(User user);



}
