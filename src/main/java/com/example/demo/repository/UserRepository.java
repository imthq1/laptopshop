package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    List<User> findAll();

    List<User> getAllUsersByEmail(String email);

    User findUserById(long id);

    User deleteById(long id);
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}