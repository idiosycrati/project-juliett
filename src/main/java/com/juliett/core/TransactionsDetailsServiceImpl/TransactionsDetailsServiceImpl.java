package com.juliett.core.TransactionsDetailsServiceImpl;

import com.juliett.core.TransactionsDetailsModel.TransactionsDetailsModel;
import com.juliett.core.TransactionsDetailsRepository.TransactionsDetailsRepository;
import com.juliett.core.TransactionsDetailsRepositoryImpl.TransactionsDetailsRepositoryImpl;
import com.juliett.core.TransactionsDetailsService.TransactionsDetailsService;
import com.juliett.core.TransactionsRepositoryImpl.TransactionsRepositoryImpl;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.service.impl.AbstractServiceImpl;

public class TransactionsDetailsServiceImpl extends AbstractServiceImpl<TransactionsDetailsModel>
		implements TransactionsDetailsService {

	private final TransactionsDetailsRepository transactionsDetailsRepository;

	public TransactionsDetailsServiceImpl(DatabaseManager databaseManager) {
		super(new TransactionsDetailsRepositoryImpl(databaseManager));
		this.transactionsDetailsRepository = (TransactionsDetailsRepository) repository;
	}
}
