package com.example.coursesystem.entity;

import com.example.coursesystem.dto.module.ModuleAddDTO;
import com.example.coursesystem.dto.module.ModuleDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "modules")
@Getter
@Setter
@NoArgsConstructor
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private List<Lesson> lessons = new ArrayList<>();

    public Module(ModuleAddDTO moduleAddDTO, Course course) {
        this.title = moduleAddDTO.title();
        this.course = course;
    }

    public Module(String title, Course course) {
        this.title = title;
        this.course = course;
    }

    public void updateInfo(ModuleDTO moduleDTO) {
        this.title = moduleDTO.title();
    }

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
        lesson.setModule(this);
    }
}
