package am.azaryan.eLearning.dto;

import am.azaryan.eLearning.dto.userMapper.UserResponseDto;
import am.azaryan.eLearning.entity.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User userToDto(UserResponseDto userResponseDto);

    UserResponseDto dtoToUser(User user);

}
