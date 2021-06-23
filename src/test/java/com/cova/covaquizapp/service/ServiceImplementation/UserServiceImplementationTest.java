package com.cova.covaquizapp.service.ServiceImplementation;

import com.cova.covaquizapp.authpayload.request.SignupRequest;
import com.cova.covaquizapp.exception.ResourceNotFoundException;
import com.cova.covaquizapp.model.User;
import com.cova.covaquizapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplementationTest {

    private ModelMapper modelMapper;

    @Mock private PasswordEncoder passwordEncoder;

    @Mock private UserRepository userRepository;

    private UserServiceImplementation userServiceImplementationUnderTest;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        userServiceImplementationUnderTest = new UserServiceImplementation(userRepository,
                passwordEncoder);
    }

    @Test
    void canPreRegisterUser() {
        // given
        SignupRequest signupRequest = new SignupRequest("demarxes", "Adedotun", "Alausa",
                "Male", "adedotunalausa@gmail.com", "123456");

        // when
        User user = userServiceImplementationUnderTest.preRegisterUser(signupRequest);

        // then
        assertThat(user).isNotNull();

    }

    @Test
    void canRegisterUser() {
        // given
        User user = modelMapper.map(new SignupRequest("demarxes", "Adedotun", "Alausa",
                "Male", "adedotunalausa@gmail.com", "123456"), User.class);
        given(userRepository.save(user)).willReturn(user);

        // when
        userServiceImplementationUnderTest.registerUser(user);

        // then
        verify(userRepository).save(user);
    }

    @Test
    void canFindUserByEmail() {
        // given
        User user = modelMapper.map(new SignupRequest("demarxes", "Adedotun", "Alausa",
                "Male", "adedotunalausa@gmail.com", "123456"), User.class);
        given(userRepository.findByEmail("adedotunalausa@gmail.com"))
                .willReturn(java.util.Optional.ofNullable(user));

        // when
        userServiceImplementationUnderTest.findUserByEmail("adedotunalausa@gmail.com");

        // then
        verify(userRepository).findByEmail("adedotunalausa@gmail.com");
    }

    @Test
    void willThrowWhenUserIsNotFound() {
        // given
        modelMapper.map(new SignupRequest("demarxes", "Adedotun", "Alausa",
                "Male", "adedotunalausa@gmail.com", "123456"), User.class);

        // when
        // then
        assertThatThrownBy(() -> userServiceImplementationUnderTest
                .findUserByEmail("adedotun@gmail.com"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("User not found!");

    }
}