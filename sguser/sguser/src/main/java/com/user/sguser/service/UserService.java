package com.user.sguser.service;

import com.user.sguser.entity.UserEntity;
import com.user.sguser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Converts the request entity and saves it to the database.
    // With status by default "In Progress"
    public ResponseEntity<String> saveUserDetailsService(UserEntity userEntity){
        UserEntity user=new UserEntity();

        user.setSelectedType(userEntity.getSelectedType());
        user.setSelectedTypeId(userEntity.getSelectedTypeId());
        user.setCustomerName(userEntity.getCustomerName());
        user.setCustomerContact(userEntity.getCustomerContact());
        user.setStatus("In Progress");

        userRepository.save(user);
        //System.out.println(user.getStatus());
        return ResponseEntity.status(HttpStatus.CREATED).body("User Details uploaded successfully");
    }

    // Retrieves all users from the database.
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    //Retrieves a user by their unique ID.
    public UserEntity findByUserID(UUID customerId){
        return userRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
    }


    //Updates an existing user's information based on their ID and request body.
    public Optional<UserEntity> updateUser(UUID customerId, UserEntity userEntity){
        return userRepository.findById(customerId).map(existingUser ->{
            existingUser.setSelectedType(userEntity.getSelectedType());
            existingUser.setSelectedTypeId(userEntity.getSelectedTypeId());
            existingUser.setCustomerName(userEntity.getCustomerName());
            existingUser.setCustomerContact(userEntity.getCustomerContact());
            existingUser.setStatus(userEntity.getStatus());
            return userRepository.save(existingUser);
        });
    }


    //Deletes a user by their customerId.
    public String deleteUser(UUID customerId){
        if(!userRepository.existsById(customerId)){
            throw new RuntimeException("Customer Details not found with ID: " + customerId);
        }
        userRepository.deleteById(customerId);
        return "Customer "+ customerId+" Details are DELETED successfully";
    }

}
