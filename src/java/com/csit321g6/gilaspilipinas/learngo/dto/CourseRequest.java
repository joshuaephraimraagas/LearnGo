package java.com.csit321g6.gilaspilipinas.learngo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CourseRequest(
        @NotBlank(message = "Course code is required")
        @Size(max = 20, message = "Code must be at most 20 characters") String code,
        @NotBlank(message = "Title is required") String title,
        String description) {
}