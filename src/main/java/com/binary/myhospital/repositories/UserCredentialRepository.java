package com.binary.myhospital.repositories;

import com.binary.myhospital.entities.UserCredential;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends ListCrudRepository<UserCredential,String>{
    Optional<UserCredential> findByUsername(String username);
}
