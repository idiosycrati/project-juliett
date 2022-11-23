package com.juliett.core.InsuranceTypeRepository;

import java.util.Collection;

import com.juliett.core.InsuranceType.model.InsuranceTypeModel;
import com.xurpas.development.core.repository.AbstractRepository;

public interface InsuranceTypeRepository extends AbstractRepository<InsuranceTypeModel> {

	public Collection<InsuranceTypeModel> findTypeById(Long typeId);
}
