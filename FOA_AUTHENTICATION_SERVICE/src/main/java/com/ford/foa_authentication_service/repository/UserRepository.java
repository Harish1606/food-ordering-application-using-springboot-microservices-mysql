package com.ford.foa_authentication_service.repository;

import com.ford.foa_authentication_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String username);

    User findByEmailId(String emailId);
}
