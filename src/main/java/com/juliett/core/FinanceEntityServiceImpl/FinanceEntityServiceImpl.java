package com.juliett.core.FinanceEntityServiceImpl;

import java.util.Collection;

import com.juliett.core.FinanceEntity.model.FinanceEntityModel;
import com.juliett.core.FinanceEntityRepository.FinanceEntityRepository;
import com.juliett.core.FinanceEntityRepositoryImpl.FinanceEntityRepositoryImpl;
import com.juliett.core.FinanceEntityService.FinanceEntityService;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;
import com.xurpas.development.core.service.impl.AbstractServiceImpl;

public class FinanceEntityServiceImpl extends AbstractServiceImpl<FinanceEntityModel> implements FinanceEntityService {

	private final FinanceEntityRepository financeEntityRepository;

	public FinanceEntityServiceImpl(DatabaseManager databaseManager) {

		super(new FinanceEntityRepositoryImpl(databaseManager));
		this.financeEntityRepository = (FinanceEntityRepository) repository;
	}

	@Override
	public Collection<FinanceEntityModel> findFinanceById(Long id) {
		// TODO Auto-generated method stub
		return this.financeEntityRepository.findFinanceById(id);
	}

}
