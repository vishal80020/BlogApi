package com.letsgo.blog.security;

import com.letsgo.blog.entity.User;
import com.letsgo.blog.exceptions.ResourceNotFoundException;
import com.letsgo.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //loading user from database by username
        User user = this.userRepository.findByEmail(username)
                                        .orElseThrow(()-> new ResourceNotFoundException("User","UserEmail",username));

        return user; //see return type is UserDetails but we are returning User
        //still no error because User class has implemented UserDetails

    }
}
