package am.azaryan.eLearning.repository;

import am.azaryan.eLearning.entity.lesson.LessonRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonRegisterRepository extends JpaRepository<LessonRegister, Integer> {

    Optional<LessonRegister> findLessonRegisterByStudent(int id);

}
