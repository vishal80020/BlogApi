package com.letsgo.blog.dto;

import com.letsgo.blog.entity.Category;
import com.letsgo.blog.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private int id;

    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private UserDto user;

    private CategoryDto category;

}
