package org.example.restapi2.service.event;

import org.example.restapi2.exception.CustomNotFoundException;
import org.example.restapi2.model.dto.request.EventRequest;
import org.example.restapi2.model.entity.Attendee;
import org.example.restapi2.model.entity.Event;
import org.example.restapi2.model.entity.Venue;
import org.example.restapi2.repository.AttendeeRepository;
import org.example.restapi2.repository.EventRepository;
import org.example.restapi2.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final AttendeeRepository attendeeRepository;

    public EventServiceImpl(EventRepository eventRepository, VenueRepository venueRepository, AttendeeRepository attendeeRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Event> getAllEvents(Integer offset, Integer limit) {
        if (eventRepository.getAllEvents(offset, limit) == null){
            throw new CustomNotFoundException("The event has not been founded.");
        }
        return eventRepository.getAllEvents(offset, limit);
    }

    @Override
    public Event getEventById(Integer id) {
        if (eventRepository.getEventById(id) == null){
            throw new CustomNotFoundException("The event with id " + id + " has not been founded.");
        }
        return eventRepository.getEventById(id);
    }

    @Override
    public void deleteEventById(Integer id) {
        getEventById(id);
        eventRepository.deleteEventById(id);
    }

    @Override
    public Event insertEvent(EventRequest eventRequest) {
        Venue venue = venueRepository.getVenueById(eventRequest.getVenueId());
        if (venue == null) {
            throw new CustomNotFoundException("The venue with id " + eventRequest.getVenueId() + " does not exist.");
        }

        for (Integer attendeeId : eventRequest.getAttendeeId()){
            Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);

            if (attendee == null) {
                throw new CustomNotFoundException("The attendee with id " + attendeeId + " does not exist.");
            }
        }

        Event event = eventRepository.insertEvent(eventRequest);

        for (Integer attendeeId : eventRequest.getAttendeeId()){
            eventRepository.insertIntoEventAttendee(event.getEventId(),attendeeId);
        }

        return getEventById(event.getEventId());
    }

    @Override
    public Event updateEventById(Integer id, EventRequest eventRequest) {
        getEventById(id);

        if (venueRepository.getVenueById(eventRequest.getVenueId()) == null) {
            throw new CustomNotFoundException("The venue with id " + eventRequest.getVenueId() + " does not exist.");
        }

        for (Integer attendeeId : eventRequest.getAttendeeId()){
            Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);

            if (attendee == null) {
                throw new CustomNotFoundException("The attendee with id " + attendeeId + " does not exist.");
            }
        }

        eventRepository.deleteEventAttendeeByEventId(id);

        for (Integer attendeeId : eventRequest.getAttendeeId()){
            eventRepository.insertIntoEventAttendee(id, attendeeId);
        }

        eventRepository.updateEventById(id, eventRequest);
        return getEventById(id);
    }
}
