package com.works.repositories;

import com.works.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {


    Optional<Admin> findByEmailEqualsAndUsernameEquals(String email, String username);

    Optional<Admin> findByUsernameEquals(String username);


}