package java.com.csit321g6.gilaspilipinas.learngo.service.impl;

import java.com.csit321g6.gilaspilipinas.learngo.dto.CourseRequest;
import java.com.csit321g6.gilaspilipinas.learngo.dto.CourseResponse;
import java.com.csit321g6.gilaspilipinas.learngo.entity.Course;
import java.com.csit321g6.gilaspilipinas.learngo.exception.DuplicateResourceException;
import java.com.csit321g6.gilaspilipinas.learngo.exception.ResourceNotFoundException;
import java.com.csit321g6.gilaspilipinas.learngo.repository.CourseRepository;
import java.com.csit321g6.gilaspilipinas.learngo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseResponse create(CourseRequest request) {
        if (courseRepository.existsByCode(request.code())) {
            throw new DuplicateResourceException("Course code already exists: " + request.code());
        }
        Course course = new Course();
        applyRequest(course, request);
        return toResponse(courseRepository.save(course));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> getAll() {
        return courseRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    @Override
    public CourseResponse update(Long id, CourseRequest request) {
        Course course = findOrThrow(id);
        if (!course.getCode().equalsIgnoreCase(request.code())
                && courseRepository.existsByCode(request.code())) {
            throw new DuplicateResourceException("Course code already exists: " + request.code());
        }
        applyRequest(course, request);
        return toResponse(courseRepository.save(course));
    }

    @Override
    public void delete(Long id) {
        courseRepository.delete(findOrThrow(id)); // cascades to lessons, quizzes, enrollments
    }

    private Course findOrThrow(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));
    }

    private void applyRequest(Course course, CourseRequest request) {
        course.setCode(request.code());
        course.setTitle(request.title());
        course.setDescription(request.description());
    }

    private CourseResponse toResponse(Course c) {
        return new CourseResponse(c.getCourseId(), c.getCode(), c.getTitle(), c.getDescription());
    }
}