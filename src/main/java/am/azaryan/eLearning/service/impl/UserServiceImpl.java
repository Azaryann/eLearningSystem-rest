package am.azaryan.eLearning.service.impl;

import am.azaryan.eLearning.mapper.UserMapper;
import am.azaryan.eLearning.dto.userMapper.CreateUserRequestDto;
import am.azaryan.eLearning.dto.userMapper.UserResponseDto;
import am.azaryan.eLearning.entity.user.User;
import am.azaryan.eLearning.repository.UserRepository;
import am.azaryan.eLearning.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserResponseDto getUserById(int id) {
        return userMapper.userToUserResponse(userRepository.getReferenceById(id));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> result = new ArrayList<>();
        for (User user : users) {
            result.add(userMapper.userToUserResponse(user));
        }
        return result;
    }

    @Override
    public UserResponseDto saveUser(CreateUserRequestDto createUserRequestDto) {
        if (createUserRequestDto != null && userRepository.findUserByEmail(createUserRequestDto.getEmail()).isEmpty()) {
            return userMapper.userToUserResponse(userRepository.save(userMapper.userRequestToUser(createUserRequestDto)));
        }
        return null;
    }

    @Override
    public boolean deleteUserById(int id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
