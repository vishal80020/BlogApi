package com.letsgo.blog.service;


import com.letsgo.blog.dto.PostDto;
import com.letsgo.blog.dto.PostResponse;
import com.letsgo.blog.entity.Post;
import javafx.geometry.Pos;

import java.util.List;

public interface PostService {

    //Create Post
    PostDto createPost(PostDto postDto, int theUserId, int theCategoryId);

    //Update Post
    PostDto updatePost(PostDto postDto, int thePostId);

    //Delete Post
    void deletePost(int thePostId);

    //Get Post
    PostDto getPostById(int thePostId);

    //Get All Post
    PostResponse getAllPost(int pageNumber, int pageSize,String sortBy,String sortDir);

    //Get All Posts By Category
    List<PostDto> getPostsByCategory(int theCategoryId);

    //Get All Posts By a User\
    List<PostDto> getPostsByUser(int theUserId);

    // Search Post By a Keyword
    List<PostDto> searchPostByTitle(String theKeyword);





}
