package am.azaryan.eLearning.dto;

import am.azaryan.eLearning.dto.lessonMapper.LessonDto;
import am.azaryan.eLearning.entity.lesson.Lesson;
import org.mapstruct.Mapper;

@Mapper
public interface LessonMapper {

    Lesson lessonToDto(LessonDto lessonDto);

    LessonDto dtoToLesson(Lesson lesson);

}
