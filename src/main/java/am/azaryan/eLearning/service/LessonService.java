package am.azaryan.eLearning.service;

import am.azaryan.eLearning.dto.lessonMapper.LessonResponseDto;
import am.azaryan.eLearning.dto.lessonMapper.CreateLessonRequestDto;
import am.azaryan.eLearning.dto.lessonMapper.UpdateLessonDto;

import java.util.List;

public interface LessonService {

    List<LessonResponseDto> findAll();

    LessonResponseDto findById(int id);

    LessonResponseDto editLesson(UpdateLessonDto updateLessonDto);

    LessonResponseDto save(CreateLessonRequestDto createLessonRequestDto);

    boolean deleteById(int id);
}
