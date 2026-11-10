package com.csit321g6.gilaspilipinas.learngo.service;

import java.com.csit321g6.gilaspilipinas.learngo.dto.EnrollmentRequest;
import java.com.csit321g6.gilaspilipinas.learngo.dto.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse create(EnrollmentRequest request);
    List<EnrollmentResponse> getAll();
    List<EnrollmentResponse> getByUser(Long userId);
    EnrollmentResponse getById(Long id);
    EnrollmentResponse update(Long id, EnrollmentRequest request);
    void delete(Long id);
}