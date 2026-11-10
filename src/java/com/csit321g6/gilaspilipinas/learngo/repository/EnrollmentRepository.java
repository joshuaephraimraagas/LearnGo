package java.com.csit321g6.gilaspilipinas.learngo.repository;

import com.csit321section.groupname.projectname.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByUser_UserIdAndCourse_CourseId(Long userId, Long courseId);
    List<Enrollment> findByUser_UserId(Long userId);
}