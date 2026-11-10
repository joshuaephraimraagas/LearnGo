package com.csit321g6.gilaspilipinas.learngo.dto;

public record QuizResponse(Long quizId, Long courseId, String title, Integer passingScore) {
}