package airbnb.clone.api.controllerTests;

import airbnb.clone.api.controller.RoomsController;
import airbnb.clone.api.controller.UserController;
import airbnb.clone.api.entity.Users;
import airbnb.clone.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTests {

    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userController = new UserController(userService);
    }

    @Test
    public void testAddUser() {
        // Given
        Users user = new Users();
        user.setEmail("johndoe@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        when(userController.addUser(user)).thenReturn(new ResponseEntity<>(user, HttpStatus.CREATED));

        // When
        ResponseEntity<Users> response = userController.addUser(user);

        // Then
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(user.getEmail(), response.getBody().getEmail());
    }

    @Test
    public void testUpdateUser() {
        // Given
        Users user = new Users();
        user.setEmail("johndoe@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        when(userController.updateUser(user)).thenReturn(new ResponseEntity<>(user, HttpStatus.FOUND));

        // When

        ResponseEntity<Users> response = userController.updateUser(user);

        // Then
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(user.getEmail(), response.getBody().getEmail());
    }

    @Test
    public void testGetUserById() {
        // Given
        Long id = 1L;
        Users user = new Users();
        user.setId(id);
        user.setEmail("johndoe@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        when(userController.getUserById(1L)).thenReturn(new ResponseEntity<>(user, HttpStatus.FOUND));

        // When

        ResponseEntity<Users> response = userController.getUserById(id);

        // Then
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(id, response.getBody().getId());
    }

    @Test
    public void testGetUsers() {
        // Given
        List<Users> users = Arrays.asList(new Users(), new Users());
        when(userController.getAllUsers()).thenReturn(new ResponseEntity<>(users, HttpStatus.FOUND));

        // When
        ResponseEntity<List<Users>> response = userController.getAllUsers();

        // Then
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(users.size(), response.getBody().size());
    }



    @Test
    public void testAddUserWithExistingEmail() {
        // Given
        Users user = new Users();
        user.setEmail("johndoe@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        when(userController.addUser(user)).thenReturn(new ResponseEntity<>(user, HttpStatus.CONFLICT));

        // When
        ResponseEntity<Users> response = userController.addUser(user);

        // Then
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testUpdateUserWithNonExistentUser() {
        // Given
        Users user = new Users();
        user.setEmail("non-existent-user@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        when(userController.updateUser(user)).thenReturn(new ResponseEntity<>(user, HttpStatus.NOT_FOUND));

        // When

        ResponseEntity<Users> response = userController.updateUser(user);

        // Then
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
