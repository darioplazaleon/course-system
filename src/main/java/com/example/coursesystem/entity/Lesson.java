package com.example.coursesystem.entity;

import com.example.coursesystem.dto.lesson.LessonAddDTO;
import com.example.coursesystem.dto.lesson.LessonModuleDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Setter
    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    public Lesson(LessonAddDTO lessonAddDTO, Module module, String videoUrl) {
        this.title = lessonAddDTO.title();
        this.module = module;
        this.videoUrl = videoUrl;
    }

    public Lesson(LessonModuleDTO lessonModuleDTO, Module module) {
        this.title = lessonModuleDTO.title();
        this.module = module;
        this.videoUrl = lessonModuleDTO.video();
    }

}