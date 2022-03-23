package com.amkurakin32.topjogger.rest;

import com.amkurakin32.topjogger.model.response.TopJoggerErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleGenericException(final Exception ex, final ServletWebRequest req) {
        return handleExceptionInternal(ex, new TopJoggerErrorResponse(ex.getMessage(), ex.getClass().getName()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, req);
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    protected ResponseEntity<Object> handleNoSuchElementException(final NoSuchElementException ex, final ServletWebRequest req) {
        return handleExceptionInternal(ex, new TopJoggerErrorResponse(ex.getMessage(), ex.getClass().getName()), new HttpHeaders(), HttpStatus.NOT_FOUND, req);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handleIllegalArgumentException(final IllegalArgumentException ex, final ServletWebRequest req) {
        return handleExceptionInternal(ex, new TopJoggerErrorResponse(ex.getMessage(), ex.getClass().getName()), new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDeniedException(final AccessDeniedException ex, final ServletWebRequest req) {
        return handleExceptionInternal(ex, new TopJoggerErrorResponse(ex.getMessage(), ex.getClass().getName()), new HttpHeaders(), HttpStatus.FORBIDDEN, req);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    protected ResponseEntity<Object> handleAuthenticationException(final AuthenticationException ex, final ServletWebRequest req) {
        return handleExceptionInternal(ex, new TopJoggerErrorResponse(ex.getMessage(), ex.getClass().getName()), new HttpHeaders(), HttpStatus.UNAUTHORIZED, req);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        String message = methodName((ServletWebRequest) request) + " failed. ";

        if (HttpStatus.INTERNAL_SERVER_ERROR == status) {
            log.error(message, ex);
        } else {
            log.warn(message + " " + ex.getClass().getName() + ": " + ex.getMessage());
            log.debug("Exception details: ", ex);
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private String methodName(final ServletWebRequest req) {
        return req.getHttpMethod().name() + " method " + req.getRequest().getRequestURI();
    }
}
