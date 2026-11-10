package java.com.csit321g6.gilaspilipinas.learngo.repository;

import com.csit321section.groupname.projectname.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    // Lessons of one course, in their intended order (course.courseId, sorted by orderNo)
    List<Lesson> findByCourse_CourseIdOrderByOrderNoAsc(Long courseId);
}