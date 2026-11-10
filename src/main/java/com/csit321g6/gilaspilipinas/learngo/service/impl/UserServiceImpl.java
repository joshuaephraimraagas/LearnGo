package com.csit321g6.gilaspilipinas.learngo.service.impl;

import com.csit321g6.gilaspilipinas.learngo.dto.UserRequest;
import com.csit321g6.gilaspilipinas.learngo.dto.UserResponse;
import com.csit321g6.gilaspilipinas.learngo.entity.User;
import com.csit321g6.gilaspilipinas.learngo.exception.DuplicateResourceException;
import com.csit321g6.gilaspilipinas.learngo.exception.ResourceNotFoundException;
import com.csit321g6.gilaspilipinas.learngo.repository.UserRepository;
import com.csit321g6.gilaspilipinas.learngo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor          // constructor injection of the final fields
@Transactional                    // each method runs in one database transaction
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(UserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email already registered: " + request.email());
        }
        User user = new User();
        applyRequest(user, request);
        return toResponse(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        User user = findOrThrow(id);
        // Only check for duplicates if the email is actually being changed
        if (!user.getEmail().equalsIgnoreCase(request.email())
                && userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email already registered: " + request.email());
        }
        applyRequest(user, request);
        return toResponse(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(findOrThrow(id));
    }

    // ----- helpers -----
    private User findOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    private void applyRequest(User user, UserRequest request) {
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password())); // never store plain text
        user.setProgram(request.program());
    }

    private UserResponse toResponse(User u) {
        return new UserResponse(u.getUserId(), u.getFullName(), u.getEmail(),
                u.getProgram(), u.getPoints(), u.getCreatedAt());
    }
}