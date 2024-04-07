package org.example.restapi2.service.event;

import org.example.restapi2.model.dto.request.EventRequest;
import org.example.restapi2.model.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(Integer offset, Integer limit);

    Event getEventById(Integer id);

    void deleteEventById(Integer id);

    Event insertEvent(EventRequest eventRequest);

    Event updateEventById(Integer id, EventRequest eventRequest);
}
