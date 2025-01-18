package am.azaryan.eLearning.service.impl;

import am.azaryan.eLearning.dto.userMapper.UserDto;
import am.azaryan.eLearning.exceptions.ErrorResponse;
import am.azaryan.eLearning.exceptions.RecordNotFoundException;
import am.azaryan.eLearning.mapper.LessonMapper;
import am.azaryan.eLearning.dto.lessonMapper.LessonDto;
import am.azaryan.eLearning.dto.lessonMapper.CreateLessonRequestDto;
import am.azaryan.eLearning.dto.lessonMapper.UpdateLessonDto;
import am.azaryan.eLearning.entity.lesson.Lesson;
import am.azaryan.eLearning.entity.user.User;
import am.azaryan.eLearning.entity.user.UserType;
import am.azaryan.eLearning.repository.LessonRepository;
import am.azaryan.eLearning.repository.UserRepository;
import am.azaryan.eLearning.response.Response;
import am.azaryan.eLearning.service.LessonService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    private final LessonMapper lessonMapper;

    private final UserRepository userRepository;

    @Override
    public List<LessonDto> findAll() {
        List<Lesson> lessons = lessonRepository.findAll();
        List<LessonDto> lessonDto = new ArrayList<>();
        for (Lesson lesson : lessons) {
            lessonDto.add(lessonMapper.createLessonResponseDto(lesson));
        }
        return lessonDto;
    }

    @Override
    public Response<ErrorResponse, LessonDto> findById(int id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User is not found with id : " + id));
        return new Response<>(null, lessonMapper.createLessonResponseDto(lesson), LessonDto.class.getSimpleName());
    }

    @Override
    public LessonDto save(CreateLessonRequestDto createLessonRequestDto) {
        Optional<User> userDb = userRepository.findById(createLessonRequestDto.getTeacherId());
        if (userDb.isPresent()) {
            User user = userDb.get();
            if (user.getUserType() == UserType.TEACHER) {
                return lessonMapper.createLessonResponseDto(lessonRepository.save(lessonMapper.createLesson(createLessonRequestDto)));
            }
        }
        return null;
    }

    @Override
    public LessonDto editLesson(UpdateLessonDto updateLessonDto) {
        if (lessonRepository.existsById(updateLessonDto.getId())) {
            return lessonMapper.createLessonResponseDto(lessonRepository.save(lessonMapper.updateLesson(updateLessonDto)));
        }
        return null;
    }


    @Override
    public boolean deleteById(int id) {
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
