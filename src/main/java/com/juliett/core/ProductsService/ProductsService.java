package com.juliett.core.ProductsService;

import java.util.Collection;

import com.juliett.core.Plans.model.PlansModel;
import com.juliett.core.Products.model.ProductsModel;
import com.xurpas.development.core.service.AbstractService;

public interface ProductsService extends AbstractService<ProductsModel> {
	public Collection<PlansModel> listPlan();

	public Collection<ProductsModel> findProductsById(Long id);

	public void btcSumAssurance(ProductsModel productsModel);

	public Collection<ProductsModel> findProductsByFinanceId(Long financeId, Long insuranceId);
}
