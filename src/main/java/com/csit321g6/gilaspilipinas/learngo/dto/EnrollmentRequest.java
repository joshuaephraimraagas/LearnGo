package com.csit321g6.gilaspilipinas.learngo.dto;

import jakarta.validation.constraints.NotNull;

public record EnrollmentRequest(
        @NotNull(message = "userId is required") Long userId,
        @NotNull(message = "courseId is required") Long courseId) {
}