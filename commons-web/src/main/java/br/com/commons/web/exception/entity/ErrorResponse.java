package br.com.commons.web.exception.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private Integer status;
    private String error;
    private String message;
    private StackTraceElement stackTrace;

}
