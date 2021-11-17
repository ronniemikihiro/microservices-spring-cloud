package br.com.commons.web.exception;

import br.com.commons.web.exception.entity.ErrorResponse;
import br.com.domain.exception.rules.RuleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

public class ResponseException {

    public static ResponseEntity<Object> exception(Throwable throwable) {
        ResponseEntity<Object> response;

        if (throwable == null) {
            response = ResponseEntity.internalServerError().body("Internal Server Error");
        } else {
            final HttpStatus httpStatus = throwable instanceof RuleException ? HttpStatus.UNPROCESSABLE_ENTITY : HttpStatus.INTERNAL_SERVER_ERROR;
            final ErrorResponse errorResponse = getResponseError(throwable, httpStatus);
            response = ResponseEntity.status(httpStatus).body(errorResponse);
        }

        return response;
    }

    private static ErrorResponse getResponseError(Throwable throwable, HttpStatus httpStatus) {
        return ErrorResponse.builder()
                .status(httpStatus.value())
                .error(throwable.getCause() == null ? throwable.getClass().getName() : throwable.getCause().getClass().getName())
                .message(throwable.getMessage())
                .stackTrace(throwable.getStackTrace() == null ? null : Arrays.stream(throwable.getStackTrace()).iterator().next())
                .build();
    }


}
