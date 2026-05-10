package com.client.busticket.auth_service.repository;

import com.client.busticket.auth_service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <Users, Long> {
    Optional<Users> findByEmail(String email);   //Here email is unique so ,...

    boolean existsByEmail(String email);
}
