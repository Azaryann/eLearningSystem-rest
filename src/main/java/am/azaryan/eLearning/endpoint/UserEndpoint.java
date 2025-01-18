package am.azaryan.eLearning.endpoint;

import am.azaryan.eLearning.dto.userMapper.CreateUserRequestDto;
import am.azaryan.eLearning.dto.userMapper.ResponseDeleteUserDto;
import am.azaryan.eLearning.dto.userMapper.UpdateUserDto;
import am.azaryan.eLearning.dto.userMapper.UserDto;
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
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ErrorResponse, UserDto>> getUserById(@PathVariable int id) {
        Response<ErrorResponse, UserDto> userById = userService.getUserById(id);
        return ResponseEntity.ok(userById);
    }

    @PostMapping()
    public ResponseEntity<UserDto> getUsers(@RequestBody CreateUserRequestDto createUserRequestDto) {
        UserDto userDto = userService.saveUser(createUserRequestDto);
        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response<ErrorResponse, UserDto>> update(@PathVariable(name = "id") String id,
                                                                   @RequestBody UpdateUserDto updateUserDto) {
        Response<ErrorResponse, UserDto> userDtoResponse = userService.update(id, updateUserDto);
        return ResponseEntity.ok(userDtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<ErrorResponse, ResponseDeleteUserDto>> delete(@PathVariable(name = "id") String id) {
        Response<ErrorResponse, ResponseDeleteUserDto> responseDeleteUserDto = userService.delete(id);
        return ResponseEntity.ok(responseDeleteUserDto);
    }
}
