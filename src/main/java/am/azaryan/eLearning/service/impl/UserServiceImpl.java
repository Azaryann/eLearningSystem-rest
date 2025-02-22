package am.azaryan.eLearning.service.impl;

import am.azaryan.eLearning.dto.userMapper.ResponseDeleteUserDto;
import am.azaryan.eLearning.dto.userMapper.UpdateUserDto;
import am.azaryan.eLearning.exceptions.ErrorResponse;
import am.azaryan.eLearning.exceptions.RecordNotFoundException;
import am.azaryan.eLearning.mapper.UserMapper;
import am.azaryan.eLearning.dto.userMapper.CreateUserRequestDto;
import am.azaryan.eLearning.dto.userMapper.UserDto;
import am.azaryan.eLearning.entity.user.User;
import am.azaryan.eLearning.repository.UserRepository;
import am.azaryan.eLearning.response.Response;
import am.azaryan.eLearning.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public Response<ErrorResponse, UserDto> getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User is not found with id : " + id));
        return new Response<>(null, userMapper.userToUserResponse(user), UserDto.class.getSimpleName());
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> result = new ArrayList<>();
        for (User user : users) {
            result.add(userMapper.userToUserResponse(user));
        }
        return result;
    }

    @Override
    public Response<ErrorResponse, ResponseDeleteUserDto> delete(String id) {
        User user = userRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new RecordNotFoundException("User is not found with id : " + id));
        user.setActive(true);
        User savedUser = userRepository.save(user);
        return new Response<>(null,
                new ResponseDeleteUserDto(savedUser.isActive()), ResponseDeleteUserDto.class.getSimpleName());
    }

    @Override
    public Response<ErrorResponse, UserDto> update(String id, UpdateUserDto updateUserDto) {
        User user = userRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new RecordNotFoundException("User is not found with id : " + id));
        User updatedUser = updateUserFields(updateUserDto, user);
        User save = userRepository.save(updatedUser);
        return new Response<>(null, userMapper.userToUserResponse(save), UserDto.class.getSimpleName());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public UserDto saveUser(CreateUserRequestDto createUserRequestDto) {
        if (createUserRequestDto != null && userRepository.findUserByEmail(createUserRequestDto.getEmail()).isEmpty()) {
            return userMapper.userToUserResponse(userRepository.save(userMapper.userRequestToUser(createUserRequestDto)));
        }
        return null;
    }

    private User updateUserFields(UpdateUserDto updateUserDto, User user) {
        user.setUserType(updateUserDto.getUserType());
        user.setActive(updateUserDto.getActive());
        return user;
    }
}
