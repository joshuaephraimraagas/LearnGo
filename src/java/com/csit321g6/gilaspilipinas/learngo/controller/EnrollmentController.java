package java.com.csit321g6.gilaspilipinas.learngo.controller;

import java.com.csit321g6.gilaspilipinas.learngo.dto.EnrollmentRequest;
import java.com.csit321g6.gilaspilipinas.learngo.dto.EnrollmentResponse;
import java.com.csit321g6.gilaspilipinas.learngo.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponse> create(@Valid @RequestBody EnrollmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponse>> getAll() {
        return ResponseEntity.ok(enrollmentService.getAll());
    }

    // "Show the student's enrolled courses in one place" (functional requirement F4)
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<EnrollmentResponse>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(enrollmentService.getByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> update(@PathVariable Long id,
                                                     @Valid @RequestBody EnrollmentRequest request) {
        return ResponseEntity.ok(enrollmentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}