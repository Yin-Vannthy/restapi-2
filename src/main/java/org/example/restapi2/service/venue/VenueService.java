package org.example.restapi2.service.venue;

import org.example.restapi2.model.dto.request.VenueRequest;
import org.example.restapi2.model.entity.Venue;
import org.example.restapi2.repository.VenueRepository;

import java.util.List;

public interface VenueService {

    List<Venue> getAllVenues(Integer offset, Integer limit);

    Venue getVenueById(Integer id);

    Venue insertVenue(VenueRequest venueRequest);

    void deleteVenueById(Integer id);

    Venue updateVenueById(VenueRequest venueRequest, Integer id);
}
