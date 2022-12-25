package com.letsgo.blog.controller;

import com.letsgo.blog.dto.JwtAuthRequest;
import com.letsgo.blog.dto.JwtAuthResponse;
import com.letsgo.blog.exceptions.InvalidUserNamePassword;
import com.letsgo.blog.security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {


    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request)  {

        this.authenticate(request.getUserName(),request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUserName());

        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(token);

        return  new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);

    }

    private void authenticate(String userName, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName,password);

        try {
            this.authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException ex) {
            System.out.println("Invalid Username or Password");
            throw new InvalidUserNamePassword("Invalid Username or Password");
        }

    }

}
