package airbnb.clone.api.service;

import airbnb.clone.api.entity.Rooms;
import airbnb.clone.api.repository.AddressRepository;
import airbnb.clone.api.repository.AmenitiesRepository;
import airbnb.clone.api.repository.RoomImageRepo;
import airbnb.clone.api.repository.RoomsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomsService {
    @Autowired
    private final RoomsRepo roomsRepository;

    @Autowired
    private final AddressRepository addressRepository;

    @Autowired
    private final RoomImageRepo roomImageRepo;

    @Autowired
    private final AmenitiesRepository amenitiesRepository;

    @Autowired
    public RoomsService(RoomsRepo roomsRepo, AddressRepository addressRepository, RoomImageRepo roomImageRepo, AmenitiesRepository amenitiesRepository) {
        this.roomsRepository = roomsRepo;
        this.addressRepository = addressRepository;
        this.roomImageRepo = roomImageRepo;
        this.amenitiesRepository = amenitiesRepository;
    }

    public List<Rooms> getAllRooms() {
        return roomsRepository.findAll();
    }

    public Rooms getRoomById(Long id) {
        return roomsRepository.findById(id).orElse(null);
    }

    @Transactional
    public Rooms createRoom(Rooms room) {

        Optional<Rooms> existingRoom = roomsRepository.findById(room.getId());
        if (existingRoom.isEmpty()) {
            return roomsRepository.save(room);
        }
        return null;
    }

    public Rooms updateRoom(Long id, Rooms room) {
        Optional<Rooms> existingRoom = roomsRepository.findById(id);
        if (existingRoom.isEmpty()) {
            return null;
        }

        room.setId(id);
        return roomsRepository.save(room);
    }

    public boolean deleteRoom(Long id) {
        Optional<Rooms> existingRoom = roomsRepository.findById(id);
        if (existingRoom.isEmpty()) {
            return false;
        }
        roomsRepository.deleteById(id);
        return true;
    }
}
