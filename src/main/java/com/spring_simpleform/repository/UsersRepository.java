package com.spring_simpleform.repository;

import com.spring_simpleform.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersModel, Integer> {   // defaultCRUD methods

    Optional<UsersModel> findByLoginAndPassword( String login, String password);

    // To check if user with such login is Present
    Optional<UsersModel>  findFirstByLogin(String login);


}
