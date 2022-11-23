package com.juliett.core.FinanceEntityService;

import java.util.Collection;

import com.juliett.core.FinanceEntity.model.FinanceEntityModel;
import com.xurpas.development.core.service.AbstractService;

public interface FinanceEntityService extends AbstractService<FinanceEntityModel> {
	Collection<FinanceEntityModel> findFinanceById(Long id);
}
