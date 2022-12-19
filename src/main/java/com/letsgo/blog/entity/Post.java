package com.letsgo.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @Column(name = "post_title", length = 100, nullable = false)
    private String title;

    @Column(name = "content", length = 700)
    private String content;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "created_date")
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category; // which category this post belongs to

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  //which user has created this post


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)  // mappedBy se foreign key Post table mn bane
    private Set<Comment> comments = new HashSet<>();


}
