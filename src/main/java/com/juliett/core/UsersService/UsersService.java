package com.juliett.core.UsersService;

import com.juliett.core.Users.model.UsersModel;
import com.xurpas.development.core.service.AbstractService;

public interface UsersService extends AbstractService<UsersModel> {

	UsersModel checkUser(UsersModel inputUser);

	void updateToken(UsersModel usersModel);

	Boolean isEmailAlreadyTaken(String input_email);

	Boolean findUserByToken(String token);
}
