package com.postItApi.postIt.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    //analogous to user router in the OG project
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping()
    public List<User> getUsers(){
        // return "Hello!";
        return userService.getAllUsers();
        
    }

    @PostMapping()
    public List<User> createUser(@RequestBody User user){
        userService.createUser(user);
        return userService.getAllUsers();
    }
}
