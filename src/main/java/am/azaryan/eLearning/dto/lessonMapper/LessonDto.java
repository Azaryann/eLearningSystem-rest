package am.azaryan.eLearning.dto.lessonMapper;

import am.azaryan.eLearning.dto.userMapper.UserDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {

    @NotBlank(message = "Title is mandatory")
    private String title;
    private String description;
    private UserDto teacher;
}
