package com.juliett.core.ApplicationFormRepository;

import java.util.Collection;

import com.juliett.core.ApplicationForm.model.ApplicationFormModel;
import com.xurpas.development.core.repository.AbstractRepository;

public interface ApplicationFormRepository extends AbstractRepository<ApplicationFormModel> {

	public Collection<ApplicationFormModel> findApplicationByUsersId(Long usersId);
	
	public Collection<ApplicationFormModel> findApplicationById(Long id);

}
