package com.binary.myhospital.services;

import com.binary.myhospital.dto.AuthorizationRequest;
import com.binary.myhospital.dto.NewUserCredentialRecord;
import com.binary.myhospital.dto.UserCredentialDto;
import com.binary.myhospital.repositories.UserCredentialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialServiceImpl implements UserCredentialService{

    private static final Logger log = LoggerFactory.getLogger(UserCredentialServiceImpl.class);
    private final UserCredentialRepository userCredentialRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserCredentialServiceImpl(UserCredentialRepository userCredentialRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userCredentialRepo = userCredentialRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserCredentialDto createUser(NewUserCredentialRecord user) {
        return null;
    }

    @Override
    public String login(AuthorizationRequest request) {
        return "";
    }
}
