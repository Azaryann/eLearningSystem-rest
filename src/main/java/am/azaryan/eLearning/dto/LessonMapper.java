package am.azaryan.eLearning.dto;

import am.azaryan.eLearning.dto.lessonMapper.LessonResponseDto;
import am.azaryan.eLearning.entity.lesson.Lesson;
import org.mapstruct.Mapper;

@Mapper
public interface LessonMapper {

    Lesson lessonToDto(LessonResponseDto lessonResponseDto);

    LessonResponseDto dtoToLesson(Lesson lesson);

}
