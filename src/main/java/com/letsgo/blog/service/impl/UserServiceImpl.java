package com.letsgo.blog.service.impl;

import com.letsgo.blog.dto.UserDto;
import com.letsgo.blog.entity.User;
import com.letsgo.blog.exceptions.ResourceNotFoundException;
import com.letsgo.blog.repository.UserRepository;
import com.letsgo.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service // spring will pick it up during component scanning
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.userDtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        UserDto savedUserDto = this.userToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepository.save(user);

        UserDto updatedUserDto = this.userToUserDto(updatedUser);
        return updatedUserDto;
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
        return this.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = this.userRepository.findAll();

        List<UserDto> userDtoList = userList.stream()
                                    .map(user -> this.userToUserDto(user))
                                    .collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public void deleteUser(int userId) {

        User user = this.userRepository.findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));
        this.userRepository.delete(user);
    }


    public User userDtoToUser(UserDto theUserDto) {
        User theUser = new User();
        theUser.setId(theUserDto.getId());
        theUser.setName(theUserDto.getName());
        theUser.setEmail(theUserDto.getEmail());
        theUser.setPassword(theUserDto.getPassword());
        theUser.setAbout(theUserDto.getAbout());
        return  theUser;
    }

    public UserDto userToUserDto(User theUser) {
        UserDto theUserDto = new UserDto();
        theUserDto.setId(theUser.getId());
        theUserDto.setName(theUser.getName());
        theUserDto.setEmail(theUser.getEmail());
        theUserDto.setPassword(theUser.getPassword());
        theUserDto.setAbout(theUser.getAbout());
        return theUserDto;
    }
}
