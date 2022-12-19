package com.letsgo.blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    private Set<CommentDto> comments = new HashSet<>();

}
