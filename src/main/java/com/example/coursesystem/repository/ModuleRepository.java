package com.example.coursesystem.repository;

import com.example.coursesystem.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    boolean existsByTitle(String title);

}
