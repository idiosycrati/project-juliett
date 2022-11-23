package com.juliett.core.PolicyServiceImpl;

import java.util.Collection;

import com.juliett.core.Policy.model.PolicyModel;
import com.juliett.core.PolicyRepository.PolicyRepository;
import com.juliett.core.PolicyRepositoryImpl.PolicyRepositoryImpl;
import com.juliett.core.PolicyService.PolicyService;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.service.impl.AbstractServiceImpl;

public class PolicyServiceImpl extends AbstractServiceImpl<PolicyModel> implements PolicyService {

	private final PolicyRepository policyRepository;

	public PolicyServiceImpl(DatabaseManager databaseManager) {
		super(new PolicyRepositoryImpl(databaseManager));
		this.policyRepository = (PolicyRepository) repository;

	}

	public Collection<PolicyModel> findPolicyByPlansCategory(Long id) {

		return this.policyRepository.findPolicyByPlansCategory(id);
	}

}
