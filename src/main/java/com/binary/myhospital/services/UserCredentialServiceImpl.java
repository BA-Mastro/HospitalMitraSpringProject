package com.binary.myhospital.services;

import com.binary.myhospital.dto.AuthorizationRequest;
import com.binary.myhospital.dto.NewUserCredentialRecord;
import com.binary.myhospital.dto.UserCredentialDto;
import com.binary.myhospital.entities.UserCredential;
import com.binary.myhospital.exceptions.InvalidRoleException;
import com.binary.myhospital.exceptions.UsernameTakenException;
import com.binary.myhospital.repositories.UserCredentialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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
    public UserCredentialDto createUser(NewUserCredentialRecord newUser) {
        log.info(newUser.toString());
        Optional<UserCredential> existUser = userCredentialRepo.findByUsername(newUser.username());
        if(existUser.isPresent()){
            throw new UsernameTakenException("Username has been taken, please choose another!");
        }
        if(newUser.role().equalsIgnoreCase("admin")){
            throw new InvalidRoleException("User cannot be created as ADMIN, has to be USER or DEPARTMENT");
        }
        if(newUser.role().equalsIgnoreCase("user") || newUser.role().equalsIgnoreCase("department")){
            UserCredential acceptedUser = new UserCredential(
                    newUser.username().toLowerCase(),
                    passwordEncoder.encode(newUser.password()),
                    newUser.role().toUpperCase()
            );
            log.info(acceptedUser.toString());
            acceptedUser = userCredentialRepo.save(acceptedUser);
            log.info(acceptedUser.toString());
            return new UserCredentialDto(acceptedUser.getUsername(),acceptedUser.getRole());
        }
        else{
            throw new InvalidRoleException("User cannot be created, Only USER or DEPARTMENT roles are accepted!");
        }
    }

    @Override
    public String login(AuthorizationRequest request) {
            log.info(request.password());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.username(),
                    request.password()
            ));
            return jwtService.generateToken(request.username());
    }
}
