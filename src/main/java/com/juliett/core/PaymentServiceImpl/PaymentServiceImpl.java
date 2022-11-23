package com.juliett.core.PaymentServiceImpl;

import java.util.Collection;

import com.juliett.core.Payment.model.PaymentModel;
import com.juliett.core.PaymentRepository.PaymentRepository;
import com.juliett.core.PaymentRepositoryImpl.PaymentRepositoryImpl;
import com.juliett.core.PaymentService.PaymentService;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.service.impl.AbstractServiceImpl;

public class PaymentServiceImpl extends AbstractServiceImpl<PaymentModel> implements PaymentService {

	private final PaymentRepository paymentRepository;

	public PaymentServiceImpl(DatabaseManager databaseManager) {
		super(new PaymentRepositoryImpl(databaseManager));
		this.paymentRepository = (PaymentRepository) repository;
	}

	public Collection<PaymentModel> findPaymentByUsersId(Long usersId) {

		return this.paymentRepository.findPaymentByUsersId(usersId);
	}
}
