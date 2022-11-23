package com.juliett.core.ApplicationFormService;

import java.util.Collection;

import com.juliett.core.ApplicationForm.model.ApplicationFormModel;
import com.xurpas.development.core.service.AbstractService;

public interface ApplicationFormService extends AbstractService<ApplicationFormModel> {

	public Collection<ApplicationFormModel> findApplicationByUsersId(Long usersId);

	public Collection<ApplicationFormModel> findApplicationById(Long id);
}
