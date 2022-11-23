package com.juliett.core.InsuranceTypeServiceImpl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import com.juliett.core.InsuranceType.model.InsuranceTypeModel;
import com.juliett.core.InsuranceTypeRepository.InsuranceTypeRepository;
import com.juliett.core.InsuranceTypeRepositoryImpl.InsuranceTypeRepositoryImpl;
import com.juliett.core.InsuranceTypeService.InsuranceTypeService;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.logger.Logger;
import com.xurpas.development.core.service.impl.AbstractServiceImpl;

public class InsuranceTypeServiceImpl extends AbstractServiceImpl<InsuranceTypeModel> implements InsuranceTypeService {

	private final InsuranceTypeRepository insuranceTypeRepository;

	public InsuranceTypeServiceImpl(DatabaseManager databaseManager) {

		super(new InsuranceTypeRepositoryImpl(databaseManager));
		this.insuranceTypeRepository = (InsuranceTypeRepository) repository;
	}

	public Collection<InsuranceTypeModel> findTypeById(Long typeId) {
		return this.insuranceTypeRepository.findTypeById(typeId);
	}
}
