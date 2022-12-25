package com.letsgo.blog.controller;

import com.letsgo.blog.dto.ApiResponse;
import com.letsgo.blog.dto.UserDto;
import com.letsgo.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

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
        UserDto userDto = this.userService.getUserById(theUserId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    //POST - create user
    @PostMapping("/create-user")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto theUserDto) {
        UserDto createdUserDto = this.userService.createUser(theUserDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    //PUT - update user
    @PutMapping("/update-user/{theUserId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto theUserDto, @PathVariable int theUserId) {
        UserDto updatedUserDto = this.userService.updateUser(theUserDto,theUserId);
        return new ResponseEntity(updatedUserDto,HttpStatus.OK);
    }

    //ADMIN
    //DELETE - delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete-user/{theUserId}")
    public ResponseEntity<ApiResponse> deleteUser(@RequestBody UserDto theUserDto, @PathVariable int theUserId) {
        this.userService.deleteUser(theUserId);
        return new ResponseEntity(new ApiResponse("User deleted successfully", true),HttpStatus.OK);
    }


}
