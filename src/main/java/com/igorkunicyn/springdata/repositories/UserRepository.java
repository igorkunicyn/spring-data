package com.igorkunicyn.springdata.repositories;

import com.igorkunicyn.springdata.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAll();
    User findByUsername(String username);
    User findById(Long id);
    void deleteById(Long id);

//    User save(User user);
}
