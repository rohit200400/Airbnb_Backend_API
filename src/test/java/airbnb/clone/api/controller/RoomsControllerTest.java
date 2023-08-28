package airbnb.clone.api.controller;

import airbnb.clone.api.entity.Rooms;
import airbnb.clone.api.service.RoomsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RoomsControllerTest {

    @InjectMocks
    private RoomsController roomsController;

    @Mock
    private RoomsService roomsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllRoomsWhenCalledThenReturnAllRooms() {
        Rooms room1 = new Rooms();
        Rooms room2 = new Rooms();
        List<Rooms> roomsList = Arrays.asList(room1, room2);

        when(roomsService.getAllRooms()).thenReturn(roomsList);

        ResponseEntity<List<Rooms>> response = roomsController.getAllRooms();

        assertEquals(roomsList, response.getBody());
        verify(roomsService, times(1)).getAllRooms();
    }

    @Test
    public void testGetRoomByIdWhenIdProvidedThenReturnRoom() {
        Rooms room = new Rooms();
        room.setId(1L);

        when(roomsService.getRoomById(1L)).thenReturn(room);

        ResponseEntity<Rooms> response = roomsController.getRoomById(1L);

        assertEquals(room, response.getBody());
        verify(roomsService, times(1)).getRoomById(1L);
    }

    @Test
    public void testCreateRoomWhenRoomProvidedThenReturnCreatedRoom() throws URISyntaxException {
        Rooms room = new Rooms();
        room.setId(1L);

        when(roomsService.createRoom(room)).thenReturn(room);

        ResponseEntity<Rooms> response = roomsController.createRoom(room);

        assertEquals(room, response.getBody());
        assertEquals(new URI("/rooms/" + room.getId()), response.getHeaders().getLocation());
        verify(roomsService, times(1)).createRoom(room);
    }

    @Test
    public void testUpdateRoomWhenIdAndRoomProvidedThenReturnUpdatedRoom() {
        Rooms room = new Rooms();
        room.setId(1L);

        when(roomsService.updateRoom(1L, room)).thenReturn(room);

        ResponseEntity<Rooms> response = roomsController.updateRoom(1L, room);

        assertEquals(room, response.getBody());
        verify(roomsService, times(1)).updateRoom(1L, room);
    }

    @Test
    public void testDeleteRoomWhenIdProvidedThenReturnNoContent() {
        doNothing().when(roomsService).deleteRoom(1L);

        ResponseEntity<Void> response = roomsController.deleteRoom(1L);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(roomsService, times(1)).deleteRoom(1L);
    }
}