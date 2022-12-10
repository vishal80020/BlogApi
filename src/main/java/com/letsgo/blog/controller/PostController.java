package com.letsgo.blog.controller;

import com.letsgo.blog.dto.ApiResponse;
import com.letsgo.blog.dto.PostDto;
import com.letsgo.blog.service.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //Get all posts created a User
    @GetMapping("user/{theUserId}")
    public  ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable int theUserId) {
        List<PostDto> postDtoList = this.postService.getPostsByUser(theUserId);
        return  new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    //Get all posts belong to a Category
    @GetMapping("category/{theCategoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategoryId(@PathVariable int theCategoryId) {
        List<PostDto> postDtoList = this.postService.getPostsByCategory(theCategoryId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }


    //Get All Post
    @GetMapping("")
    public  ResponseEntity<List<PostDto>> getAllPost() {
        List<PostDto> postDtoList = this.postService.getAllPost();
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    //Get Post By Id
    @GetMapping("/{thePostId}")
    public  ResponseEntity<PostDto> getPostById(@PathVariable int thePostId) {
        PostDto postDto = this.postService.getPostById(thePostId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    //Update Post
    @PutMapping("update-post/{thePostId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable int thePostId) {
        PostDto updatedPostDto = this.postService.updatePost(postDto,thePostId);
        return new ResponseEntity<>(updatedPostDto,HttpStatus.OK);
    }

    //Delete Post
    @DeleteMapping("delete-post/{thePostId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int thePostId) {
        this.postService.deletePost(thePostId);
        return new ResponseEntity<>(new ApiResponse("Post is deleted successfully",true),HttpStatus.OK);
    }

}
