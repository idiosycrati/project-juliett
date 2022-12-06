package com.juliett.core.AutoEmailService;

import java.util.Collection;

import com.juliett.core.AutoEmailModel.AutoEmailModel;
import com.xurpas.development.core.service.AbstractService;

public interface AutoEmailService extends AbstractService<AutoEmailModel> {

	public Collection<AutoEmailModel> findByTransactionsId(Long transactionsId);
}
