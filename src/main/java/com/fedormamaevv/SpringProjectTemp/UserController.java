package com.fedormamaevv.SpringProjectTemp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) throws NotFoundException {
        Optional<User> data = userRepository.findById(id);
        if (data.isPresent())
            return data.get();
        throw new NotFoundException();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) throws ConflictException {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new ConflictException();
        return userRepository.save(new User(user.getUsername(), user.getPassword(), user.getAge()));
    }

    @DeleteMapping("/users/{id}")
    public HttpStatus deleteUser(@PathVariable Long id) throws NotFoundException {
        if (userRepository.findById(id).isEmpty())
            throw new NotFoundException();
        userRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user)
            throws NotFoundException, ConflictException {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new ConflictException();
        Optional<User> userData = userRepository.findById(id);
        if (userData.isEmpty()) throw new NotFoundException();
        User newUser = userData.get();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setAge(user.getAge());
        return userRepository.save(newUser);
    }
}
