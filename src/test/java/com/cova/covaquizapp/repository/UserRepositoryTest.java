package com.cova.covaquizapp.repository;

import com.cova.covaquizapp.authpayload.request.SignupRequest;
import com.cova.covaquizapp.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepositoryUnderTest;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        // given
        User user = modelMapper.map(new SignupRequest("demarxes", "Adedotun", "Alausa",
                "Male", "adedotunalausa@gmail.com", "123456"), User.class);
        userRepositoryUnderTest.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepositoryUnderTest.deleteAll();
    }

    @Test
    void shouldFindUserByEmail() {
        // when
        Optional<User> user = userRepositoryUnderTest.findByEmail("adedotunalausa@gmail.com");

        // then
        assertTrue(user.isPresent());
    }

    @Test
    void shouldCheckIfUserExistsByEmail() {
        // when
        Boolean userExist = userRepositoryUnderTest.existsByEmail("adedotunalausa@gmail.com");

        // then
        assertTrue(userExist);
    }

    @Test
    void existsByUsername() {
        // when
        Boolean userExist = userRepositoryUnderTest.existsByUsername("demarxes");

        // then
        assertTrue(userExist);
    }
}