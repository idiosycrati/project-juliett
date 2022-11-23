package com.juliett.core.ApplicationFormServiceImpl;

import java.util.Collection;

import com.juliett.core.ApplicationForm.model.ApplicationFormModel;
import com.juliett.core.ApplicationFormRepository.ApplicationFormRepository;
import com.juliett.core.ApplicationFormRepositoryImpl.ApplicationFormRepositoryImpl;
import com.juliett.core.ApplicationFormService.ApplicationFormService;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.service.impl.AbstractServiceImpl;

public class ApplicationFormServiceImpl extends AbstractServiceImpl<ApplicationFormModel>
		implements ApplicationFormService {

	private final ApplicationFormRepository applicationFormRepository;

	public ApplicationFormServiceImpl(DatabaseManager databaseManager) {

		super(new ApplicationFormRepositoryImpl(databaseManager));
		this.applicationFormRepository = (ApplicationFormRepository) repository;
	}

	public Collection<ApplicationFormModel> findApplicationByUsersId(Long usersId) {

		return this.applicationFormRepository.findApplicationByUsersId(usersId);
	}

	public Collection<ApplicationFormModel> findApplicationById(Long id) {
		return this.applicationFormRepository.findApplicationById(id);
	}

}
