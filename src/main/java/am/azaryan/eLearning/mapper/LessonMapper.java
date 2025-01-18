package am.azaryan.eLearning.mapper;

import am.azaryan.eLearning.dto.lessonMapper.LessonDto;
import am.azaryan.eLearning.dto.lessonMapper.CreateLessonRequestDto;
import am.azaryan.eLearning.dto.lessonMapper.UpdateLessonDto;
import am.azaryan.eLearning.entity.lesson.Lesson;
import am.azaryan.eLearning.entity.user.User;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonMapper {

    private final UserMapper userMapper;

    public Lesson createLesson(CreateLessonRequestDto createLessonRequestDto) {
        if (createLessonRequestDto == null) {
            return null;
        }
        return Lesson.builder()
                .title(createLessonRequestDto.getTitle())
                .description(createLessonRequestDto.getDescription())
                .teacher(User.builder().id(createLessonRequestDto.getTeacherId()).build())
                .build();
    }

    public LessonDto createLessonResponseDto(Lesson lesson) {
        if (lesson == null) {
            return null;
        }
        return LessonDto.builder()
                .title(lesson.getTitle())
                .description(lesson.getDescription())
                .teacher(userMapper.userToUserResponse(lesson.getTeacher()))
                .build();
    }

    public Lesson updateLesson(UpdateLessonDto updateLessonDto) {
        if (updateLessonDto == null) {
            return null;
        }
        return Lesson.builder()
                .id(updateLessonDto.getId())
                .title(updateLessonDto.getTitle())
                .description(updateLessonDto.getDescription())
                .build();
    }
}
