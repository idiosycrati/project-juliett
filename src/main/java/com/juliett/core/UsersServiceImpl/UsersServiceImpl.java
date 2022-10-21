package com.juliett.core.UsersServiceImpl;

import com.juliett.core.Users.model.UsersModel;
import com.juliett.core.UsersRepository.UsersRepository;
import com.juliett.core.UsersRepositoryImpl.UsersRepositoryImpl;
import com.juliett.core.UsersService.UsersService;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.service.impl.AbstractServiceImpl;

public class UsersServiceImpl extends AbstractServiceImpl<UsersModel> implements UsersService {

	private final UsersRepository usersRepository;

	public UsersServiceImpl(DatabaseManager databaseManager) {

		super(new UsersRepositoryImpl(databaseManager));
		this.usersRepository = (UsersRepository) repository;

	}

	@Override
	public UsersModel checkUser(UsersModel inputUser) {
		// TODO Auto-generated method stub
		return this.usersRepository.checkUser(inputUser);

	}

	@Override
	public void updateToken(UsersModel usersModel) {
		// TODO Auto-generated method stub
		this.usersRepository.updateToken(usersModel);
	}

	@Override
	public Boolean isEmailAlreadyTaken(String input_email) {
		// TODO Auto-generated method stub
		return this.usersRepository.emailIsAlreadyTaken(input_email);
	}

}
