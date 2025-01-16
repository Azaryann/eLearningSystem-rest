package am.azaryan.eLearning.service;

import am.azaryan.eLearning.dto.userMapper.CreateUserRequestDto;
import am.azaryan.eLearning.dto.userMapper.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto getUserById(int id);

    boolean deleteUserById(int id);

    UserResponseDto saveUser(CreateUserRequestDto createUserRequestDto);

    List<UserResponseDto> getAllUsers();
}
