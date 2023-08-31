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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomsControllerTest {

    @Mock
    private RoomsService roomsService;

    private RoomsController roomsController;

    @BeforeEach
    public void setUp() {
        roomsController = new RoomsController(roomsService);
    }
    @Test
    public void testGetAllRoomsWhenCalledThenReturnAllRooms() {
        // Arrange
        Rooms room1 = new Rooms();
        Rooms room2 = new Rooms();
        List<Rooms> roomsList = Arrays.asList(room1, room2);
        when(roomsService.getAllRooms()).thenReturn(roomsList);

        // Act
        ResponseEntity<List<Rooms>> responseEntity = roomsController.getAllRooms();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(roomsList, responseEntity.getBody());
    }

    @Test
    public void testGetAllRoomsWhenNoRoomsThenReturnEmptyList() {
        // Arrange
        when(roomsService.getAllRooms()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Rooms>> responseEntity = roomsController.getAllRooms();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.emptyList(), responseEntity.getBody());
    }

    @Test
    public void testGetRoomByIdWhenIdIsValidThenReturnCorrectRoom() {
        // Arrange
        Rooms mockRoom = new Rooms();
        Long validId = 1L;
        when(roomsService.getRoomById(validId)).thenReturn(mockRoom);

        // Act
        ResponseEntity<Rooms> responseEntity = roomsController.getRoomById(validId);

        // Assert
        assertEquals(mockRoom, responseEntity.getBody());
    }

    @Test
    public void testGetRoomByIdWhenCalledThenRoomsServiceGetRoomByIdIsCalledWithCorrectId() {
        // Arrange
        Long validId = 1L;

        // Act
        roomsController.getRoomById(validId);

        // Assert
        verify(roomsService, times(1)).getRoomById(validId);
    }

    @Test
    public void testGetRoomByIdWhenIdIsValidThenReturnResponseEntityWithCorrectStatusCodeAndBody() {
        // Arrange
        Rooms mockRoom = new Rooms();
        Long validId = 1L;
        when(roomsService.getRoomById(validId)).thenReturn(mockRoom);

        // Act
        ResponseEntity<Rooms> responseEntity = roomsController.getRoomById(validId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockRoom, responseEntity.getBody());
    }

    @Test
    public void testDeleteRoomWhenIdIsValidThenReturnNoContent() {
        // Arrange
        Long validId = 1L;
        when(roomsService.deleteRoom(validId)).thenReturn(true);

        // Act
        ResponseEntity<Void> responseEntity = roomsController.deleteRoom(validId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteRoomWhenIdIsInvalidThenReturnNotFound() {
        // Arrange
        Long inValidId = -1L;
        when(roomsService.deleteRoom(inValidId)).thenReturn(false);

        // Act
        ResponseEntity<Void> responseEntity = roomsController.deleteRoom(inValidId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateRoomWhenRoomIsUpdatedThenReturnUpdatedRoom() {
        // Arrange
        Rooms room = new Rooms();
        room.setId(1L);
        when(roomsService.updateRoom(anyLong(), any(Rooms.class))).thenReturn(room);

        // Act
        ResponseEntity<Rooms> responseEntity = roomsController.updateRoom(1L, new Rooms());

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(room, responseEntity.getBody());
    }

    @Test
    public void testUpdateRoomWhenCalledThenCallUpdateRoomOnRoomsService() {
        // Arrange
        Rooms room = new Rooms();
        room.setId(1L);
        when(roomsService.updateRoom(anyLong(),any(Rooms.class))).thenReturn(room);

        // Act
        roomsController.updateRoom(1L, new Rooms());

        // Assert
        verify(roomsService, times(1)).updateRoom(anyLong(), any(Rooms.class));
    }

    @Test
    public void testCreateRoomWhenRoomIsPassedThenReturnsCreatedRoom() throws Exception {
        // Arrange
        Rooms room = new Rooms();
        when(roomsService.createRoom(room)).thenReturn(room);

        // Act
        ResponseEntity<Rooms> responseEntity = roomsController.createRoom(room);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(room, responseEntity.getBody());
    }

    @Test
    public void testCreateRoomWhenRoomIsPassedThenCallsRoomServiceCreateRoom() throws Exception {
        // Arrange
        Rooms room = new Rooms();

        // Act
        roomsController.createRoom(room);

        // Assert
        verify(roomsService, times(1)).createRoom(room);
    }
}