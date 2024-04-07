package org.example.restapi2.model.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Venue {
    private Integer venueId;
    @NotBlank
    @NotNull
    @NotEmpty
    private String venueName;
    @NotBlank
    @NotNull
    @NotEmpty
    private String location;
}
