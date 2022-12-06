package com.juliett.core.AutoEmailServiceImpl;

import java.util.Collection;

import com.juliett.core.AutoEmailModel.AutoEmailModel;
import com.juliett.core.AutoEmailRepository.AutoEmailRepository;
import com.juliett.core.AutoEmailRepositoryImpl.AutoEmailRepositoryImpl;
import com.juliett.core.AutoEmailService.AutoEmailService;
import com.juliett.core.TransactionsRepository.TransactionsRepository;
import com.juliett.core.TransactionsRepositoryImpl.TransactionsRepositoryImpl;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.service.impl.AbstractServiceImpl;

public class AutoEmailServiceImpl extends AbstractServiceImpl<AutoEmailModel> implements AutoEmailService {
	private final AutoEmailRepository autoEmailRepository;

	public AutoEmailServiceImpl(DatabaseManager databaseManager) {
		super(new AutoEmailRepositoryImpl(databaseManager));
		this.autoEmailRepository = (AutoEmailRepository) repository;
	}

	public Collection<AutoEmailModel> findByTransactionsId(Long transactionsId) {
		return this.autoEmailRepository.findByTransactionsId(transactionsId);
	}

}
