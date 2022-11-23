package com.juliett.core.InsuranceEntityRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import com.juliett.core.FinanceEntity.model.FinanceEntityModel;
import com.juliett.core.InsuranceEntity.model.InsuranceEntityModel;
import com.juliett.core.InsuranceEntityRepository.InsuranceEntityRepository;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;

public class InsuranceEntityRepositoryImpl extends AbstractRepositoryImpl<InsuranceEntityModel>
		implements InsuranceEntityRepository {

	private String tableName;

	private InsuranceEntityModel insuranceEntityModel;
	Connection connection = null;
	PreparedStatement statement = null;

	public InsuranceEntityRepositoryImpl(DatabaseManager databaseManager) {
		super(InsuranceEntityModel.class, databaseManager);
		this.tableName = "insurance_entity";

	}

	public Collection<InsuranceEntityModel> findItemById(Long id) {

		List<InsuranceEntityModel> items = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where id = ?");

		try {

			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());

			statement.setLong(1, id);

			rs = statement.executeQuery();
			if (rs != null) {

				items = new ArrayList<>();
				while (rs.next()) {

					InsuranceEntityModel insuranceEntityModel = new InsuranceEntityModel(rs.getLong("id"),
							rs.getString("item_name"), rs.getLong("insurance_id"));

					items.add(insuranceEntityModel);
				}
			}
		} catch (Exception e) {
			error(e.getMessage());
		} finally {

			close(connection, statement);
		}
		return items;

	}

	public Collection<InsuranceEntityModel> list() throws ClassNotFoundException, SQLException, NamingException {

		List<InsuranceEntityModel> items = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName);

		try {

			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());

			sql(statement.toString());

			rs = statement.executeQuery();

			if (rs != null) {

				items = new ArrayList<>();
				while (rs.next()) {

					InsuranceEntityModel insuranceEntityModel = new InsuranceEntityModel(rs.getLong("id"),
							rs.getString("item_name"), rs.getLong("insurance_id"));

					items.add(insuranceEntityModel);

				}
			}

		} catch (Exception e) {

			error(e.getMessage());

		} finally {

			close(connection, statement);

		}
		return items;

	}

	public InsuranceEntityModel insert(InsuranceEntityModel insuranceEntityModel) {

		StringBuilder sql = new StringBuilder(
				"insert into " + this.tableName + " (item_name, insurance_id) VALUES (?,?)");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, insuranceEntityModel.getItemName());
			statement.setLong(2, insuranceEntityModel.getInsuranceId());
			sql(statement.toString());
			statement.executeUpdate();

		} catch (Exception e) {

			error(e.getMessage());
		} finally {

			close(connection, statement);
		}
		return insuranceEntityModel;
	}

	@Override
	public void update(InsuranceEntityModel insuranceEntityModel)
			throws ClassNotFoundException, SQLException, NamingException {
		// TODO Auto-generated method stub

		StringBuilder sql = new StringBuilder("update " + this.tableName
				+ " set item_name = coalesce(?, item_name), insurance_id= coalesce(?, insurance_id) where id=?");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, insuranceEntityModel.getItemName());
			statement.setLong(2, insuranceEntityModel.getInsuranceId());
			statement.setLong(3, insuranceEntityModel.getId());
			sql(statement.toString());
			statement.executeUpdate();
		} catch (Exception e) {
			error(e.getMessage());
		} finally {
			close(connection, statement);
		}
	}

	@Override
	public void delete(InsuranceEntityModel insuranceEntityModel)
			throws ClassNotFoundException, SQLException, NamingException {

		StringBuilder sql = new StringBuilder("delete from " + this.tableName + " where id=?");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, insuranceEntityModel.getId());

			sql(statement.toString());
			statement.executeUpdate();
		} catch (Exception e) {

			error(e.getMessage());

		} finally {
			close(connection, statement);
		}
	}

}
