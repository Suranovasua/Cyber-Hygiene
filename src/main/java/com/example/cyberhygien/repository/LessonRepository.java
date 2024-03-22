package com.example.cyberhygien.repository;

import com.example.cyberhygien.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
