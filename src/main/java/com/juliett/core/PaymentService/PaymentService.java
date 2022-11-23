package com.juliett.core.PaymentService;

import java.util.Collection;

import com.juliett.core.Payment.model.PaymentModel;
import com.xurpas.development.core.repository.AbstractRepository;
import com.xurpas.development.core.service.AbstractService;

public interface PaymentService extends AbstractService<PaymentModel> {

	public Collection<PaymentModel> findPaymentByUsersId(Long usersId);

}
