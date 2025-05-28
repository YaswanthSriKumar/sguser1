package com.user.sguser.controller;

import com.user.sguser.entity.UserEntity;
import com.user.sguser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // saving the new User detils
    @PostMapping("/saveuser")
    public ResponseEntity<String> saveUserDetails(@RequestBody UserEntity userEntity)
    {
        System.out.println("received user details");
       return userService.saveUserDetailsService(userEntity);
    }
     // Get the all user details as list
    @GetMapping("/getalluserdetails")
    public List<UserEntity> getAllUserDetails(){
        return userService.getAllUsers();
    }

    @GetMapping("finduser/{customerId}")
    public UserEntity findUserById(@PathVariable UUID customerId){
     return userService.findByUserID(customerId);
    }

    @DeleteMapping("/deletebyid/{customerId}")
    public String deleteUserById(@PathVariable UUID customerId){
        return userService.deleteUser(customerId);
    }



}
