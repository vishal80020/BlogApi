package com.letsgo.blog.service.impl;
import com.letsgo.blog.dto.PostDto;
import com.letsgo.blog.entity.Category;
import com.letsgo.blog.entity.Post;
import com.letsgo.blog.entity.User;
import com.letsgo.blog.exceptions.ResourceNotFoundException;
import com.letsgo.blog.repository.CategoryRepository;
import com.letsgo.blog.repository.PostRepository;
import com.letsgo.blog.repository.UserRepository;
import com.letsgo.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, int theUserId, int theCategoryId) {

        User theUser = this.userRepository.findById(theUserId)
                .orElseThrow(()-> new ResourceNotFoundException("User","User Id",theUserId));

        Category theCategory = this.categoryRepository.findById(theCategoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",theCategoryId));

        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(theUser);
        post.setCategory(theCategory);
        Post createdPost = this.postRepository.save(post);
        return this.modelMapper.map(createdPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int thePostId) {
        Post post = this.postRepository.findById(thePostId)
                .orElseThrow(() -> new ResourceNotFoundException("Post","Post Id",thePostId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post updatedPost = this.postRepository.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(int thePostId) {
        Post post = this.postRepository.findById(thePostId)
                    .orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",thePostId));
        this.postRepository.delete(post);
    }

    @Override
    public PostDto getPostById(int thePostId) {
        Post post = this.postRepository.findById(thePostId)
                .orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",thePostId));
        PostDto postDto = this.modelMapper.map(post,PostDto.class);
        return postDto;
    }

    @Override
    public List<PostDto> getAllPost() {

        List<Post> postList = this.postRepository.findAll();
        List<PostDto> postDtoList =postList.stream().map((post) -> this.modelMapper.map(post,PostDto.class))
                                    .collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> getPostsByCategory(int theCategoryId) {
        Category theCategory = this.categoryRepository.findById(theCategoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",theCategoryId));
        List<Post> postList = this.postRepository.findByCategory(theCategory);
        List<PostDto>  postDtoList = postList.stream().map((post) -> this.modelMapper.map(post,PostDto.class))
                                    .collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> getPostsByUser(int theUserId) {
        User theUser = this.userRepository.findById(theUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User","User Id",theUserId));

        List<Post> postList = this.postRepository.findByUser(theUser);
        List<PostDto> postDtoList = postList.stream().map((post )-> this.modelMapper.map(post,PostDto.class))
                                    .collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> searchPost(String theKeyword) {
        return null;
    }
}
