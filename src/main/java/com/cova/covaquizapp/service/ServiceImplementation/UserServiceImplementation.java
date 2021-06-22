package com.cova.covaquizapp.service.ServiceImplementation;

import com.cova.covaquizapp.authpayload.request.SignupRequest;
import com.cova.covaquizapp.exception.ResourceNotFoundException;
import com.cova.covaquizapp.model.User;
import com.cova.covaquizapp.repository.UserRepository;
import com.cova.covaquizapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Override
    public User registration(SignupRequest signupRequest) {

        return new User(
                signupRequest.getUsername(),
                signupRequest.getFirstname(),
                signupRequest.getLastname(),
                signupRequest.getGender(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword())
        );
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("User not found!");
                }
        );
    }

}
