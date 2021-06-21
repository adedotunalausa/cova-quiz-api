package com.cova.covaquizapp.repository;

import com.cova.covaquizapp.enums.ERole;
import com.cova.covaquizapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName (ERole name);
}
