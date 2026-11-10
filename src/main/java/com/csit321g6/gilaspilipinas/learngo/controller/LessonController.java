package com.csit321g6.gilaspilipinas.learngo.controller;

import java.com.csit321g6.gilaspilipinas.learngo.dto.LessonRequest;
import java.com.csit321g6.gilaspilipinas.learngo.dto.LessonResponse;
import java.com.csit321g6.gilaspilipinas.learngo.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping
    public ResponseEntity<LessonResponse> create(@Valid @RequestBody LessonRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<LessonResponse>> getAll() {
        return ResponseEntity.ok(lessonService.getAll());
    }

    // Lessons of one course in their intended order (shows the Course 1-N Lesson relationship)
    @GetMapping("/by-course/{courseId}")
    public ResponseEntity<List<LessonResponse>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(lessonService.getByCourse(courseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonResponse> update(@PathVariable Long id,
                                                 @Valid @RequestBody LessonRequest request) {
        return ResponseEntity.ok(lessonService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}