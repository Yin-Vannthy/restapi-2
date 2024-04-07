package org.example.restapi2.service.venue;

import org.example.restapi2.exception.CustomNotFoundException;
import org.example.restapi2.model.dto.request.VenueRequest;
import org.example.restapi2.model.entity.Venue;
import org.example.restapi2.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService{
    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> getAllVenues(Integer offset, Integer limit) {
        if(venueRepository.getAllVenues(offset, limit) == null){
            throw new CustomNotFoundException("The venue has not been founded.");
        }
        return venueRepository.getAllVenues(offset, limit);
    }

    @Override
    public Venue getVenueById(Integer id) {
        if(venueRepository.getVenueById(id) == null){
            throw new CustomNotFoundException("The venue id "+ id +" has not been founded.");
        }
        return venueRepository.getVenueById(id);
    }

    @Override
    public Venue insertVenue(VenueRequest venueRequest) {
        return venueRepository.insertVenue(venueRequest);
    }

    @Override
    public void deleteVenueById(Integer id) {
        getVenueById(id);
        venueRepository.deleteVenueById(id);
    }

    @Override
    public Venue updateVenueById(VenueRequest venueRequest, Integer id) {
        getVenueById(id);
        return venueRepository.updateVenueById(venueRequest, id);
    }
}
