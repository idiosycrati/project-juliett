package com.juliett.core.ProductsServiceImpl;

import com.juliett.core.Products.model.ProductsModel;
import com.juliett.core.ProductsRepository.ProductsRepository;
import com.juliett.core.ProductsRepositoryImpl.ProductsRepositoryImpl;
import com.juliett.core.ProductsService.ProductsService;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.service.impl.AbstractServiceImpl;

public class ProductsServiceImpl extends AbstractServiceImpl<ProductsModel> implements ProductsService {

	private final ProductsRepository productsRepository;
	
	public ProductsServiceImpl(DatabaseManager databaseManager) {
	
	super(new ProductsRepositoryImpl(databaseManager));
	this.productsRepository = (ProductsRepository) repository;
	
	}
}
