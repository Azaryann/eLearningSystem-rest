package am.azaryan.eLearning.service;

import am.azaryan.eLearning.dto.userMapper.CreateUserRequestDto;
import am.azaryan.eLearning.dto.userMapper.UserResponseDto;
import am.azaryan.eLearning.exceptions.ErrorResponse;
import am.azaryan.eLearning.response.Response;

import java.util.List;

public interface UserService {

    Response<ErrorResponse, UserResponseDto> getUserById(int id);

    boolean deleteUserById(int id);

    UserResponseDto saveUser(CreateUserRequestDto createUserRequestDto);

    List<UserResponseDto> getAllUsers();
}
