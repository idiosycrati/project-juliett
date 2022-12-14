package com.juliett.core.TransactionsDetailsRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import com.juliett.core.TransactionsDetailsModel.TransactionsDetailsModel;
import com.juliett.core.TransactionsDetailsRepository.TransactionsDetailsRepository;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;

public class TransactionsDetailsRepositoryImpl extends AbstractRepositoryImpl<TransactionsDetailsModel>
		implements TransactionsDetailsRepository {

	private String tableName;
	private TransactionsDetailsModel transactionsDetailsModel;
	Connection connection = null;
	PreparedStatement statement = null;

	public TransactionsDetailsRepositoryImpl(DatabaseManager databaseManager) {
		super(TransactionsDetailsModel.class, databaseManager);
		this.tableName = "transactions_details";
	}

	@Override
	public Collection<TransactionsDetailsModel> list() {
		// TODO Auto-generated method stub
		List<TransactionsDetailsModel> items = null;
		ResultSet resultSet = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName);
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());

			resultSet = statement.executeQuery();
			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {
					TransactionsDetailsModel transactionsDetailsModel = new TransactionsDetailsModel(
							resultSet.getLong("id"), resultSet.getLong("transactions_id"),
							resultSet.getInt("amount_claim"), resultSet.getString("date_claim"));
					items.add(transactionsDetailsModel);
				}
			}
		} catch (Exception e) {
			error(e.getMessage());
		} finally {
			close(connection, statement);
		}
		return items;
	}

	public Collection<TransactionsDetailsModel> findTransactionsDetailsByTransId(Long id) {
		// TODO Auto-generated method stub
		List<TransactionsDetailsModel> items = null;
		ResultSet resultSet = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName);
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, id);
			sql(statement.toString());
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {
					TransactionsDetailsModel transactionsDetailsModel = new TransactionsDetailsModel(
							resultSet.getLong("id"), resultSet.getLong("transactions_id"),
							resultSet.getInt("amount_claim"), resultSet.getString("date_claim"));
					items.add(transactionsDetailsModel);
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
	public TransactionsDetailsModel insert(TransactionsDetailsModel transactionsDetailsModel)
			throws ClassNotFoundException, SQLException, NamingException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(
				"insert into " + this.tableName + " (transactions_id, amount_claim,date_claim) VALUES (?,?,?)");
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString(), new String[] { "id" });
			statement.setLong(1, transactionsDetailsModel.getTransactionsId());
			statement.setInt(2, transactionsDetailsModel.getAmount_claim());
			statement.setString(3, transactionsDetailsModel.getDate_claim());
			sql(statement.toString());
			statement.executeUpdate();
			ResultSet genkeys = statement.getGeneratedKeys();

			if (genkeys.next()) {
				transactionsDetailsModel.setId(genkeys.getLong(1));
			}
		} catch (Exception e) {
			error(e.getMessage());

		} finally {
			close(connection, statement);
		}
		return transactionsDetailsModel;
	}
}
