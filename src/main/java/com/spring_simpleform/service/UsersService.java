package com.spring_simpleform.service;

import com.spring_simpleform.model.UsersModel;
import com.spring_simpleform.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

   @Autowired
   private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public UsersModel registerUser( String login, String password, String email) {
        if (login == null || password == null) {
            return null;
        } else {
            if( usersRepository.findFirstByLogin( login ).isPresent()  ){
                System.out.println("Duplicated Login(username )");
                return null;
            }
           UsersModel usersModel = new UsersModel();
           usersModel.setLogin( login );
           usersModel.setPassword( password );
           usersModel.setEmail( email );
          return usersRepository.save( usersModel );
        }
    }
    // Authentication method
    public UsersModel authenticate( String login, String password ){
        return usersRepository.findByLoginAndPassword( login , password ).orElse(null);
    }
}
