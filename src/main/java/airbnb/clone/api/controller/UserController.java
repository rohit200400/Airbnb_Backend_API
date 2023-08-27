package airbnb.clone.api.controller;

import airbnb.clone.api.entity.Users;
import airbnb.clone.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    @PostMapping("/add")
    public ResponseEntity<Users> add(@RequestBody Users users){
        return userService.add(users);
    }
    @PatchMapping("/update")
    public ResponseEntity<Users> update(@RequestBody Users users){
        return userService.update(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUser(){
        return userService.getAllUser();
    }

}
