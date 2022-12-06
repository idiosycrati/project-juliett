package com.juliett.core.AutoEmailRepository;

import java.util.Collection;

import com.juliett.core.AutoEmailModel.AutoEmailModel;
import com.xurpas.development.core.repository.AbstractRepository;

public interface AutoEmailRepository extends AbstractRepository<AutoEmailModel> {

	public Collection<AutoEmailModel> findByTransactionsId(Long transactionsId);
}
