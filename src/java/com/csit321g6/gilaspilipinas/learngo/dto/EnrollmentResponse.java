package java.com.csit321g6.gilaspilipinas.learngo.dto;

import java.time.LocalDateTime;

public record EnrollmentResponse(Long enrollmentId, Long userId, Long courseId,
                                 String courseTitle, LocalDateTime enrolledAt) {
}