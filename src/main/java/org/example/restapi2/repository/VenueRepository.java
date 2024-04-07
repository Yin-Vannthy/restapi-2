package org.example.restapi2.repository;

import org.apache.ibatis.annotations.*;
import org.example.restapi2.model.dto.request.VenueRequest;
import org.example.restapi2.model.entity.Venue;

import java.util.List;

@Mapper
public interface VenueRepository {
    @Select("""
                SELECT * FROM venues ORDER BY venue_id LIMIT #{limit} OFFSET (#{offset} - 1) * #{limit} 
            """)
    @Results(id = "venueMapping", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name")
    })
    List<Venue> getAllVenues(Integer offset, Integer limit);

    @Select("""
                SELECT * FROM venues WHERE venue_id = #{id}
            """)
    @ResultMap(value = "venueMapping")
    Venue getVenueById(Integer id);

    @Select("""
                    INSERT INTO venues(venue_name, location) 
                    VALUES (#{venue.venueName},#{venue.location}) 
                    RETURNING *
            """)
    @ResultMap(value = "venueMapping")
    Venue insertVenue(@Param("venue") VenueRequest venueRequest);

    @Select("""
            DELETE FROM venues WHERE venue_id = #{id} RETURNING *
            """)
    @ResultMap(value = "venueMapping")
    void deleteVenueById(Integer id);

    @Select("""
        UPDATE venues SET 
        venue_name = #{venue.venueName},
        location = #{venue.location} 
        WHERE venue_id = #{id} 
        RETURNING *
        """)
    @ResultMap(value = "venueMapping")
    Venue updateVenueById(@Param("venue") VenueRequest venueRequest, Integer id);


}
