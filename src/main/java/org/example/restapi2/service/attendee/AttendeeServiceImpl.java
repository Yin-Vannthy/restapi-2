package org.example.restapi2.service.attendee;

import org.example.restapi2.exception.CustomNotFoundException;
import org.example.restapi2.model.dto.request.AttendeeRequest;
import org.example.restapi2.model.dto.request.VenueRequest;
import org.example.restapi2.model.entity.Attendee;
import org.example.restapi2.repository.AttendeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Attendee> getAllAttendees(Integer offset, Integer limit) {
        if (attendeeRepository.getAllAttendees(offset, limit) == null){
            throw new CustomNotFoundException("The attendee has not been founded.");
        }
        return attendeeRepository.getAllAttendees(offset, limit);
    }

    @Override
    public Attendee getAttendeeById(Integer id) {
        if (attendeeRepository.getAttendeeById(id) == null){
            throw new CustomNotFoundException("The attendee with id " + id + " has not been founded.");
        }
        return attendeeRepository.getAttendeeById(id);
    }

    @Override
    public Attendee insertAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.insertAttendee(attendeeRequest);
    }

    @Override
    public void deleteAttendeeById(Integer id) {
        getAttendeeById(id);
        attendeeRepository.deleteAttendeeById(id);
    }

    @Override
    public Attendee updateAttendeeById(AttendeeRequest attendeeRequest, Integer id) {
        getAttendeeById(id);
        return attendeeRepository.updateAttendeeById(attendeeRequest, id);
    }

}
