package com.juliett.core.InsuranceTypeService;

import java.util.Collection;

import com.juliett.core.InsuranceType.model.InsuranceTypeModel;
import com.xurpas.development.core.service.AbstractService;

public interface InsuranceTypeService extends AbstractService<InsuranceTypeModel> {
	public Collection<InsuranceTypeModel> findTypeById(Long typeId);
}
