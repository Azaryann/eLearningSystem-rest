package am.azaryan.eLearning.service;

import am.azaryan.eLearning.dto.lessonMapper.LessonDto;
import am.azaryan.eLearning.dto.lessonMapper.CreateLessonRequestDto;
import am.azaryan.eLearning.dto.lessonMapper.UpdateLessonDto;

import java.util.List;

public interface LessonService {

    List<LessonDto> findAll();

    LessonDto findById(int id);

    LessonDto editLesson(UpdateLessonDto updateLessonDto);

    LessonDto save(CreateLessonRequestDto createLessonRequestDto);

    boolean deleteById(int id);
}
