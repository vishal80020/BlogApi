package com.letsgo.blog.controller;

import com.letsgo.blog.dto.PostDto;
import com.letsgo.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    //Post create a Post
    @PostMapping("create-post/user/{theUserId}/category/{theCategoryId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable int theUserId,
                                              @PathVariable int theCategoryId) {

        PostDto createdPost = this.postService.createPost(postDto,theUserId,theCategoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
}
