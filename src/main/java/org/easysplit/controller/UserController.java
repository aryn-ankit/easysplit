package org.easysplit.controller;

import org.easysplit.dto.UserInfo;
import org.easysplit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping ("/default")
    public UserInfo getDefaultUser() {
        return userService.getDefaultUser();
    }

    @PostMapping ("/register")
    public UserInfo registerUser(@RequestBody UserInfo userInfo) throws Exception {
       return userService.registerUser(userInfo);
    }

    @GetMapping ("/get")
    public UserInfo getUser(int userID) throws Exception {
        return userService.getUser(userID);
    }

    @GetMapping ("/get/all")
    public List<UserInfo> getAllUsers() throws Exception {
        return userService.getAllUsers();
    }

    @PostMapping ("/update")
    public UserInfo updateUser(UserInfo userInfo) throws Exception {
        return userService.updateUser(userInfo);
    }
}
