package com.letsgo.blog.controller;

import com.letsgo.blog.dto.UserDto;
import com.letsgo.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //GET  - get user

    //POST - create user
    @PostMapping("/create-user")
    public ResponseEntity<UserDto> createUser( @RequestBody UserDto theUserDto) {
        UserDto createdUserDto = this.userService.createUser(theUserDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }



    //PUT - update user

    //DELETE - delete user


}
