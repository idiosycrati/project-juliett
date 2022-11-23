package com.juliett.core.TransactionsRepository;

import java.util.Collection;

import com.juliett.core.Transactions.model.TransactionsModel;
import com.xurpas.development.core.repository.AbstractRepository;

public interface TransactionsRepository extends AbstractRepository<TransactionsModel> {

	public Collection<TransactionsModel> findTransactionsById(Long id);

	public Collection<TransactionsModel> findTransactionByApplicationId(Long applicationId);

	public void checkTermination();

	public void updateSumAssurance(Double multiplier);
	public void updateClaims(TransactionsModel transactionsModel);
	public Collection<TransactionsModel> findTransactionsWithClaims() ;
}
