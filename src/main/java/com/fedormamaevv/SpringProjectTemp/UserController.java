package com.fedormamaevv.SpringProjectTemp;

import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<User> getAllUsers(@RequestParam(required = false) String age,
                                  @RequestParam(required = false) String sortBy,
                                  @RequestParam(required = false) String direction)
            throws BadRequestException {

        Sort.Direction sortDirection = null;
        if (direction != null && sortBy != null) {
            if (direction.compareTo("up") == 0) sortDirection = Sort.Direction.ASC;
            if (direction.compareTo("down") == 0) sortDirection = Sort.Direction.DESC;
        }

        if (age == null) {
            if (sortDirection != null) return userRepository.findAll(Sort.by(sortDirection, sortBy));
            else return userRepository.findAll();
        }

        long _age;
        try {
            _age = Integer.parseInt(age);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new BadRequestException();
        }

        if (sortDirection != null) return userRepository.findByAgeBetween(_age - 5, _age + 5, Sort.by(sortDirection, sortBy));
        else return userRepository.findByAgeBetween(_age - 5, _age + 5);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) throws NotFoundException {
        Optional<User> data = userRepository.findById(id);
        if (data.isPresent())
            return data.get();
        throw new NotFoundException();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user, @RequestParam String repeatPassword)
            throws ConflictException, BadRequestException {
        if (repeatPassword.compareTo(user.getPassword()) != 0)
            throw new BadRequestException();
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
    public User updateUser(@PathVariable Long id,
                           @RequestBody User user,
                           @RequestParam String repeatPassword)
            throws NotFoundException, ConflictException, BadRequestException {
        if (repeatPassword.compareTo(user.getPassword()) != 0)
            throw new BadRequestException();
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
