package airbnb.clone.api.controller;

import airbnb.clone.api.entity.Users;
import airbnb.clone.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller handles requests for users.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    /**
     * Adds a new user.
     *
     * @param users The user to add.
     * @return The response entity.
     */
    @PostMapping("/add")
    public ResponseEntity<Users> addUser(@RequestBody Users users) {
        // Create a response entity with the status code 201 (Created) and the added user.
        return userService.add(users);
    }

    /**
     * Updates an existing user.
     *
     * @param users The user to update.
     * @return The response entity.
     */
    @PatchMapping("/update")
    public ResponseEntity<Users> updateUser(@RequestBody Users users) {
        // Update the user and return the response entity with the status code 200 (OK).
        return userService.update(users);
    }

    /**
     * Gets a user by ID.
     *
     * @param id The ID of the user to get.
     * @return The response entity.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        // Get the user by ID and return the response entity with the status code 200 (OK) or 404 (Not Found).
        return userService.findById(id);
    }

    /**
     * Gets all users.
     *
     * @return The response entity.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers() {
        // Get all users and return the response entity with the status code 200 (OK).
        return userService.getAllUser();
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to delete.
     * @return The response entity.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        // Delete the user and return the response entity with the status code 204 (No Content).
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
