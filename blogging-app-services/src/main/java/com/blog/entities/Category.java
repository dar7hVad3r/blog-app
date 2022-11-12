package com.blog.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "description", length = 100, nullable = true)
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(title, category.title) && Objects.equals(description, category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }
}
