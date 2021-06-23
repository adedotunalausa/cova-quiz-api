package com.cova.covaquizapp.service;

import com.cova.covaquizapp.authpayload.request.SignupRequest;
import com.cova.covaquizapp.model.User;

public interface UserService {
    User preRegisterUser(SignupRequest signupRequest);
    void registerUser(User user);
    User findUserByEmail(String email);
}
