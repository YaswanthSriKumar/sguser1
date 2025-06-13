package com.user.sguser.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.sguser.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {


}
