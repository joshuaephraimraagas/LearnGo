package java.com.csit321g6.gilaspilipinas.learngo.controller;

import java.com.csit321g6.gilaspilipinas.learngo.dto.QuizRequest;
import java.com.csit321g6.gilaspilipinas.learngo.dto.QuizResponse;
import java.com.csit321g6.gilaspilipinas.learngo.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizResponse> create(@Valid @RequestBody QuizRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<QuizResponse>> getAll() {
        return ResponseEntity.ok(quizService.getAll());
    }

    @GetMapping("/by-course/{courseId}")
    public ResponseEntity<List<QuizResponse>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(quizService.getByCourse(courseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizResponse> update(@PathVariable Long id,
                                               @Valid @RequestBody QuizRequest request) {
        return ResponseEntity.ok(quizService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        quizService.delete(id);
        return ResponseEntity.noContent().build();
    }
}