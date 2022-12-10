package com.letsgo.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_title", length = 100, nullable = false)
    private String categoryTitle;

    @Column(name = "category_description")
    private  String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)  //mappedBy mn jo category hai wahi Post mn hai
    private List<Post> posts = new ArrayList<>();

}
