package am.azaryan.eLearning.dto.lessonMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonRegisterRequestDto {

    private int lessonId;
    private int studentId;
}
