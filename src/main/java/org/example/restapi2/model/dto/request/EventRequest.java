package org.example.restapi2.model.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.restapi2.model.entity.Attendee;
import org.example.restapi2.model.entity.Venue;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class EventRequest {
    @NotBlank(message = "Event name must be not blank")
    @NotNull(message = "Event name must be not null")
    @NotEmpty(message = "Event name must be not empty")
    private String eventName;

    @NotBlank(message = "Event date must be not blank")
    @NotNull(message = "Event date must be not null")
    @NotEmpty(message = "Event date must be not empty")
    private Date eventDate;

    @NotBlank(message = "Venue Id must be not blank")
    @NotNull(message = "Venue Id must be not null")
    @NotEmpty(message = "Venue Id must be not empty")
    private Integer venueId;
    private List<Integer> attendeeId;
}
