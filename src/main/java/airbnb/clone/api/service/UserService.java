package airbnb.clone.api.service;

import airbnb.clone.api.entity.Users;
import airbnb.clone.api.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<Users> findByEmail(String email){
        Users user = userRepo.findByEmail(email);
        ResponseEntity<Users> response;
        if(user != null) {
            response = new ResponseEntity<>(user, HttpStatus.FOUND);
        }
        else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    public ResponseEntity<Users> add(Users user) {
        ResponseEntity<Users> existingUser = findByEmail(user.getEmail());
        if(existingUser.getStatusCode() == HttpStatus.NOT_FOUND){
            return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    public ResponseEntity<Users> findById(Long id){
        ResponseEntity<Users> response;
        Optional<Users> user = userRepo.findById(id);

        if(!user.isEmpty()) {
            response = new ResponseEntity<>(user.get(), HttpStatus.FOUND);
        }
        else{
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    public ResponseEntity<Users> update(Users user) {
        ResponseEntity<Users> existingUser = findByEmail(user.getEmail());
        if(existingUser.getStatusCode() == HttpStatus.FOUND){
            // Update the attributes
            return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Users>> getAllUser(){
        List<Users> users = userRepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

}
