package com.juliett.core.PaymentRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import com.juliett.core.Payment.model.PaymentModel;
import com.juliett.core.PaymentRepository.PaymentRepository;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;

public class PaymentRepositoryImpl extends AbstractRepositoryImpl<PaymentModel> implements PaymentRepository {

	private String tableName;
	private PaymentModel paymentModel;
	Connection connection = null;
	PreparedStatement statement = null;

	public PaymentRepositoryImpl(DatabaseManager databaseManager) {
		super(PaymentModel.class, databaseManager);
		this.tableName = "payment";
	}

	@Override
	public PaymentModel insert(PaymentModel paymentModel) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("insert into " + this.tableName
				+ "(amount, reference_number, transaction_id, payment_date, users_id) VALUES (?,?,?,?,?)");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString(), new String[] { "id" });
			statement.setDouble(1, paymentModel.getAmount());
			statement.setString(2, paymentModel.getReferenceNumber());
			statement.setLong(3, paymentModel.getTransactionId());
			statement.setString(4, paymentModel.getPaymentDate());
			statement.setLong(5, paymentModel.getUsersId());
			sql(statement.toString());
			statement.executeUpdate();

			ResultSet genkeys = statement.getGeneratedKeys();
			if (genkeys.next()) {
				paymentModel.setId(genkeys.getLong(1));
			}

		} catch (Exception e) {
			error(e.getMessage());
		} finally {
			close(connection, statement);
		}

		return paymentModel;
	}

	@Override
	public Collection<PaymentModel> findPaymentByUsersId(Long usersId) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		List<PaymentModel> items = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where users_id=?");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, usersId);

			resultSet = statement.executeQuery();

			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {

					PaymentModel paymentModel = new PaymentModel(resultSet.getLong("id"), resultSet.getDouble("amount"),
							resultSet.getString("reference_number"), resultSet.getLong("transaction_id"),
							resultSet.getString("payment_date"), resultSet.getLong("users_id"));
					items.add(paymentModel);
					return items;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}

		return items;
	}

	public Collection<PaymentModel> list() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		List<PaymentModel> items = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName);

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());

			resultSet = statement.executeQuery();

			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {

					PaymentModel paymentModel = new PaymentModel(resultSet.getLong("id"), resultSet.getDouble("amount"),
							resultSet.getString("reference_number"), resultSet.getLong("transaction_id"),
							resultSet.getString("payment_date"), resultSet.getLong("users_id"));
					items.add(paymentModel);
					return items;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}

		return items;
	}
}
