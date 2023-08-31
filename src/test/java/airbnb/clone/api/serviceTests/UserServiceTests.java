package airbnb.clone.api.serviceTests;

import airbnb.clone.api.entity.Users;
import airbnb.clone.api.repository.UsersRepo;
import airbnb.clone.api.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UsersRepo usersRepository;

    @BeforeEach
    public void setUp() {
        userService = new UserService(usersRepository);
    }

    @Test
    public void testFindByEmail() {
        // Given
        String email = "johndoe@gmail.com";
        Users user = new Users();
        user.setId(1L);
        user.setEmail("johndoe@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        when(usersRepository.findByEmail(email)).thenReturn(user);


        // When
        Users newUser = userService.findByEmail(email).getBody();

        // Then
        Assertions.assertNotNull(newUser);
        Assertions.assertEquals(email, newUser.getEmail());
    }

    @Test
    public void testAdd() {
        // Given
        Users user = new Users();
        user.setEmail("johndoe@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        when(usersRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(usersRepository.save(user)).thenReturn(user);

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

        Users updatedUser = new Users();
        updatedUser.setEmail("johndoe@gmail.com");
        updatedUser.setFirstName("John");
        updatedUser.setLastName("Chena");
        when(usersRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(usersRepository.save(updatedUser)).thenReturn(updatedUser);

        // When
        ResponseEntity<Users> response = userService.update(updatedUser);

        // Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotEquals(user.getLastName(),response.getBody().getLastName() );
        Assertions.assertEquals(user.getEmail(), response.getBody().getEmail());
    }


    @Test
    public void testFindByEmailWithNonExistentEmail() {
        // Given
        String email = "non-existent-email@gmail.com";
        when(usersRepository.findByEmail(email)).thenReturn(null);
        // When
        ResponseEntity<Users> response = userService.findByEmail(email);

        // Then
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testAddWithExistingEmail() {
        // Given
        Users user = new Users();
        user.setEmail("johndoe@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        when(usersRepository.findByEmail(user.getEmail())).thenReturn(user);

        // When
        ResponseEntity<Users> response = userService.add(user);

        // Then
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testUpdateWithNonExistentUser() {
        // Given
        Users user = new Users();
        user.setEmail("non-existent-user@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        when(usersRepository.findByEmail(user.getEmail())).thenReturn(null);

        // When
        ResponseEntity<Users> response = userService.update(user);

        // Then
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
