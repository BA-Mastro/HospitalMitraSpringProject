package com.binary.myhospital.services;

import com.binary.myhospital.dto.AuthorizationRequest;
import com.binary.myhospital.dto.NewUserCredentialRecord;
import com.binary.myhospital.dto.UserCredentialDto;

public interface UserCredentialService {

    UserCredentialDto createUser(NewUserCredentialRecord user);
    String login(AuthorizationRequest request);

}
