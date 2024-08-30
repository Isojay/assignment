package com.assignment.Utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * It is return Object where
 * result is mapped on the details
 * message about the task performed
 * status is the httpstatus in message form
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    private String status;

    private String message;

    private Object detail;


    public static ResponseDTO customResponseDTO(HttpStatus statusCode, String message, Object detail) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setDetail(detail);
        responseDTO.setStatus(statusCode.getReasonPhrase());
        responseDTO.setMessage(message);
        return responseDTO;
    }
}
