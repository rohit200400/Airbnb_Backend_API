package airbnb.clone.api.controller;

import airbnb.clone.api.entity.Amenities;
import airbnb.clone.api.service.AmenitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amenities")
public class AmenitiesController {

    private final AmenitiesService amenitiesService;

    public AmenitiesController(AmenitiesService amenitiesService) {
        this.amenitiesService = amenitiesService;
    }

    @GetMapping
    public ResponseEntity<List<Amenities>> getAllAmenities() {
        List<Amenities> amenities = amenitiesService.getAllAmenities();
        return ResponseEntity.ok(amenities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Amenities> getAmenityById(@PathVariable Long id) {
        Amenities amenity = amenitiesService.getAmenityById(id);
        if (amenity != null) {
            return ResponseEntity.ok(amenity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Amenities> createAmenity(@RequestBody Amenities amenities) {
        Amenities createdAmenity = amenitiesService.createAmenity(amenities);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAmenity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Amenities> updateAmenity(@PathVariable Long id, @RequestBody Amenities amenities) {
        Amenities updatedAmenity = amenitiesService.updateAmenity(id, amenities);
        if (updatedAmenity != null) {
            return ResponseEntity.ok(updatedAmenity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAmenity(@PathVariable Long id) {
        boolean deleted = amenitiesService.deleteAmenity(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
