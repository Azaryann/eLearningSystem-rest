package am.azaryan.eLearning.dto;

import am.azaryan.eLearning.dto.userMapper.UserDto;
import am.azaryan.eLearning.entity.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User userToDto(UserDto userDto);

    UserDto dtoToUser(User user);

}
