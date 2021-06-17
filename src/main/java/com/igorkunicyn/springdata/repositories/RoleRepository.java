package com.igorkunicyn.springdata.repositories;

import com.igorkunicyn.springdata.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
