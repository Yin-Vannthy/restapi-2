package org.example.restapi2.repository;

import org.apache.ibatis.annotations.*;
import org.example.restapi2.model.dto.request.AttendeeRequest;
import org.example.restapi2.model.entity.Attendee;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    @Select("""
                SELECT * FROM attendees ORDER BY attendee_id LIMIT #{limit} OFFSET (#{offset} - 1) * #{limit}
            """)
    @Results(id = "attendeeMapping", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })
    List<Attendee> getAllAttendees(Integer offset, Integer limit);

    @Select("""
            SELECT * FROM attendees WHERE attendee_id = #{id}
            """)
    @ResultMap("attendeeMapping")
    Attendee getAttendeeById(Integer id);

    @Select("""
                INSERT INTO attendees (attendee_name, email) 
                VALUES (#{attendee.attendeeName},#{attendee.email}) 
                RETURNING *
            """)
    @ResultMap("attendeeMapping")
    Attendee insertAttendee(@Param("attendee") AttendeeRequest attendeeRequest);

    @Select("""
                DELETE FROM attendees WHERE attendee_id = #{id}
                RETURNING *
            """)
    @ResultMap("attendeeMapping")
    void deleteAttendeeById(Integer id);

    @Select("""
                UPDATE attendees 
                SET attendee_name = #{attendee.attendeeName}, email = #{attendee.email} 
                WHERE attendee_id = #{id}
                RETURNING *
            """)
    @ResultMap("attendeeMapping")
    Attendee updateAttendeeById(@Param("attendee") AttendeeRequest attendeeRequest, Integer id);

    @Select("""
                SELECT a.* FROM attendees a 
                INNER JOIN event_attendee ea ON a.attendee_id = ea.attendee_id
                WHERE ea.event_id = #{eventId}
                ORDER BY a.attendee_id
            """)
    @ResultMap("attendeeMapping")
    List<Attendee> getAttendeeByEventId(Integer eventId);

}
