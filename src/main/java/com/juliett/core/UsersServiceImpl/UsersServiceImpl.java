package com.juliett.core.UsersServiceImpl;

import java.util.Collection;

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

	@Override
	public Boolean foundAccount(String token) {
		// TODO Auto-generated method stub
		return this.usersRepository.foundAccount(token);
	}

	public Boolean isAdmin(String token) {
		return this.usersRepository.isAdmin(token);
	}

	public Collection<UsersModel> findUserById(Integer id) {
		return this.usersRepository.findUserById(id);
	}

	public void updateIsAdmin(UsersModel usersModel) {
		this.usersRepository.updateIsAdmin(usersModel);
	}

	public void updateUsers(UsersModel usersModel) {

		this.usersRepository.updateUsers(usersModel);
	}

	public Collection<UsersModel> findAccountByToken(String token) {
		return this.usersRepository.findAccountByToken(token);
	}

}
