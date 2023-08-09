package com.Authentication.ipAddress.controller;

import com.Authentication.ipAddress.entity.User;
import com.Authentication.ipAddress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService uSer;

    @PostMapping("/public/signup")
    public ResponseEntity<User> signupHandler (@RequestBody User user){
        User savedUser = uSer.signup(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/public/homepage")
    public ResponseEntity<String> homepageHandler (){
        String message = uSer.homepage();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/private/user/{id}")
    public ResponseEntity<User> getByIdHandler (@PathVariable Integer id){
        User user = uSer.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/private/user")
    public ResponseEntity<User> addUserHandler (@RequestBody User user){
        User savedUser = uSer.signup(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


}
