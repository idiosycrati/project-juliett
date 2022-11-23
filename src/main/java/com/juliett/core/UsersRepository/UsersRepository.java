package com.juliett.core.UsersRepository;

import java.sql.SQLException;
import java.util.Collection;

import javax.naming.NamingException;

import com.juliett.core.Users.model.UsersModel;
import com.xurpas.development.core.repository.AbstractRepository;

public interface UsersRepository extends AbstractRepository<UsersModel> {

	UsersModel checkUser(UsersModel inputUser);

	void updateToken(UsersModel usersModel);

	Boolean emailIsAlreadyTaken(String input_email);

	Boolean foundAccount(String token);

	Boolean isAdmin(String token);

	Collection<UsersModel> findUserById(Integer id);

	void updateIsAdmin(UsersModel usersModel);

	void updateUsers(UsersModel usersModel);

	Collection<UsersModel> findAccountByToken(String token);
}
