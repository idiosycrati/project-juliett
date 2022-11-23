package com.juliett.core.TransactionsServiceImpl;

import java.util.Collection;

import com.juliett.core.Transactions.model.TransactionsModel;
import com.juliett.core.TransactionsRepository.TransactionsRepository;
import com.juliett.core.TransactionsRepositoryImpl.TransactionsRepositoryImpl;
import com.juliett.core.TransactionsService.TransactionsService;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.service.impl.AbstractServiceImpl;

public class TransactionsServiceImpl extends AbstractServiceImpl<TransactionsModel> implements TransactionsService {

	private final TransactionsRepository transactionsRepository;

	public TransactionsServiceImpl(DatabaseManager databaseManager) {
		super(new TransactionsRepositoryImpl(databaseManager));
		this.transactionsRepository = (TransactionsRepository) repository;
	}

	public Collection<TransactionsModel> findTransactionsById(Long id) {
		return this.transactionsRepository.findTransactionsById(id);
	}

	public Collection<TransactionsModel> findTransactionByApplicationId(Long applicationId) {

		return this.transactionsRepository.findTransactionByApplicationId(applicationId);
	}

	public void checkTermination() {
		this.transactionsRepository.checkTermination();
	}

	public void updateSumAssurance(Double multiplier) {
		this.transactionsRepository.updateSumAssurance(multiplier);
	}

	public void updateClaims(TransactionsModel transactionsModel) {
		this.transactionsRepository.updateClaims(transactionsModel);

	}

	public Collection<TransactionsModel> findTransactionsWithClaims() {
		return this.transactionsRepository.findTransactionsWithClaims();
	}
}
