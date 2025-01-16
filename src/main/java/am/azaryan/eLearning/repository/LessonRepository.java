package am.azaryan.eLearning.repository;

import am.azaryan.eLearning.entity.lesson.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

}
