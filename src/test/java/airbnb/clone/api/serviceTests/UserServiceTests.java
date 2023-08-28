package airbnb.clone.api.serviceTests;

import airbnb.clone.api.entity.Users;
import airbnb.clone.api.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void testFindByEmail() {
        // Given
        String email = "johndoe@gmail.com";

        // When
        Users user = userService.findByEmail(email).getBody();

        // Then
        Assertions.assertNotNull(user);
        Assertions.assertEquals(email, user.getEmail());
    }

    @Test
    public void testAdd() {
        // Given
        Users user = new Users();
        user.setEmail("johndoe@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");

        // When
        ResponseEntity<Users> response = userService.add(user);

        // Then
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(user.getEmail(), response.getBody().getEmail());
    }

    @Test
    public void testUpdate() {
        // Given
        Users user = new Users();
        user.setEmail("johndoe@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");

        // When
        ResponseEntity<Users> response = userService.update(user);

        // Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(user.getEmail(), response.getBody().getEmail());
    }

//    @Test
//    public void testDeleteById() {
//        // Given
//        Long id = 1l;
//
//        // When
//        when(userService.deleteById(1L)).thenReturn(void);
//        ResponseEntity<Void> response = userService.deleteById(id);
//
//        // Then
//        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//    }

    @Test
    public void testFindByEmailWithNonExistentEmail() {
        // Given
        String email = "non-existent-email@gmail.com";

        // When
        Users user = userService.findByEmail(email).getBody();

        // Then
        Assertions.assertNull(user);
    }

    @Test
    public void testAddWithExistingEmail() {
        // Given
        Users user = new Users();
        user.setEmail("johndoe@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");

        // When
        ResponseEntity<Users> response = userService.add(user);

        // Then
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testUpdateWithNonExistentUser() {
        // Given
        Users user = new Users();
        user.setEmail("non-existent-user@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");

        // When
        ResponseEntity<Users> response = userService.update(user);

        // Then
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
