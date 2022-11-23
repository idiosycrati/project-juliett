package com.juliett.core.ProductsRepositoryImpl;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import com.juliett.core.Plans.model.PlansModel;
import com.juliett.core.Products.model.ProductsModel;
import com.juliett.core.ProductsRepository.ProductsRepository;

import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;

public class ProductsRepositoryImpl extends AbstractRepositoryImpl<ProductsModel> implements ProductsRepository {

	private String tableName;
	private String planTable;
	Connection connection = null;
	PreparedStatement statement = null;

	public ProductsRepositoryImpl(DatabaseManager databaseManager) {
		super(ProductsModel.class, databaseManager);
		this.tableName = "products";
		this.planTable = "plans";

	}

	public Collection<ProductsModel> findProductsById(Long id) {

		List<ProductsModel> items = null;
		ResultSet resultSet = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where id=?");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, id);
			sql(statement.toString());

			resultSet = statement.executeQuery();

			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {

					ProductsModel productsModel = new ProductsModel(resultSet.getLong("id"),
							resultSet.getString("category"), resultSet.getInt("premium"),
							resultSet.getInt("finance_entity_id"), resultSet.getInt("monthly"),
							resultSet.getInt("cash"), resultSet.getInt("quarterly"), resultSet.getInt("term"),
							resultSet.getInt("sum_assurance"), resultSet.getInt("insurance_type_id"));
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

					ProductsModel productsModel = new ProductsModel(resultSet.getLong("id"),
							resultSet.getString("category"), resultSet.getInt("premium"),
							resultSet.getInt("finance_entity_id"), resultSet.getInt("monthly"),
							resultSet.getInt("cash"), resultSet.getInt("quarterly"), resultSet.getInt("term"),
							resultSet.getInt("sum_assurance"), resultSet.getInt("insurance_type_id"));
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

	public Collection<PlansModel> listPlan() {
		Connection connection = null;
		PreparedStatement statement = null;
		List<PlansModel> items = null;
		ResultSet resultSet = null;

		StringBuilder sql = new StringBuilder("select * from " + this.planTable);
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			sql(statement.toString());
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {

					PlansModel plansModel = new PlansModel(resultSet.getLong("id"), resultSet.getString("plan_type"));
				}
			}
		} catch (Exception e) {
			error(e.getMessage());

		} finally {
			close(connection, statement);
		}
		return items;
	}

	public void btcSumAssurance(ProductsModel productsModel) {
		Connection connection = null;
		PreparedStatement statement = null;
		StringBuilder sql = new StringBuilder("update " + this.tableName
				+ " set sum_assurance = ? where finance_entity_id= 1 and insurance_type_id= ?");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setInt(1, productsModel.getSumAssurance());
			statement.setLong(2, productsModel.getInsuranceTypeId());
			sql(statement.toString());
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}
	}

	public Collection<ProductsModel> findProductsByFinanceId(Long financeId, Long insuranceId) {

		Connection connection = null;
		PreparedStatement statement = null;
		List<ProductsModel> items = null;
		ResultSet resultSet = null;

		StringBuilder sql = new StringBuilder(
				"select * from " + this.tableName + " where finance_entity_id=? and insurance_type_id = ?");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, financeId);
			statement.setLong(2, insuranceId);
			sql(statement.toString());

			resultSet = statement.executeQuery();

			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {

					ProductsModel productsModel = new ProductsModel(resultSet.getLong("id"),
							resultSet.getString("category"), resultSet.getInt("premium"),
							resultSet.getInt("finance_entity_id"), resultSet.getInt("monthly"),
							resultSet.getInt("cash"), resultSet.getInt("quarterly"), resultSet.getInt("term"),
							resultSet.getInt("sum_assurance"), resultSet.getInt("insurance_type_id"));
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
