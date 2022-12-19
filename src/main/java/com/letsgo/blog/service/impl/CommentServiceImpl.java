package com.letsgo.blog.service.impl;

import com.letsgo.blog.dto.CommentDto;
import com.letsgo.blog.entity.Comment;
import com.letsgo.blog.entity.Post;
import com.letsgo.blog.exceptions.ResourceNotFoundException;
import com.letsgo.blog.repository.CommentRepository;
import com.letsgo.blog.repository.PostRepository;
import com.letsgo.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, int postId) {

        Post post = this.postRepository.findById(postId)
                    .orElseThrow(()-> new ResourceNotFoundException("Post","Post_Id",postId));
        Comment comment  = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment createdComment = this.commentRepository.save(comment);
        return this.modelMapper.map(createdComment,CommentDto.class);
    }

    @Override
    public void deleteComment(int commentId) {
        Comment comment  = this.commentRepository.findById(commentId)
                            .orElseThrow(()-> new ResourceNotFoundException("Comment","Comment Id",commentId));
        this.commentRepository.delete(comment);
    }
}
