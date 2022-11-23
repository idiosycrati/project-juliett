package com.juliett.core.ProductsRepository;

import java.util.Collection;

import com.juliett.core.Plans.model.PlansModel;
import com.juliett.core.Products.model.ProductsModel;
import com.xurpas.development.core.repository.AbstractRepository;

public interface ProductsRepository extends AbstractRepository<ProductsModel> {
	public Collection<PlansModel> listPlan();

	public Collection<ProductsModel> findProductsById(Long id);

	public void btcSumAssurance(ProductsModel productsModel);

	public Collection<ProductsModel> findProductsByFinanceId(Long financeId, Long insuranceId);

}
