package com.user.sguser.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.sguser.entity.UserEntity;
import com.user.sguser.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /*
    1. Creating new user with status as "In Progress" default in to DataBase
    2. This end point accept UserEntity payload in JSON of UserEntity without STATUS data
    */
    @PostMapping("/saveuser")
    public ResponseEntity<String> saveUserDetails(@RequestBody UserEntity userEntity)
    {
        System.out.println("received user details");
       return userService.saveUserDetailsService(userEntity);
    }
     /*
     1. This end point Retrieves a list of all users from the database.
      */
    @GetMapping("/getalluserdetails")
    public List<UserEntity> getAllUserDetails(){
        return userService.getAllUsers();
    }

    /*
    1. Retrieves a user by their ID.
    2. @param id the UUID of the user to retrieve
    3. @return the user details if found, or if not found throws error
     */
    @GetMapping("finduser/{customerId}")
    public UserEntity findUserById(@PathVariable UUID customerId){
     return userService.findByUserID(customerId);
    }
    /*
    1. Update user by UUID with new data from request body
    2. @PathVariable id the UUID of the user to update
    3. @RequestBody need for the remaining data to update
    4. the updated user if found and updated, or if not found throws error
     */
    @PutMapping("/updateuserdetails/{customerId}")
    public ResponseEntity<UserEntity> updateUserDetails(@PathVariable UUID customerId,@RequestBody UserEntity userEntity){
        return userService.updateUser(customerId,userEntity).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Delete the Customer details by ID
    // If Customer ID not found returns error
    @DeleteMapping("/deletebyid/{customerId}")
    public String deleteUserById(@PathVariable List<UUID> customerId){
        return userService.deleteUser(customerId);
    }



}
