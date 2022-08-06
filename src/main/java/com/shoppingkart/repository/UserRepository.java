package com.shoppingkart.repository;

import com.shoppingkart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User,Integer>      {


    User save(User entity);


    Optional<User> findByEmailIdAndPassword(String emailId, String password);

    Optional<User> findByMobileNumberAndPassword(String userNameValue, String password);
}

