package org.example.restfulsamplejpa.adviser;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class RestControllerAdviser {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HashMap<String, Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception
    ) {
//        First Method
        List<HashMap<String, Object>> errors =
        exception.getBindingResult()
                .getFieldErrors()
                .stream().map(err->{
                    HashMap<String, Object> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).toList();
        HashMap<String, Object> errorMessages = new HashMap<>();
        errorMessages.put("message", errors);
        return errorMessages;


//        Second Method
//        return new HashMap<String, Object>() {{
//            put("message", exception.getBindingResult()
//                    .getFieldErrors()
//                    .stream().map(err -> new HashMap<String, Object>() {{
//                                put(err.getField(), err.getDefaultMessage());
//                            }}
//                    ).toList());
//        }};

    }



//        Third Method
//    public Map<String, Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException exception
//    ) {
//        List<Map<String, Object>> errors = new ArrayList<>();
//        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
//            Map<String, Object> error = Map.of(
//                    "field", fieldError.getField(),
//                    "message", Objects.requireNonNull(fieldError.getDefaultMessage())
//            );
//            errors.add(error);
//        });
//        return Map.of("message", errors);
//    }
}
