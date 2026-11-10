package com.csit321g6.gilaspilipinas.learngo.repository;

import com.csit321g6.gilaspilipinas.learngo.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCourse_CourseId(Long courseId);
}