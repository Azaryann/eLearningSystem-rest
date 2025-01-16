package am.azaryan.eLearning.mapper;

import am.azaryan.eLearning.dto.lessonMapper.LessonRegisterRequestDto;
import am.azaryan.eLearning.dto.lessonMapper.LessonRegisterResponse;
import am.azaryan.eLearning.entity.lesson.Lesson;
import am.azaryan.eLearning.entity.lesson.LessonRegister;
import am.azaryan.eLearning.entity.user.User;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonRegisterMapper {

    private final LessonMapper lessonMapper;

    private final UserMapper userMapper;


    public LessonRegister toLessonRegistration(LessonRegisterRequestDto registrationRequestDto) {
        if (registrationRequestDto == null) {
            return null;
        }
        return LessonRegister.builder()
                .student(User.builder()
                        .id(registrationRequestDto.getStudentId())
                        .build())
                .lesson(Lesson.builder()
                        .id(registrationRequestDto.getLessonId())
                        .build())
                .build();
    }

    public LessonRegisterResponse lessonRegistrationResponse(LessonRegister lessonRegister) {
        if (lessonRegister == null) {
            return null;
        }
        return LessonRegisterResponse.builder()
                .lesson(lessonMapper.createLessonResponseDto(lessonRegister.getLesson()))
                .student(userMapper.userToUserResponse(lessonRegister.getStudent()))
                .build();
    }
}
