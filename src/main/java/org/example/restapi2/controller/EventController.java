package org.example.restapi2.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Positive;
import org.example.restapi2.model.dto.request.EventRequest;
import org.example.restapi2.model.dto.response.ApiResponse;
import org.example.restapi2.model.entity.Event;
import org.example.restapi2.service.event.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Get all events with limit and offset")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllVenues(
            @RequestParam(defaultValue = "1") @Positive(message = "Offset must be greater than 0") Integer offset,
            @RequestParam(defaultValue = "3") @Positive(message = "Limit must be greater than 0") Integer limit
    ) {
        List<Event> events = eventService.getAllEvents(offset, limit);

        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .message("All attendees have been fetched successfully.")
                .payload(events)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get event by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable Integer id) {
        Event event = eventService.getEventById(id);

        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("The event has been founded successfully.")
                .payload(event)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete event by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteEventById(@PathVariable Integer id) {
        eventService.deleteEventById(id);

        ApiResponse<?> response = ApiResponse.builder()
                .message("The event has been deleted successfully.")
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add new event")
    @PostMapping
    public ResponseEntity<?> insertEvent(@RequestBody EventRequest eventRequest) {
        Event event = eventService.insertEvent(eventRequest);

        ApiResponse<?> response = ApiResponse.builder()
                .message("The event has been inserted successfully.")
                .payload(event)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update event by ID")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEventById(@PathVariable Integer id, @RequestBody EventRequest eventRequest) {
        Event event = eventService.updateEventById(id, eventRequest);

        ApiResponse<?> response = ApiResponse.builder()
                .message("The event has been updated successfully.")
                .payload(event)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
