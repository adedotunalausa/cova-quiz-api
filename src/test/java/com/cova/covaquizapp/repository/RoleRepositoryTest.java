package com.cova.covaquizapp.repository;

import com.cova.covaquizapp.dto.RoleDTO;
import com.cova.covaquizapp.enums.ERole;
import com.cova.covaquizapp.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        // given
        Role role = modelMapper.map(new RoleDTO(ERole.ROLE_USER), Role.class);
        roleRepository.save(role);
    }

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
    }

    @Test
    void shouldFindRoleByName() {
        // when
        Optional<Role> role = roleRepository.findByName(ERole.ROLE_USER);

        assert(role.isPresent());
    }
}