package com.letsgo.blog.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    private int id;

    private String name;
}
