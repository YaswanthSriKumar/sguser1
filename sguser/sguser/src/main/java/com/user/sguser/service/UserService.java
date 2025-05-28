package com.user.sguser.service;

import com.user.sguser.entity.UserEntity;
import com.user.sguser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> saveUserDetailsService(UserEntity userEntity){

        userRepository.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Details uploaded successfully");
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity findByUserID(UUID customerId){
        return userRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
    }

    public String deleteUser(UUID customerId){
        if(!userRepository.existsById(customerId)){
            throw new RuntimeException("Customer Details not found with ID: " + customerId);
        }
        userRepository.deleteById(customerId);
        return "Customer "+ customerId+" Details are DELETED successfully";
    }

}
