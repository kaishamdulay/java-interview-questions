package com.collective.backbase.advice;

import com.collective.backbase.controller.UrlSanitizerController;
import com.collective.backbase.exceptions.ApiKeyException;
import com.collective.backbase.exceptions.ShortUrlNotFoundException;
import com.collective.backbase.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice(assignableTypes = UrlSanitizerController.class)
public class BaseExceptionHandlerAdvice {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BindException.class)
  private BaseResponse handleBindException(BindException bindException) {
    FieldError fieldError = bindException.getBindingResult().getFieldError();
    return createBaseResponse(fieldError.getDefaultMessage(), fieldError.getCode(), "FAILURE");
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ApiKeyException.class)
  private BaseResponse handleApiKeyException(ApiKeyException bindException) {
    return createBaseResponse(bindException.getMessage(), "ERR-CODE-5", "FAILURE");
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  private BaseResponse handleException(Exception bindException) {
    return createBaseResponse(bindException.getMessage(), "ERR-CODE-6", "FAILURE");
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingServletRequestParameterException.class)
  private BaseResponse handleMissingServletRequestParameterException(
      MissingServletRequestParameterException bindException) {
    return createBaseResponse(bindException.getMessage(), "ERR-CODE-3", "FAILURE");
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  private BaseResponse handleMissingServletRequestParameterException(
      MethodArgumentNotValidException bindException) {
    return createBaseResponse(bindException.getMessage(), "ERR-CODE-1", "FAILURE");
  }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  private BaseResponse handleConstraintViolationException(
          ConstraintViolationException bindException) {
    return createBaseResponse(bindException.getMessage(), "ERR-CODE-2", "FAILURE");
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ShortUrlNotFoundException.class)
  private BaseResponse handleShortUrlNotFoundException(
          ShortUrlNotFoundException bindException) {
    return createBaseResponse(bindException.getMessage(), "ERR-CODE-4", "FAILURE");
  }

  private BaseResponse createBaseResponse(
      String responseMessage, String responseCode, String status) {
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setResponseCode(responseCode);
    baseResponse.setStatus(status);
    baseResponse.setResponseMessage(responseMessage);
    return baseResponse;
  }
}
