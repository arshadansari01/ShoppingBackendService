package com.shoppingkart.controller;

import com.shoppingkart.model.User;
import com.shoppingkart.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepository;

    UserController(UserRepository user) {
        userRepository = user;

    }

    @PostMapping("")
    public User createUser(@RequestBody User userPayload) {
        User savedUser = userRepository.save(userPayload);
        return savedUser;
    }

    @GetMapping("/getUserDetail")
    public User getUserDetails(@RequestParam(name = "userId") Integer userId) {
        User user = userRepository.findById(userId).get();
        return user;
    }


    @GetMapping("/userLogin")
    public Optional<User> handleUserLogin(@RequestParam(name = "username") String userNameValue,
                                          @RequestParam(name = "password") String password) {

        Optional<User> userFromDb = Optional.empty();
        try {
            userFromDb = userRepository.findByEmailIdAndPassword(userNameValue, password);
            if (!userFromDb.isPresent()) {
                userFromDb = userRepository.findByMobileNumberAndPassword(userNameValue, password);
            }
            return userFromDb;
        } catch (Exception e) {
            return userFromDb;
        }
    }


}



