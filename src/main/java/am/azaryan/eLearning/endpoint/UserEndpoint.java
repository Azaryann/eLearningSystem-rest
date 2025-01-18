package am.azaryan.eLearning.endpoint;

import am.azaryan.eLearning.dto.userMapper.CreateUserRequestDto;
import am.azaryan.eLearning.dto.userMapper.ResponseDeleteUserDto;
import am.azaryan.eLearning.dto.userMapper.UserResponseDto;
import am.azaryan.eLearning.exceptions.ErrorResponse;
import am.azaryan.eLearning.response.Response;
import am.azaryan.eLearning.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserEndpoint {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ErrorResponse,UserResponseDto>> getUserById(@PathVariable int id) {
        Response<ErrorResponse,UserResponseDto> userById = userService.getUserById(id);
        return ResponseEntity.ok(userById);
    }

    @PostMapping()
    public ResponseEntity<UserResponseDto> getUsers(@RequestBody CreateUserRequestDto createUserRequestDto) {
        UserResponseDto userResponseDto = userService.saveUser(createUserRequestDto);
        if (userResponseDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<ErrorResponse, ResponseDeleteUserDto>> delete(@PathVariable(name = "id") String id) {
        Response<ErrorResponse, ResponseDeleteUserDto> responseDeleteUserDto = userService.delete(id);
        return ResponseEntity.ok(responseDeleteUserDto);
    }
}
