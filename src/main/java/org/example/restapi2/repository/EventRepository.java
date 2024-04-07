package org.example.restapi2.repository;

import org.apache.ibatis.annotations.*;
import org.example.restapi2.model.dto.request.EventRequest;
import org.example.restapi2.model.entity.Event;

import java.util.List;

@Mapper
public interface EventRepository {
    @Select("""
                SELECT DISTINCT e.event_id, e.event_name, e.event_date, v.*
                FROM events e
                INNER JOIN venues v ON e.venue_id = v.venue_id
                LEFT JOIN event_attendee ea ON e.event_id = ea.event_id
                LEFT JOIN attendees a ON ea.attendee_id = a.attendee_id
                ORDER BY e.event_id
                LIMIT #{limit} OFFSET (#{offset} - 1) * #{limit}
            """)
    @Results(id = "eventMapping", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venueId", column = "venue_id",
                    one = @One(select = "org.example.restapi2.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "org.example.restapi2.repository.AttendeeRepository.getAttendeeByEventId"))
    })
    List<Event> getAllEvents(Integer offset, Integer limit);

    @Select("""
                SELECT * FROM events WHERE event_id = #{id}
            """)
    @ResultMap("eventMapping")
    Event getEventById(Integer id);

    @Delete("""
                DELETE FROM events WHERE event_id = #{id}
            """)
    void deleteEventById(Integer id);

    @Select("""
                INSERT INTO events(event_name, event_date, venue_id) 
                VALUES (#{event.eventName}, #{event.eventDate}, #{event.venueId})
                RETURNING *
            """)
    @ResultMap("eventMapping")
    Event insertEvent(@Param("event") EventRequest eventRequest);

    @Select("""
               INSERT INTO event_attendee(event_id, attendee_id) 
               VALUES (#{eventId}, #{attendeeId})
            """)
    void insertIntoEventAttendee(Integer eventId, Integer attendeeId);

    @Delete("""
                DELETE FROM event_attendee WHERE event_id = #{id}
            """)
    void deleteEventAttendeeByEventId(Integer id);

    @Update("""
                UPDATE events 
                SET event_name = #{event.eventName}, event_date = #{event.eventDate}, venue_id = #{event.venueId}
                WHERE event_id = #{id}
            """)
    void updateEventById(Integer id, @Param("event") EventRequest eventRequest);
}
