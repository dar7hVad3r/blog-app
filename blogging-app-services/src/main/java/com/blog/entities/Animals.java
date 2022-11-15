package com.blog.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Animals {
    @Id
    Integer id;

    @Column(length = 30, nullable = false)
    String name;

    @Column(length = 30, nullable = false)
    String category;
}
