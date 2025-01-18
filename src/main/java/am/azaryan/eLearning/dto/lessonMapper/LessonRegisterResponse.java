package am.azaryan.eLearning.dto.lessonMapper;

import am.azaryan.eLearning.dto.userMapper.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonRegisterResponse {

    private UserDto student;
    private LessonDto lesson;

}
