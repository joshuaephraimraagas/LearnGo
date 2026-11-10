package com.csit321g6.gilaspilipinas.learngo.service.impl;

import com.csit321g6.gilaspilipinas.learngo.dto.LessonRequest;
import com.csit321g6.gilaspilipinas.learngo.dto.LessonResponse;
import com.csit321g6.gilaspilipinas.learngo.entity.Course;
import com.csit321g6.gilaspilipinas.learngo.entity.Lesson;
import com.csit321g6.gilaspilipinas.learngo.exception.ResourceNotFoundException;
import com.csit321g6.gilaspilipinas.learngo.repository.CourseRepository;
import com.csit321g6.gilaspilipinas.learngo.repository.LessonRepository;
import com.csit321g6.gilaspilipinas.learngo.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public LessonResponse create(LessonRequest request) {
        Lesson lesson = new Lesson();
        applyRequest(lesson, request);
        return toResponse(lessonRepository.save(lesson));
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonResponse> getAll() {
        return lessonRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonResponse> getByCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course", courseId);
        }
        return lessonRepository.findByCourse_CourseIdOrderByOrderNoAsc(courseId)
                .stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LessonResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    @Override
    public LessonResponse update(Long id, LessonRequest request) {
        Lesson lesson = findOrThrow(id);
        applyRequest(lesson, request);
        return toResponse(lessonRepository.save(lesson));
    }

    @Override
    public void delete(Long id) {
        lessonRepository.delete(findOrThrow(id));
    }

    private Lesson findOrThrow(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson", id));
    }

    private void applyRequest(Lesson lesson, LessonRequest request) {
        Course course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", request.courseId()));
        lesson.setCourse(course);
        lesson.setTitle(request.title());
        lesson.setContent(request.content());
        lesson.setOrderNo(request.orderNo());
    }

    private LessonResponse toResponse(Lesson l) {
        return new LessonResponse(l.getLessonId(), l.getCourse().getCourseId(),
                l.getTitle(), l.getContent(), l.getOrderNo());
    }
}