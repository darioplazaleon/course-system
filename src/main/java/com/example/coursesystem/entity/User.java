package com.example.coursesystem.entity;

import com.example.coursesystem.dto.user.UserAddDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> favoriteCourses = new HashSet<>();

    public User(UserAddDTO userAddDTO, String password) {
        this.name = userAddDTO.name();
        this.email = userAddDTO.email();
        this.password = password;
        this.role = Role.STUDENT;
    }
}
