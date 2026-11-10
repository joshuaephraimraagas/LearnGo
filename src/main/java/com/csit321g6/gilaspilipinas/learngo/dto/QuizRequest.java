package com.csit321g6.gilaspilipinas.learngo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuizRequest(
        @NotNull(message = "courseId is required") Long courseId,
        @NotBlank(message = "Title is required") String title,
        @NotNull(message = "passingScore is required")
        @Min(value = 0, message = "passingScore must be 0 to 100")
        @Max(value = 100, message = "passingScore must be 0 to 100") Integer passingScore) {
}