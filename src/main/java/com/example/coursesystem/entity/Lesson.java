package com.example.coursesystem.entity;

import com.example.coursesystem.dto.lesson.LessonAddDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lessons")
@Getter
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    public Lesson(LessonAddDTO lessonAddDTO, Module module, String videoUrl) {
        this.title = lessonAddDTO.title();
        this.module = module;
        this.videoUrl = videoUrl;
    }
}