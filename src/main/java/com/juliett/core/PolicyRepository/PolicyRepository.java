package com.juliett.core.PolicyRepository;

import java.util.Collection;

import com.juliett.core.Policy.model.PolicyModel;
import com.xurpas.development.core.repository.AbstractRepository;

public interface PolicyRepository extends AbstractRepository<PolicyModel> {
	public Collection<PolicyModel> findPolicyByPlansCategory(Long id);
}
