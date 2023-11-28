package uz.gc.travel.logger.app.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;
import uz.gc.travel.logger.api.dto.http.FieldErrorDto;
import uz.gc.travel.logger.api.dto.http.GenericHttpResponseDto;
import uz.gc.travel.logger.api.enums.ERequestStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a.ergashev
 * Date: 11/28/2023
 * Time: 10:40 AM
 */
@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Mono<GenericHttpResponseDto> methodArgumentExceptionHandler(MethodArgumentNotValidException ex){
        List<FieldErrorDto> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fe -> new FieldErrorDto(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return Mono.just(new GenericHttpResponseDto<>(ERequestStatus.ERROR, null, "Validation error", errors));
    }
}
