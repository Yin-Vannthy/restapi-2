package org.example.restapi2.service.attendee;

import org.example.restapi2.model.dto.request.AttendeeRequest;
import org.example.restapi2.model.dto.request.VenueRequest;
import org.example.restapi2.model.entity.Attendee;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendees(Integer offset, Integer limit);

    Attendee getAttendeeById(Integer id);

    Attendee insertAttendee(AttendeeRequest attendeeRequest);

    void deleteAttendeeById(Integer id);

    Attendee updateAttendeeById(AttendeeRequest attendeeRequest, Integer id);
}
