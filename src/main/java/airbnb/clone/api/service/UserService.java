package airbnb.clone.api.service;

import airbnb.clone.api.entity.Users;
import airbnb.clone.api.repository.UsersRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UsersRepo usersRepo;

    public UserService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    /**
     * Finds a user by email.
     *
     * @param email The email of the user to find.
     * @return A response entity with the status code 200 (Found) and the user if the user is found, or a response entity with the status code 404 (Not Found) if the user is not found.
     */
    public ResponseEntity<Users> findByEmail(String email) {
        Users user = usersRepo.findByEmail(email);
        ResponseEntity<Users> response;
        if (user != null) {
            response = new ResponseEntity<>(user, HttpStatus.FOUND);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Adds a new user.
     *
     * @param user The user to add.
     * @return A response entity with the status code 201 (Created) and the added user if the user was added successfully, or a response entity with the status code 409 (Conflict) if the user already exists.
     */
    public ResponseEntity<Users> add(Users user) {
        ResponseEntity<Users> existingUser = findByEmail(user.getEmail());
        if (existingUser.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new ResponseEntity<>(usersRepo.save(user), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Finds a user by ID.
     *
     * @param id The ID of the user to find.
     * @return A response entity with the status code 200 (Found) and the user if the user is found, or a response entity with the status code 404 (Not Found) if the user is not found.
     */
    public ResponseEntity<Users> findById(Long id) {
        ResponseEntity<Users> response;
        Optional<Users> user = usersRepo.findById(id);
        response = user.map(users -> new ResponseEntity<>(users, HttpStatus.FOUND))
                        .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        return response;
    }

    /**
     * Updates a user.
     *
     * @param user The user to update.
     * @return A response entity with the status code 200 (OK) if the user was updated successfully,
     * or a response entity with the status code 404 (Not Found) if the user was not found.
     */
    public ResponseEntity<Users> update(Users user) {
        ResponseEntity<Users> existingUser = findByEmail(user.getEmail());
        if (existingUser.getStatusCode() == HttpStatus.FOUND) {
            // Update the attributes
            return new ResponseEntity<>(usersRepo.save(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets all users.
     *
     * @return A response entity with the status code 200 (Found) and the list of all users.
     */
    public ResponseEntity<List<Users>> getAllUser() {
        List<Users> users = usersRepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to be deleted
     * @return A response entity with the status code 204 (No Content) if the user was deleted successfully,
     * or a response entity with the status code 404 (Not Found) if the user was not found.
     */
    public ResponseEntity<Void> deleteById(Long id) {
        ResponseEntity<Users> existingUser = findById(id);
        if (existingUser.getStatusCode() == HttpStatus.FOUND) {
            // Delete the user
            usersRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
