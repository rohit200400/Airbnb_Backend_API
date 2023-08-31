package airbnb.clone.api.controller;


import airbnb.clone.api.entity.Rooms;
import airbnb.clone.api.service.RoomsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomsController {

    private final RoomsService roomsService;

    public RoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @GetMapping
    public ResponseEntity<List<Rooms>> getAllRooms() {
        List<Rooms> rooms = roomsService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rooms> getRoomById(@PathVariable Long id) {
        Rooms room = roomsService.getRoomById(id);
        return ResponseEntity.ok(room);
    }

    @PostMapping("/createRoom")
    public ResponseEntity<Rooms> createRoom(@RequestBody Rooms room) throws URISyntaxException {
        Rooms newRoom = roomsService.createRoom(room);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rooms> updateRoom(@PathVariable Long id, @RequestBody Rooms room) {
        Rooms updatedRoom = roomsService.updateRoom(id, room);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        if(roomsService.deleteRoom(id)){
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}

