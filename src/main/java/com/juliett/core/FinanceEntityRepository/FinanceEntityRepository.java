package com.juliett.core.FinanceEntityRepository;

import java.util.Collection;

import com.juliett.core.FinanceEntity.model.FinanceEntityModel;
import com.xurpas.development.core.repository.AbstractRepository;

public interface FinanceEntityRepository extends AbstractRepository<FinanceEntityModel> {

	Collection<FinanceEntityModel> findFinanceById(Long id);
}
