package com.letsgo.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "categories")
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
}
