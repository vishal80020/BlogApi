package com.letsgo.blog.dto;

import lombok.Data;

@Data //lombok will generate getters and setters method
public class UserDto {

    private int id;

    private String name;

    private String email;

    private String password;

    private String about;

}
