package java.com.csit321g6.gilaspilipinas.learngo.service.impl;

import java.com.csit321g6.gilaspilipinas.learngo.dto.EnrollmentRequest;
import java.com.csit321g6.gilaspilipinas.learngo.dto.EnrollmentResponse;
import java.com.csit321g6.gilaspilipinas.learngo.entity.Course;
import java.com.csit321g6.gilaspilipinas.learngo.entity.Enrollment;
import java.com.csit321g6.gilaspilipinas.learngo.entity.User;
import java.com.csit321g6.gilaspilipinas.learngo.exception.DuplicateResourceException;
import java.com.csit321g6.gilaspilipinas.learngo.exception.ResourceNotFoundException;
import java.com.csit321g6.gilaspilipinas.learngo.repository.CourseRepository;
import java.com.csit321g6.gilaspilipinas.learngo.repository.EnrollmentRepository;
import java.com.csit321g6.gilaspilipinas.learngo.repository.UserRepository;
import java.com.csit321g6.gilaspilipinas.learngo.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public EnrollmentResponse create(EnrollmentRequest request) {
        if (enrollmentRepository.existsByUser_UserIdAndCourse_CourseId(request.userId(), request.courseId())) {
            throw new DuplicateResourceException("User is already enrolled in this course");
        }
        Enrollment enrollment = new Enrollment();
        applyRequest(enrollment, request);
        return toResponse(enrollmentRepository.save(enrollment));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getAll() {
        return enrollmentRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", userId);
        }
        return enrollmentRepository.findByUser_UserId(userId).stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EnrollmentResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    @Override
    public EnrollmentResponse update(Long id, EnrollmentRequest request) {
        Enrollment enrollment = findOrThrow(id);
        boolean pairChanged = !enrollment.getUser().getUserId().equals(request.userId())
                || !enrollment.getCourse().getCourseId().equals(request.courseId());
        if (pairChanged && enrollmentRepository
                .existsByUser_UserIdAndCourse_CourseId(request.userId(), request.courseId())) {
            throw new DuplicateResourceException("User is already enrolled in this course");
        }
        applyRequest(enrollment, request);
        return toResponse(enrollmentRepository.save(enrollment));
    }

    @Override
    public void delete(Long id) {
        enrollmentRepository.delete(findOrThrow(id));
    }

    private Enrollment findOrThrow(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", id));
    }

    private void applyRequest(Enrollment enrollment, EnrollmentRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User", request.userId()));
        Course course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", request.courseId()));
        enrollment.setUser(user);
        enrollment.setCourse(course);
    }

    private EnrollmentResponse toResponse(Enrollment e) {
        return new EnrollmentResponse(e.getEnrollmentId(), e.getUser().getUserId(),
                e.getCourse().getCourseId(), e.getCourse().getTitle(), e.getEnrolledAt());
    }
}