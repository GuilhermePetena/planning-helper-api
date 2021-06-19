package com.planning.taskplanning.exception;

import com.planning.taskplanning.exception.errors.ResponseException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestExceptionHandler {
    private static final String UNEXPECTED_ERROR = "Um erro inesperado aconteceu!";
    private static final String RESOURCE_NOT_FOUND = "Recurso nao encontrado!";
    private static final String INVALID_JSON = "Json invalido!";
    private static final String METHOD_NOT_SUPPORTED = "Metodo nao suportado!";
    private static final String INVALID_ARGUMENTS    = "Argumentos invalidos";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseException handleException(HttpServletRequest httpServletRequest, Exception exception){
        return new ResponseException(httpServletRequest,UNEXPECTED_ERROR, exception.getMessage());
    }

    @ExceptionHandler({NoSuchElementException.class, EmptyResultDataAccessException.class, NoHandlerFoundException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseException notFoundException(HttpServletRequest httpServletRequest, Exception exception){
        return new ResponseException(httpServletRequest,RESOURCE_NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseException badRequestException(HttpServletRequest httpServletRequest, HttpMessageNotReadableException exception){
        return new ResponseException(httpServletRequest,INVALID_JSON, exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseException badRequestException(HttpServletRequest httpServletRequest, HttpRequestMethodNotSupportedException exception){
        return new ResponseException(httpServletRequest,METHOD_NOT_SUPPORTED, exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseException badRequestException(HttpServletRequest request, MethodArgumentNotValidException exception) {

        List<FieldError> errors = exception.getBindingResult().getFieldErrors();

        HashMap<String, String> detail = new HashMap<String, String>();

        for (FieldError fieldError : errors) {
            detail.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseException(request, INVALID_ARGUMENTS, detail);
    }

}
