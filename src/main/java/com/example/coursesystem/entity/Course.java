package com.example.coursesystem.entity;

import com.example.coursesystem.dto.course.CourseAddDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@NoArgsConstructor
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String description;
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "instructor_id", nullable = false)
  private User instructor;

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
  private List<Module> modules;

  @OneToMany(mappedBy = "course")
  private List<Enrollment> enrollments;

  @OneToMany(mappedBy = "course")
  private List<Rating> ratings;

  @ManyToMany
  @JoinTable(
      name = "course_languages",
      joinColumns = @JoinColumn(name = "course_id"),
      inverseJoinColumns = @JoinColumn(name = "language_id"))
  private Set<Language> languages = new HashSet<>();

  @ManyToMany
  @JoinTable(
      name = "course_categories",
      joinColumns = @JoinColumn(name = "course_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private Set<Category> categories = new HashSet<>();

  public Course(CourseAddDTO courseAddDTO, User instructor) {
    this.title = courseAddDTO.title();
    this.description = courseAddDTO.description();
    this.price = courseAddDTO.price();
    this.instructor = instructor;
  }

  public void updateInfo(CourseAddDTO courseAddDTO) {
    this.title = courseAddDTO.title();
    this.description = courseAddDTO.description();
  }

  public double getAverageRating() {
    if (this.ratings == null || this.ratings.isEmpty()) {
      return 0.0;
    }

    return this.ratings.stream().mapToInt(Rating::getScore).average().orElse(0.0);
  }
}
