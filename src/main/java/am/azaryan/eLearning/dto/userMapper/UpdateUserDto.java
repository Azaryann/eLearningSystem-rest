package am.azaryan.eLearning.dto.userMapper;

import am.azaryan.eLearning.entity.user.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {

    private UserType userType;
    private Boolean active;

}
