package am.azaryan.eLearning.service;

import am.azaryan.eLearning.dto.userMapper.CreateUserRequestDto;
import am.azaryan.eLearning.dto.userMapper.ResponseDeleteUserDto;
import am.azaryan.eLearning.dto.userMapper.UpdateUserDto;
import am.azaryan.eLearning.dto.userMapper.UserDto;
import am.azaryan.eLearning.exceptions.ErrorResponse;
import am.azaryan.eLearning.response.Response;

import java.util.List;

public interface UserService {

    Response<ErrorResponse, UserDto> getUserById(int id);

    UserDto saveUser(CreateUserRequestDto createUserRequestDto);

    List<UserDto> getAllUsers();

    Response<ErrorResponse, ResponseDeleteUserDto> delete(String id);

    Response<ErrorResponse, UserDto> update(String id, UpdateUserDto userDto);
}
