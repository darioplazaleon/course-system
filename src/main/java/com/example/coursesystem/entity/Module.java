package com.example.coursesystem.entity;

import com.example.coursesystem.dto.ModuleAddDTO;
import com.example.coursesystem.dto.ModuleDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "modules")
@Getter
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
    private List<Lesson> lessons;

    public Module(ModuleAddDTO moduleAddDTO, Course course) {
        this.title = moduleAddDTO.title();
        this.course = course;
    }

    public void updateInfo(ModuleDTO moduleDTO) {
        this.title = moduleDTO.title();
    }
}
