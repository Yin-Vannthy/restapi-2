package org.example.restapi2.model.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    private Integer eventId;

    @NotBlank(message = "Event name must be not blank")
    @NotNull(message = "Event name must be not null")
    @NotEmpty(message = "Event name must be not empty")
    private String eventName;

    @NotBlank(message = "Event date must be not blank")
    @NotNull(message = "Event date must be not null")
    @NotEmpty(message = "Event date must be not empty")
    private Date eventDate;

    @NotBlank(message = "Venue must be not blank")
    @NotNull(message = "Venue must be not null")
    @NotEmpty(message = "Venue must be not empty")
    private Venue venueId;

    @NotBlank(message = "Attendee must be not blank")
    @NotNull(message = "Attendee must be not null")
    @NotEmpty(message = "Attendee must be not empty")
    private List<Attendee> attendees;
}
