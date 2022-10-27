package com.letsgo.blog.repository;

import com.letsgo.blog.dto.UserDto;
import com.letsgo.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

}
