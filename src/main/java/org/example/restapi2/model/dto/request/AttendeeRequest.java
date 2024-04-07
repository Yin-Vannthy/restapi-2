package org.example.restapi2.model.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AttendeeRequest {
    @NotBlank(message = "Attendee name must be not blank")
    @NotNull(message = "Attendee name must be not null")
    @NotEmpty(message = "Attendee name must be not empty")
    private String attendeeName;

    @NotBlank(message = "Email must be not blank")
    @NotNull(message = "Email must be not null")
    @NotEmpty(message = "Email must be not empty")
    @Email(message = "Invalid Email")
    private String email;
}
