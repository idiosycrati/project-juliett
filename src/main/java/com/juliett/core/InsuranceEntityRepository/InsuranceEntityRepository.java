package com.juliett.core.InsuranceEntityRepository;

import java.util.Collection;

import com.juliett.core.InsuranceEntity.model.InsuranceEntityModel;
import com.xurpas.development.core.repository.AbstractRepository;

public interface InsuranceEntityRepository extends AbstractRepository<InsuranceEntityModel> {

	Collection<InsuranceEntityModel> findItemById(Long id);
}
