package com.letsgo.blog.controller;

import com.letsgo.blog.dto.UserDto;
import com.letsgo.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //GET  - get user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto>  userDtoList = this.userService.getAllUser();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }


    //GET  - get user
    @GetMapping("/{theUserId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int theUserId) {
        UserDto userDtoList = this.userService.getUserById(theUserId);
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    //POST - create user
    @PostMapping("/create-user")
    public ResponseEntity<UserDto> createUser( @RequestBody UserDto theUserDto) {
        UserDto createdUserDto = this.userService.createUser(theUserDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    //PUT - update user
    @PutMapping("/update-user/{theUserId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto theUserDto, @PathVariable int theUserId) {
        UserDto updatedUserDto = this.userService.updateUser(theUserDto,theUserId);
        return new ResponseEntity(updatedUserDto,HttpStatus.OK);
    }

    //DELETE - delete user
    @DeleteMapping("delete-user/{theUserId}")
    public ResponseEntity<Map<String,String>> deleteUser(@RequestBody UserDto theUserDto, @PathVariable int theUserId) {
        this.userService.deleteUser(theUserId);
        Map<String,String> messageMap = new HashMap<>();
        messageMap.put("message","User deleted successfully");
        return new ResponseEntity(messageMap,HttpStatus.OK);
    }


}
