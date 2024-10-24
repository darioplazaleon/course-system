package com.example.coursesystem.entity;

import com.example.coursesystem.dto.category.CategoryAddDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Course> courses;

    public Category(CategoryAddDTO categoryAddDTO) {
    this.name = categoryAddDTO.name();
    }

    public void updateCategory(CategoryAddDTO categoryAddDTO) {
    this.name = categoryAddDTO.name();
    }
}
