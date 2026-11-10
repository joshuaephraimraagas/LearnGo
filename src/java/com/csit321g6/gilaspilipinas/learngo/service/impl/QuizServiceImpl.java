package java.com.csit321g6.gilaspilipinas.learngo.service.impl;

import java.com.csit321g6.gilaspilipinas.learngo.dto.QuizRequest;
import java.com.csit321g6.gilaspilipinas.learngo.dto.QuizResponse;
import java.com.csit321g6.gilaspilipinas.learngo.entity.Course;
import java.com.csit321g6.gilaspilipinas.learngo.entity.Quiz;
import java.com.csit321g6.gilaspilipinas.learngo.exception.ResourceNotFoundException;
import java.com.csit321g6.gilaspilipinas.learngo.repository.CourseRepository;
import java.com.csit321g6.gilaspilipinas.learngo.repository.QuizRepository;
import java.com.csit321g6.gilaspilipinas.learngo.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final CourseRepository courseRepository;

    @Override
    public QuizResponse create(QuizRequest request) {
        Quiz quiz = new Quiz();
        applyRequest(quiz, request);
        return toResponse(quizRepository.save(quiz));
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizResponse> getAll() {
        return quizRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizResponse> getByCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course", courseId);
        }
        return quizRepository.findByCourse_CourseId(courseId).stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public QuizResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    @Override
    public QuizResponse update(Long id, QuizRequest request) {
        Quiz quiz = findOrThrow(id);
        applyRequest(quiz, request);
        return toResponse(quizRepository.save(quiz));
    }

    @Override
    public void delete(Long id) {
        quizRepository.delete(findOrThrow(id));
    }

    private Quiz findOrThrow(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz", id));
    }

    private void applyRequest(Quiz quiz, QuizRequest request) {
        Course course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", request.courseId()));
        quiz.setCourse(course);
        quiz.setTitle(request.title());
        quiz.setPassingScore(request.passingScore());
    }

    private QuizResponse toResponse(Quiz q) {
        return new QuizResponse(q.getQuizId(), q.getCourse().getCourseId(),
                q.getTitle(), q.getPassingScore());
    }
}