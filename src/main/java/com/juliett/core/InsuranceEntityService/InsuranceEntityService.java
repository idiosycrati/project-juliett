package com.juliett.core.InsuranceEntityService;

import java.util.Collection;

import com.juliett.core.InsuranceEntity.model.InsuranceEntityModel;
import com.xurpas.development.core.service.AbstractService;

public interface InsuranceEntityService extends AbstractService<InsuranceEntityModel> {

	Collection<InsuranceEntityModel> findItemById(Long id);
}
