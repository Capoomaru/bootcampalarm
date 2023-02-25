package com.btca.bootcampalarm.repository;

import com.btca.bootcampalarm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByMail(String mail);
    Boolean existsByMail(String mail);

}