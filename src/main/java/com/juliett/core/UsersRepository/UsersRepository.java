package com.juliett.core.UsersRepository;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.juliett.core.Users.model.UsersModel;
import com.xurpas.development.core.repository.AbstractRepository;

public interface UsersRepository extends AbstractRepository<UsersModel> {

	UsersModel checkUser(UsersModel inputUser);

	void updateToken(UsersModel usersModel);

	Boolean emailIsAlreadyTaken(String input_email);

	Boolean findAccountByToken(String token );

}
