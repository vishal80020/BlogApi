package com.letsgo.blog.controller;

import com.letsgo.blog.dto.ApiResponse;
import com.letsgo.blog.dto.CommentDto;
import com.letsgo.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create-comment/{postId}")
    ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                            @PathVariable int postId) {

        CommentDto createdCommentDto = this.commentService.createComment(commentDto, postId);
        return  new ResponseEntity<>(createdCommentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-comment/{commentId}")
    ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity(new ApiResponse("Comment is deleted successfully !!",true), HttpStatus.OK);
    }



}
