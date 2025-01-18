package am.azaryan.eLearning.dto.userMapper;

import am.azaryan.eLearning.entity.user.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 20, message = "Name should be between 3 and 20 characters")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 3, max = 20, message = "Surname should be between 3 and 20 characters")
    private String surname;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email is invalid")
    private String email;

    private UserType userType;

}
