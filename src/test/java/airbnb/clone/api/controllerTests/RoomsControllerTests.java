package airbnb.clone.api.controllerTests;

import airbnb.clone.api.controller.RoomsController;
import airbnb.clone.api.entity.Rooms;
import airbnb.clone.api.service.RoomsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomsControllerTests {

    @Mock
    private RoomsService roomsService;

    private RoomsController roomsController;

    @BeforeEach
    public void setUp() {
        roomsController = new RoomsController(roomsService);
    }

    @Test
    public void getAllRooms() {
        // Given
        Rooms room1 = new Rooms();
        room1.setId(1L);
        Rooms room2 = new Rooms();
        room2.setId(2L);

        when(roomsService.getAllRooms()).thenReturn(List.of(room1, room2));

        // When
        ResponseEntity<List<Rooms>> roomsResponse = roomsController.getAllRooms();

        // Then
        assertEquals(HttpStatus.OK, roomsResponse.getStatusCode());
        assertEquals(2, roomsResponse.getBody().size());
        assertEquals(1L, roomsResponse.getBody().get(0).getId());
        assertEquals(2L, roomsResponse.getBody().get(1).getId());
    }

    @Test
    public void getRoomById() {
        // Given
        Rooms room = new Rooms();
        room.setId(1L);

        when(roomsService.getRoomById(1L)).thenReturn(room);

        // When
        ResponseEntity<Rooms> roomResponse = roomsController.getRoomById(1L);

        // Then
        assertEquals(HttpStatus.OK, roomResponse.getStatusCode());
        assertEquals(room, roomResponse.getBody());
    }

    @Test
    public void createRoom() throws URISyntaxException {
        // Given
        Rooms room = new Rooms();

        when(roomsService.createRoom(room)).thenReturn(room);

        // When
        ResponseEntity<Rooms> roomResponse = roomsController.createRoom(room);

        // Then
        assertEquals(HttpStatus.CREATED, roomResponse.getStatusCode());
        assertEquals(room, roomResponse.getBody());
    }

    @Test
    public void updateRoom() {
        // Given
        Rooms room = new Rooms();
        room.setId(1L);

        when(roomsService.updateRoom(1L, room)).thenReturn(room);

        // When
        ResponseEntity<Rooms> roomResponse = roomsController.updateRoom(1L, room);

        // Then
        assertEquals(HttpStatus.OK, roomResponse.getStatusCode());
        assertEquals(room, roomResponse.getBody());
    }

}
