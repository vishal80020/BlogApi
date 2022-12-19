package com.letsgo.blog.controller;

import com.letsgo.blog.config.AppConstants;
import com.letsgo.blog.dto.ApiResponse;
import com.letsgo.blog.dto.PostDto;
import com.letsgo.blog.dto.PostResponse;
import com.letsgo.blog.service.FileService;
import com.letsgo.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;


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
    public  ResponseEntity<PostResponse> getAllPost(
            @RequestParam (value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) int pageNumber,
            @RequestParam (value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
            @RequestParam (value = "sortBy", defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam (value = "sortDir", defaultValue = AppConstants.SORT_DIR,required = false) String sortDir) {
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
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


    //Search
    @GetMapping("/search/{theKeyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String theKeyword) {
        List<PostDto> postDtoList = this.postService.searchPostByTitle(theKeyword);
        return  new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }


    //Post Image Upload
    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image")MultipartFile image,
                                                   @PathVariable int postId) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path,image);
        postDto.setImageName(fileName);
        PostDto updatedPostDto = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatedPostDto,HttpStatus.OK);
    }

    //Method to Serve file
    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName,
                              HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }


}
