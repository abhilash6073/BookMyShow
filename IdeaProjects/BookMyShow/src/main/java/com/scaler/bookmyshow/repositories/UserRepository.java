package com.scaler.bookmyshow.repositories;


import com.scaler.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//we will be using jpa interface to do CRUD operations over DB
//we need to follow some principles while doing so
//our repository should be an interface and not a class
//interface should extend JpaRepository

@Repository //this would enable spring/jpa to create sample userRepository objects for us
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long userId); //this is equivalent to select * from users where id = userId;
}
