package com.letsgo.blog.service;

import com.letsgo.blog.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, int userId);
    UserDto getUserById(int userId);
    List<UserDto> getAllUser();
    void deleteUser(int userId);

}
