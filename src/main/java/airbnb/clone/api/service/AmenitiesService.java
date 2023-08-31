package airbnb.clone.api.service;

import airbnb.clone.api.entity.Amenities;
import airbnb.clone.api.repository.AmenitiesRepository;

import java.util.List;
import java.util.Optional;

public class AmenitiesService {
    private final AmenitiesRepository amenitiesRepository;

    public AmenitiesService(AmenitiesRepository amenitiesRepository) {
        this.amenitiesRepository = amenitiesRepository;
    }


    public List<Amenities> getAllAmenities() {
        return amenitiesRepository.findAll();
    }

    public Amenities getAmenityById(Long id) {
        return amenitiesRepository.findById(id).orElse(null);
    }

    public Amenities createAmenity(Amenities amenities) {
        return amenitiesRepository.save(amenities);
    }

    public Amenities updateAmenity(Long id, Amenities amenities) {
        Amenities existingAmenities = amenitiesRepository.findById(id).orElse(null);
        if (existingAmenities == null) {
            return null;
        }

        existingAmenities.setName(amenities.getName());
        existingAmenities.setDescription(amenities.getDescription());
        return amenitiesRepository.save(existingAmenities);
    }

    public boolean deleteAmenity(Long id) {
        Optional<Amenities> amenityOptional = amenitiesRepository.findById(id);
        if (amenityOptional.isPresent()) {
            Amenities amenity = amenityOptional.get();
            amenitiesRepository.delete(amenity);
            return true; // Successfully deleted
        } else {
            return false; // Amenity not found
        }
    }
}
