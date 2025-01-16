package am.azaryan.eLearning.service;

import am.azaryan.eLearning.dto.lessonMapper.LessonRegisterRequestDto;
import am.azaryan.eLearning.dto.lessonMapper.LessonRegisterResponse;

import java.util.List;

public interface LessonRegisterService {

    boolean deleteByStudentID(int id);

    LessonRegisterResponse findByStudentId(int id);

    LessonRegisterResponse save(LessonRegisterRequestDto lessonRegisterRequestDto);

    List<LessonRegisterResponse> findAll();

}
