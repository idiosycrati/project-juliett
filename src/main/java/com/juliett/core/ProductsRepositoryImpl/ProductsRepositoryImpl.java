package com.juliett.core.ProductsRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import com.juliett.core.Products.model.ProductsModel;
import com.juliett.core.ProductsRepository.ProductsRepository;

import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;

public class ProductsRepositoryImpl extends AbstractRepositoryImpl<ProductsModel> implements ProductsRepository {

	private String tableName;

	public ProductsRepositoryImpl(DatabaseManager databaseManager) {
		super(ProductsModel.class, databaseManager);
		this.tableName = "products";

	}

	public Collection<ProductsModel> list() throws ClassNotFoundException, SQLException, NamingException {

		Connection connection = null;
		PreparedStatement statement = null;
		List<ProductsModel> items = null;
		ResultSet resultSet = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName);

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			sql(statement.toString());

			resultSet = statement.executeQuery();

			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {

					ProductsModel productsModel = new ProductsModel(resultSet.getLong("product_id"),
							resultSet.getString("category"), resultSet.getInt("premium"), resultSet.getInt("tenure"),
							resultSet.getString("creation_date"));
					items.add(productsModel);

				}
			}

		} catch (Exception e) {
			error(e.getMessage());

		} finally {
			close(connection, statement);

		}
		return items;

	}

}
