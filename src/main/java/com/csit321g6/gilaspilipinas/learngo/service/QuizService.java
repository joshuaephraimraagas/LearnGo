package  java.com.csit321g6.gilaspilipinas.learngo.service;

import  java.com.csit321g6.gilaspilipinas.learngo.dto.QuizRequest;
import  java.com.csit321g6.gilaspilipinas.learngo.dto.QuizResponse;

import java.util.List;

public interface QuizService {
    QuizResponse create(QuizRequest request);
    List<QuizResponse> getAll();
    List<QuizResponse> getByCourse(Long courseId);
    QuizResponse getById(Long id);
    QuizResponse update(Long id, QuizRequest request);
    void delete(Long id);
}