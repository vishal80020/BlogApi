package com.letsgo.blog.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data //lombok will generate getters and setters method
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "Username must be min of 4 characters !!")
    private String name;

    @Email(message = "Email address is invalid !!")
    private String email;

    @NotEmpty
    @Size(min = 8, max=25, message = "Password must be min of 8 chars and max of 25 chars !!")
    private String password;

    @NotEmpty
    private String about;

}
