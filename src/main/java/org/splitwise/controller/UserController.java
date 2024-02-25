package org.splitwise.controller;

import org.splitwise.dto.UserInfo;
import org.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping ("default")
    public UserInfo getDefaultUser() {
        return userService.getDefaultUser();
    }

    @PostMapping ("register")
    public UserInfo registerUser(UserInfo userInfo) throws Exception {
       return userService.registerUser(userInfo);
    }

    @GetMapping ("get")
    public UserInfo getUser(String userName) throws Exception {
        return userService.getUser(userName);
    }


    @GetMapping ("get/all")
    public List<UserInfo> getAllUsers() throws Exception {
        return userService.getAllUsers();
    }

    @PostMapping ("update")
    public UserInfo updateUser(UserInfo userInfo) throws Exception {
        return userService.updateUser(userInfo);
    }
}
