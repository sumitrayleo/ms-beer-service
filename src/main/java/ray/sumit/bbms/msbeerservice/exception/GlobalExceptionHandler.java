package ray.sumit.bbms.msbeerservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ray.sumit.bbms.msbeerservice.enumeration.ReturnCodeEnum;
import ray.sumit.bbms.msbeerservice.exception.model.BreweriesError;
import ray.sumit.bbms.msbeerservice.exception.model.ErrorResponse;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        log.error("Model Validation failed : " + exception);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<String> validationResults = new ArrayList<>(exception.getConstraintViolations().size());
        exception.getConstraintViolations().forEach(violation -> {
            validationResults.add(violation.getPropertyPath() + " : " + violation.getMessage());
        });

        final ErrorResponse response = new ErrorResponse();
        response.getErrorList().add(new BreweriesError(ReturnCodeEnum.INVALID_REQUEST_PARAMS.toString(), exception.getLocalizedMessage(), validationResults));

        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindExceptionException(BindException exception) {
        log.error("Binding failed : " + exception);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        List<String> validationResults = new ArrayList<>(fieldErrors.size());
        fieldErrors.forEach(fieldError -> {
            validationResults.add(fieldError.getField() + " : " + fieldError.getDefaultMessage());
        });

        final ErrorResponse response = new ErrorResponse();
        response.getErrorList().add(new BreweriesError(ReturnCodeEnum.INVALID_REQUEST_PARAMS.toString(), exception.getLocalizedMessage(), validationResults));

        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleDefault(Exception exception) {
        log.error("Default Exception : ", exception);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final ErrorResponse response = new ErrorResponse();
        response.getErrorList().add(new BreweriesError(ReturnCodeEnum.DEFAULT_ERROR.toString(), exception.getMessage()));
        return new ResponseEntity<ErrorResponse>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
