package java.com.csit321g6.gilaspilipinas.learngo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LessonRequest(
        @NotNull(message = "courseId is required") Long courseId,
        @NotBlank(message = "Title is required") String title,
        @NotBlank(message = "Content is required") String content,
        @NotNull(message = "orderNo is required") @Min(value = 1, message = "orderNo must be at least 1") Integer orderNo) {
}