package am.azaryan.eLearning.mapper;

import am.azaryan.eLearning.dto.userMapper.CreateUserRequestDto;
import am.azaryan.eLearning.dto.userMapper.UserDto;
import am.azaryan.eLearning.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User userRequestToUser(CreateUserRequestDto createUserRequest) {

        if (createUserRequest == null) {
            return null;
        }

        return User.builder()
                .name(createUserRequest.getName())
                .surname(createUserRequest.getSurname())
                .email(createUserRequest.getEmail())
                .userType(createUserRequest.getUserType())
                .build();

    }

    public UserDto userToUserResponse(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .userType(user.getUserType())
                .build();
    }
}
