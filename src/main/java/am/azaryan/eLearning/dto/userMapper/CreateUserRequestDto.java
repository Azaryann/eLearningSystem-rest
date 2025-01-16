package am.azaryan.eLearning.dto.userMapper;

import am.azaryan.eLearning.entity.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {

    private String name;
    private String surname;
    private String email;
    private UserType userType;
}
