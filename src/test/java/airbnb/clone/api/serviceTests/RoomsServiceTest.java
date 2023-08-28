package airbnb.clone.api.serviceTests;

import airbnb.clone.api.entity.Rooms;
import airbnb.clone.api.repository.RoomsRepo;
import airbnb.clone.api.service.RoomsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomsServiceTest {

    @Mock
    private RoomsRepo roomsRepository;

    private RoomsService roomsService;

    @BeforeEach
    public void setUp() {
        roomsService = new RoomsService(roomsRepository);
    }

    @Test
    public void getAllRooms() {
        // Given
        Rooms room1 = new Rooms();
        room1.setId(1L);
        Rooms room2 = new Rooms();
        room2.setId(2L);

        when(roomsRepository.findAll()).thenReturn(List.of(room1, room2));

        // When
        List<Rooms> rooms = roomsService.getAllRooms();

        // Then
        assertEquals(2, rooms.size());
        assertEquals(1L, rooms.get(0).getId());
        assertEquals(2L, rooms.get(1).getId());
    }

    @Test
    public void getRoomById() {
        // Given
        Rooms room = new Rooms();
        room.setId(1L);

        when(roomsRepository.findById(1L)).thenReturn(Optional.of(room));

        // When
        Rooms foundRoom = roomsService.getRoomById(1L);

        // Then
        assertEquals(room, foundRoom);
    }

    @Test
    public void createRoom() {
        // Given
        Rooms room = new Rooms();

        when(roomsRepository.save(room)).thenReturn(room);

        // When
        Rooms savedRoom = roomsService.createRoom(room);

        // Then
        assertEquals(room, savedRoom);
    }

    @Test
    public void updateRoom() {
        // Given
        Rooms room = new Rooms();
        room.setId(1L);

        when(roomsRepository.findById(1L)).thenReturn(Optional.of(room));
        when(roomsRepository.save(room)).thenReturn(room);

        // When
        Rooms updatedRoom = roomsService.updateRoom(1L, room);

        // Then
        assertEquals(room, updatedRoom);
    }

    @Test
    public void deleteRoom() {
        // Given
        Rooms room = new Rooms();
        room.setId(1L);

        // When
        roomsService.deleteRoom(1L);

        // Then
        verify(roomsRepository).deleteById(1L);
    }
}
