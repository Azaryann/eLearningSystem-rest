package am.azaryan.eLearning.exceptions;

import am.azaryan.eLearning.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Response<ErrorResponse, ?>> handleAllExceptions(Exception ex) {
        List<String> errorList = new LinkedList<>();
        errorList.add(ex.getLocalizedMessage());
        errorList.add(ex.getCause().getMessage());
        ErrorResponse error = new ErrorResponse("500", "ServerException", "Something wrong with server",
                errorList);
        Response<ErrorResponse, ?> response = new Response<>(error, null, ex.getClass().getSimpleName());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            errors.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }


    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Response<ErrorResponse, ?>> handleUserNotFoundException(RecordNotFoundException ex) {
        List<String> errorList = new LinkedList<>();
        errorList.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("404", "NOT_FOUND", "Record not found",
                errorList);
        Response<ErrorResponse, ?> response = new Response<>(error, null, ex.getClass().getSimpleName());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParamInvalidException.class)
    public final ResponseEntity<Response<ErrorResponse, ?>> handleParameterInvalidException(ParamInvalidException ex) {
        List<String> errorList = new LinkedList<>();
        errorList.add(ex.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("400", "BAD_REQUEST", "Parameter is not correct",
                errorList);
        Response<ErrorResponse, ?> response = new Response<>(errorResponse, null, ex.getClass().getSimpleName());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public final ResponseEntity<Response<ErrorResponse, ?>> handleConflictException(ConflictException ex) {
        List<String> errorList = new LinkedList<>();
        errorList.add(ex.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("409", "CONFLICT", "There is conflict this request",
                errorList);
        Response<ErrorResponse, ?> response = new Response<>(errorResponse, null, ex.getClass().getSimpleName());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ForbiddenException.class)
    public final ResponseEntity<Response<ErrorResponse, ?>> handleForbiddenException(ForbiddenException ex) {
        List<String> errorList = new LinkedList<>();
        errorList.add(ex.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("403", "FORBIDDEN",
                "You don't have that authority", errorList);
        Response<ErrorResponse, ?> response = new Response<>(errorResponse, null, ex.getClass().getSimpleName());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}