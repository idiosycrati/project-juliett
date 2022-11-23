package com.juliett.core.InsuranceEntityServiceImpl;

import java.util.Collection;

import com.juliett.core.InsuranceEntity.model.InsuranceEntityModel;
import com.juliett.core.InsuranceEntityRepository.InsuranceEntityRepository;
import com.juliett.core.InsuranceEntityService.InsuranceEntityService;
import com.juliett.core.InsuranceEntityRepositoryImpl.InsuranceEntityRepositoryImpl;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.service.impl.AbstractServiceImpl;

public class InsuranceEntityServiceImpl extends AbstractServiceImpl<InsuranceEntityModel>
		implements InsuranceEntityService {

	private final InsuranceEntityRepository insuranceEntityRepository;

	public InsuranceEntityServiceImpl(DatabaseManager databaseManager) {

		super(new InsuranceEntityRepositoryImpl(databaseManager));

		this.insuranceEntityRepository = (InsuranceEntityRepository) repository;

	}

	@Override
	public Collection<InsuranceEntityModel> findItemById(Long id) {
		// TODO Auto-generated method stub
		return this.insuranceEntityRepository.findItemById(id);
	}
}
