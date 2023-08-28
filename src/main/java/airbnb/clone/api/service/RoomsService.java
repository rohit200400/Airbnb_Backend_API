package airbnb.clone.api.service;

import airbnb.clone.api.entity.Rooms;
import airbnb.clone.api.repository.RoomsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomsService {
    @Autowired
    private final RoomsRepo roomsRepository;

    public RoomsService(RoomsRepo roomsRepo) {
        this.roomsRepository = roomsRepo;
    }

    public List<Rooms> getAllRooms() {
        return roomsRepository.findAll();
    }

    public Rooms getRoomById(Long id) {
        return roomsRepository.findById(id).orElse(null);
    }

    public Rooms createRoom(Rooms room) {
        return roomsRepository.save(room);
    }

    public Rooms updateRoom(Long id, Rooms room) {
        Rooms existingRoom = roomsRepository.findById(id).orElse(null);
        if (existingRoom == null) {
            return null;
        }

        room.setId(id);
        return roomsRepository.save(room);
    }

    public void deleteRoom(Long id) {
        roomsRepository.deleteById(id);
    }
}
