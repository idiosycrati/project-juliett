package com.juliett.core.FinanceEntityRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.juliett.core.FinanceEntity.model.FinanceEntityModel;
import com.juliett.core.FinanceEntityRepository.FinanceEntityRepository;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;

public class FinanceEntityRepositoryImpl extends AbstractRepositoryImpl<FinanceEntityModel>
		implements FinanceEntityRepository {

	private String tableName;
	private FinanceEntityModel financeEntityModel;
	Connection connection = null;
	PreparedStatement statement = null;

	public FinanceEntityRepositoryImpl(DatabaseManager databaseManager) {

		super(FinanceEntityModel.class, databaseManager);
		this.tableName = "finance_entity";
	}

	public Collection<FinanceEntityModel> findFinanceById(Long id) {

		Connection connection = null;
		PreparedStatement statement = null;
		List<FinanceEntityModel> items = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where id =?");

		try {

			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, id);

			sql(statement.toString());

			rs = statement.executeQuery();

			if (rs != null) {

				items = new ArrayList<>();
				while (rs.next()) {

					FinanceEntityModel financeEntityModel = new FinanceEntityModel(rs.getLong("id"),
							rs.getString("currency_name"), rs.getDouble("exchange_rate"));

					items.add(financeEntityModel);
					return items;
				}
			}

		} catch (Exception e) {

			error(e.getMessage());

		} finally {
			close(connection, statement);

		}
		return items;

	}

	@Override

	public Collection<FinanceEntityModel> list() throws ClassNotFoundException, SQLException, NamingException {

		Connection connection = null;
		PreparedStatement statement = null;
		List<FinanceEntityModel> items = null;
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

					FinanceEntityModel financeEntityModel = new FinanceEntityModel(rs.getLong("id"),
							rs.getString("currency_name"), rs.getDouble("exchange_rate"));

					items.add(financeEntityModel);
				}
			}

		} catch (Exception e) {

			error(e.getMessage());

		} finally {
			close(connection, statement);

		}
		return items;

	}

	public FinanceEntityModel insert(FinanceEntityModel financeEntityModel) {
		Connection connection = null;
		PreparedStatement statement = null;

		StringBuilder sql = new StringBuilder(
				"insert into " + this.tableName + " (currency_name, exchange_rate) VALUES (?,?)");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, financeEntityModel.getCurrencyName());
			statement.setDouble(2, financeEntityModel.getExchangeRate());
			sql(statement.toString());
			statement.executeUpdate();

		} catch (Exception e) {

			error(e.getMessage());
		} finally {

			close(connection, statement);
		}
		return financeEntityModel;
	}

	@Override

	public void update(FinanceEntityModel financeEntityModel)
			throws ClassNotFoundException, SQLException, NamingException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement statement = null;
		StringBuilder sql = new StringBuilder(
				"update " + this.tableName + " set currency_name = coalesce(?, currency_name),"
						+ "exchange_rate = coalesce(?,exchange_rate) where id = ?");
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());

			statement.setString(1, financeEntityModel.getCurrencyName());
			statement.setDouble(2, financeEntityModel.getExchangeRate());
			statement.setLong(3, financeEntityModel.getId());
			sql(statement.toString());
			statement.executeUpdate();

		} catch (Exception e) {

			error(e.getMessage());

		} finally {
			close(connection, statement);
		}

	}

	public void delete(FinanceEntityModel item) throws ClassNotFoundException, SQLException, NamingException {

		StringBuilder sql = new StringBuilder("delete from " + this.tableName + " where id = ?;");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, item.getId());

			sql(statement.toString());

			statement.executeUpdate();

		} catch (Exception e) {

			error(e.getMessage());
		} finally {
			close(connection, statement);
		}

	}

}
