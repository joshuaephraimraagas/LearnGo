package java.com.csit321g6.gilaspilipinas.learngo.dto;

import java.time.LocalDateTime;

/** Note: no password field, so the hash is never sent to the client. */
public record UserResponse(Long userId, String fullName, String email,
                           String program, int points, LocalDateTime createdAt) {
}