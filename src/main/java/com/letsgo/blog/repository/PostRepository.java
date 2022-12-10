package com.letsgo.blog.repository;

import com.letsgo.blog.entity.Category;
import com.letsgo.blog.entity.Post;
import com.letsgo.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    // select user from Post where user_id = id;
    List<User> findByUser(User user);

    // select category from Post where category_id = id;
    List<Category> findAllByCategory(Category category);

}
