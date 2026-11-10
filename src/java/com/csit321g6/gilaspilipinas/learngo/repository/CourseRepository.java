package java.com.csit321g6.gilaspilipinas.learngo.repository;

import com.csit321section.groupname.projectname.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCode(String code);
}