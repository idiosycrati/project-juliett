package com.juliett.core.AutoEmailRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import com.juliett.core.AutoEmailModel.AutoEmailModel;
import com.juliett.core.AutoEmailRepository.AutoEmailRepository;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;

public class AutoEmailRepositoryImpl extends AbstractRepositoryImpl<AutoEmailModel> implements AutoEmailRepository {

	private String tableName;
	private AutoEmailModel autoEmailModel;
	Connection connection = null;
	PreparedStatement statement = null;

	public AutoEmailRepositoryImpl(DatabaseManager databaseManager) {
		super(AutoEmailModel.class, databaseManager);
		this.tableName = "auto_email";
	}

	public AutoEmailModel insert(AutoEmailModel autoEmailModel) {
		StringBuilder sql = new StringBuilder("insert into " + this.tableName + " (transactions_id) VALUE (?)");
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString(), new String[] { "id" });
			statement.setLong(1, autoEmailModel.getTransactionsId());

			sql(statement.toString());
			statement.executeUpdate();
			ResultSet genkeys = statement.getGeneratedKeys();

			if (genkeys.next()) {
				autoEmailModel.setId(genkeys.getLong(1));
			}

		} catch (Exception e) {
			error(e.getMessage());
		} finally {
			close(connection, statement);
		}
		return autoEmailModel;
	}

	public void update(AutoEmailModel autoEmailModel) {
		StringBuilder sql = new StringBuilder("update " + this.tableName
				+ " set approval= coalesce(?, approval), termination = coalesce(?, termination), notice_due_date = coalesce(?,notice_due_date) where transactions_id = ?");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());

			statement.setBoolean(1, autoEmailModel.getApproval());
			statement.setBoolean(2, autoEmailModel.getTermination());
			statement.setBoolean(3, autoEmailModel.getNoticeDueDate());
			statement.setLong(4, autoEmailModel.getTransactionsId());
			sql(statement.toString());
			statement.executeUpdate();
		} catch (Exception e) {
			error(e.getMessage());
		} finally {
			close(connection, statement);
		}
	}

	@Override
	public Collection<AutoEmailModel> list() throws ClassNotFoundException, SQLException, NamingException {
		// TODO Auto-generated method stub
		List<AutoEmailModel> items = null;
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
					AutoEmailModel autoEmailModel = new AutoEmailModel(rs.getLong("id"), rs.getLong("transactions_id"),
							rs.getBoolean("approval"), rs.getBoolean("termination"), rs.getBoolean("notice_due_date"));
					items.add(autoEmailModel);
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

	public Collection<AutoEmailModel> findByTransactionsId(Long transactionsId) {
		// TODO Auto-generated method stub
		List<AutoEmailModel> items = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where transactions_id= ?");
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, transactionsId);
			sql(statement.toString());
			rs = statement.executeQuery();

			if (rs != null) {
				items = new ArrayList<>();
				while (rs.next()) {
					AutoEmailModel autoEmailModel = new AutoEmailModel(rs.getLong("id"), rs.getLong("transactions_id"),
							rs.getBoolean("approval"), rs.getBoolean("termination"), rs.getBoolean("notice_due_date"));
					items.add(autoEmailModel);
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

}
