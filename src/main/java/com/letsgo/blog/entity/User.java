package com.letsgo.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table( name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column( name = "name", nullable = false, length = 100)
    private String name;

    @Column (name = "email")
    private  String email;

    @Column(name = "password")
    private  String password;

    @Column(name = "about")
    private  String about;


}
