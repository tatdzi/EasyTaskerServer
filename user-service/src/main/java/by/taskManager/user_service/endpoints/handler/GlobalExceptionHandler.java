package by.taskManager.user_service.endpoints.handler;


import by.TaskManeger.utils.error.Error;
import by.TaskManeger.utils.error.ErrorResponse;
import by.TaskManeger.utils.error.StructuredErrorResponse;
import by.taskManager.user_service.core.exception.DtUpdateNotCorrectException;
import by.taskManager.user_service.core.exception.NotCorrectUUIDException;
import by.taskManager.user_service.core.exception.StrcturedErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            IllegalArgumentException.class,
            NotCorrectUUIDException.class,
            DtUpdateNotCorrectException.class})
    public ResponseEntity<List<Error>> handler(Exception e){
        ErrorResponse exception = new ErrorResponse();
        exception.setError(new Error("Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз"));
        exception.setError(new Error(e.getMessage()));
        return new ResponseEntity<>(exception.getErrors(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<StructuredErrorResponse> handler(StrcturedErrorException exception){
        StructuredErrorResponse errorResponse = new StructuredErrorResponse(exception.getErrors());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
/*
    @ExceptionHandler(value = {
            IOException.class, java.lang.Error.class,
            RuntimeException.class,})
    public ResponseEntity<List<Error>> handler5(Exception e){
        ErrorResponse exception = new ErrorResponse();
        exception.setError(new Error("Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору"));
        exception.setError(new Error(e.getMessage()));
        return new ResponseEntity<>(exception.getErrors(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

 */




}
