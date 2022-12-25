package com.letsgo.blog.dto;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String userName;

    private String password;
}
