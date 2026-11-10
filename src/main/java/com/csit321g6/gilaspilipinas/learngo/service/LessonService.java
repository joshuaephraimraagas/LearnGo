package com.csit321g6.gilaspilipinas.learngo.service;

import com.csit321g6.gilaspilipinas.learngo.dto.LessonRequest;
import com.csit321g6.gilaspilipinas.learngo.dto.LessonResponse;

import java.util.List;

public interface LessonService {
    LessonResponse create(LessonRequest request);
    List<LessonResponse> getAll();
    List<LessonResponse> getByCourse(Long courseId);
    LessonResponse getById(Long id);
    LessonResponse update(Long id, LessonRequest request);
    void delete(Long id);
}