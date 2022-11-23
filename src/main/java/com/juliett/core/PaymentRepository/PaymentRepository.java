package com.juliett.core.PaymentRepository;

import java.util.Collection;

import com.juliett.core.Payment.model.PaymentModel;
import com.xurpas.development.core.repository.AbstractRepository;

public interface PaymentRepository extends AbstractRepository<PaymentModel> {

	public Collection<PaymentModel> findPaymentByUsersId(Long usersId);

}
