package org.example.restapi2.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.restapi2.model.dto.request.VenueRequest;
import org.example.restapi2.model.dto.response.ApiResponse;
import org.example.restapi2.model.entity.Venue;
import org.example.restapi2.service.venue.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @Operation(summary = "Get all venues with limit and offset")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(
            @RequestParam(defaultValue = "1") @Positive(message = "Offset must be greater than 0") Integer offset,
            @RequestParam(defaultValue = "3") @Positive(message = "Limit must be greater than 0") Integer limit
    ) {
        List<Venue> venues = venueService.getAllVenues(offset, limit);

        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .message("All venues have been successfully fetched.")
                .payload(venues)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get venue by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(
            @Positive(message = "Id must be greater than 0") @PathVariable Integer id
    ) {
        Venue venue = venueService.getVenueById(id);

        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("The venue has been successfully founded.")
                .payload(venue)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add new venue")
    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> insertVenue(@Valid @RequestBody VenueRequest venueRequest) {
        Venue venue = venueService.insertVenue(venueRequest);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("The venue has been successfully added.")
                .payload(venue)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete venue by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteVenueById(@PathVariable Integer id) {
        venueService.deleteVenueById(id);
        ApiResponse<?> response = ApiResponse.builder()
                .message("The venue has been deleted successfully.")
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update venue by ID")
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Venue>> updateVenueById(@PathVariable Integer id, @Valid @RequestBody VenueRequest venueRequest) {
        Venue venue = venueService.updateVenueById(venueRequest, id);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("The venue has been updated successfully.")
                .payload(venue)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

}
