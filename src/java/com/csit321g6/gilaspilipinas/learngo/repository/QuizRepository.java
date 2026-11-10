package java.com.csit321g6.gilaspilipinas.learngo.repository;

import com.csit321section.groupname.projectname.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCourse_CourseId(Long courseId);
}