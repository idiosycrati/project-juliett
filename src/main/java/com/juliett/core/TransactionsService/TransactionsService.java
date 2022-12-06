package com.juliett.core.TransactionsService;

import java.util.Collection;

import com.juliett.core.Transactions.model.TransactionsModel;
import com.xurpas.development.core.service.AbstractService;

public interface TransactionsService extends AbstractService<TransactionsModel> {

	public Collection<TransactionsModel> findTransactionsById(Long id);

	public Collection<TransactionsModel> findTransactionByApplicationId(Long applicationId);

	public void checkTermination();

	public void updateSumAssurance(Double multiplier);

	public void updateClaims(TransactionsModel transactionsModel);

	public Collection<TransactionsModel> findTransactionsWithClaims();

	public Collection<TransactionsModel> getJsonPolicy();

	public void updateClaimAssurance(Double multiplier);

	public Collection<TransactionsModel> findTransactionsTerminated();

	public Collection<TransactionsModel> getUsersName();
	
	public Collection<TransactionsModel> getUsersInfo(Long id);

}
