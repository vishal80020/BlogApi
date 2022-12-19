package com.letsgo.blog.service;

import com.letsgo.blog.dto.CommentDto;
import com.letsgo.blog.entity.Comment;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, int postId);
    void deleteComment(int commentId);

}
