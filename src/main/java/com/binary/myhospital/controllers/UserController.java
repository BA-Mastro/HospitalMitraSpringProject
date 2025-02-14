package com.binary.myhospital.controllers;

import com.binary.myhospital.dto.AuthorizationRequest;
import com.binary.myhospital.dto.NewUserCredentialRecord;
import com.binary.myhospital.dto.UserCredentialDto;
import com.binary.myhospital.services.UserCredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserCredentialService userCredentialService;

    public UserController(UserCredentialService userCredentialService){
        this.userCredentialService = userCredentialService;
    }

    @PostMapping("/")
    public ResponseEntity<UserCredentialDto> postNewUser(@RequestBody NewUserCredentialRecord postNewUser){
        return new ResponseEntity<>(userCredentialService.createUser(postNewUser), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String > login(@RequestBody AuthorizationRequest request){
        return new ResponseEntity<>(userCredentialService.login(request), HttpStatus.OK);
    }


}

