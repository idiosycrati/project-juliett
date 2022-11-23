package com.juliett.core.UsersService;

import java.util.Collection;

import com.juliett.core.Users.model.UsersModel;
import com.xurpas.development.core.service.AbstractService;

public interface UsersService extends AbstractService<UsersModel> {

	UsersModel checkUser(UsersModel inputUser);

	void updateToken(UsersModel usersModel);

	Boolean isEmailAlreadyTaken(String input_email);

	Boolean foundAccount(String token);

	Boolean isAdmin(String token);

	Collection<UsersModel> findUserById(Integer id);

	void updateIsAdmin(UsersModel usersModel);

	void updateUsers(UsersModel usersModel);

	Collection<UsersModel> findAccountByToken(String token);
}
