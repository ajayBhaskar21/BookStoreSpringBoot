package com.example.BookStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.BookStore.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
