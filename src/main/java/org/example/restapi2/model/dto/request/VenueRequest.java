package org.example.restapi2.model.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class VenueRequest {
    @NotBlank(message = "Venue name must be not blank")
    @NotNull(message = "Venue name must be not null")
    @NotEmpty(message = "Venue name must be not empty")
    private String venueName;

    @NotBlank(message = "Location must be not blank")
    @NotNull(message = "Location must be not null")
    @NotEmpty(message = "Location must be not empty")
    private String location;
}
