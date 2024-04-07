package org.example.restapi2.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Builder
public class ApiResponse <T>{
    private String message;
    private T payload;
    private HttpStatus status;
    private LocalDateTime localDateTime;
}
