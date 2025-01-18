package am.azaryan.eLearning.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    private String errorCode;
    private String errorStatus;
    private String errorMessage;
    private List<String> errorList;
    private LocalDateTime localDateTime;

    public ErrorResponse(String errorCode, String errorStatus, String errorMessage, List<String> errorList) {
        this.errorCode = errorCode;
        this.errorStatus = errorStatus;
        this.errorMessage = errorMessage;
        this.errorList = errorList;
        this.localDateTime = LocalDateTime.now();
    }
}
