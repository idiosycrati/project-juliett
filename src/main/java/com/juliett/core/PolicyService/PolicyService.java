package com.juliett.core.PolicyService;

import java.util.Collection;

import com.juliett.core.Policy.model.PolicyModel;
import com.xurpas.development.core.service.AbstractService;

public interface PolicyService extends AbstractService<PolicyModel> {
	public Collection<PolicyModel> findPolicyByPlansCategory(Long id);
}
