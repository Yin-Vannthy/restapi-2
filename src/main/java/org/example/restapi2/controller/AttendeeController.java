package org.example.restapi2.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.restapi2.model.dto.request.AttendeeRequest;
import org.example.restapi2.model.dto.response.ApiResponse;
import org.example.restapi2.model.entity.Attendee;
import org.example.restapi2.service.attendee.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @Operation(summary = "Get all attendees with limit and offset")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllVenues(
            @RequestParam(defaultValue = "1") @Positive(message = "Offset must be greater than 0") Integer offset,
            @RequestParam(defaultValue = "3") @Positive(message = "Limit must be greater than 0") Integer limit
    ) {
        List<Attendee> attendees = attendeeService.getAllAttendees(offset, limit);

        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .message("All attendees have been successfully fetched.")
                .payload(attendees)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get attendee by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(
            @Positive(message = "Id must be greater than 0") @PathVariable Integer id
    ) {
        Attendee attendee = attendeeService.getAttendeeById(id);

        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("The venue has been successfully founded.")
                .payload(attendee)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add new attendee")
    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> insertAttendee(@Valid @RequestBody AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeService.insertAttendee(attendeeRequest);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("The venue has been successfully added.")
                .payload(attendee)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete attendee by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteAttendeeById(@PathVariable Integer id) {
        attendeeService.deleteAttendeeById(id);
        ApiResponse<?> response = ApiResponse.builder()
                .message("The venue has been deleted successfully.")
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update attendee by ID")
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendeeById(
            @PathVariable Integer id,
            @Valid @RequestBody AttendeeRequest attendeeRequest
    ) {
        Attendee attendee = attendeeService.updateAttendeeById(attendeeRequest, id);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("The venue has been updated successfully.")
                .payload(attendee)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
