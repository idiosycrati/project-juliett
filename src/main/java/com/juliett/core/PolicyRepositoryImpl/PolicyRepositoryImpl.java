package com.juliett.core.PolicyRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import com.juliett.core.Policy.model.PolicyModel;
import com.juliett.core.PolicyRepository.PolicyRepository;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;

public class PolicyRepositoryImpl extends AbstractRepositoryImpl<PolicyModel> implements PolicyRepository {

	private PolicyModel policyModel;
	private String tableName;
	Connection connection;
	PreparedStatement statement;

	public PolicyRepositoryImpl(DatabaseManager databaseManager) {
		super(PolicyModel.class, databaseManager);

		this.tableName = "policy";

	}

	@Override
	public Collection<PolicyModel> list() throws ClassNotFoundException, SQLException, NamingException {

		List<PolicyModel> items = null;

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
					PolicyModel policyModel = new PolicyModel(rs.getLong("id"), rs.getString("policy_name"),
							rs.getString("description"), rs.getLong("plans_category"), rs.getString("json_policy"));

					items.add(policyModel);
				}
			}

		} catch (Exception e) {

			error(e.getMessage());

		} finally {
			close(connection, statement);
		}

		return items;

	}

	public Collection<PolicyModel> findPolicyByPlansCategory(Long id) {

		List<PolicyModel> items = null;

		ResultSet rs = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where plans_category=?");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, id);

			sql(statement.toString());
			rs = statement.executeQuery();

			if (rs != null) {

				items = new ArrayList<>();
				while (rs.next()) {
					PolicyModel policyModel = new PolicyModel(rs.getLong("id"), rs.getString("policy_name"),
							rs.getString("description"), rs.getLong("plans_category"), rs.getString("json_policy"));

					items.add(policyModel);
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
	public PolicyModel insert(PolicyModel policyModel) throws ClassNotFoundException, SQLException, NamingException {

		StringBuilder sql = new StringBuilder(
				"insert into " + this.tableName + "(policy_name, description, plans_category) VALUES (?,?,?)");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());

			statement.setString(1, policyModel.getPolicyName());
			statement.setString(2, policyModel.getDescription());
			statement.setLong(3, policyModel.getPlansCategory());
			sql(statement.toString());
			statement.executeUpdate();

		} catch (Exception e) {
			error(e.getMessage());
		} finally {

			close(connection, statement);
		}
		return policyModel;
	}

	@Override
	public void update(PolicyModel policyModel) {
		// TODO Auto-generated method stub

		StringBuilder sql = new StringBuilder("update " + this.tableName
				+ "set policy_name = coalesce(?, policy_name), description = coalesce(?, description),"
				+ " product_id = coalesce(?, product_id) where id = ?");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());

			statement.setString(1, policyModel.getPolicyName());
			statement.setString(2, policyModel.getDescription());
			statement.setLong(3, policyModel.getPlansCategory());
			statement.setLong(4, policyModel.getId());

			sql(statement.toString());
			statement.executeUpdate();

		} catch (Exception e) {
			error(e.getMessage());
		} finally {

			close(connection, statement);
		}

	}

	@Override
	public void delete(PolicyModel policyModel) throws ClassNotFoundException, SQLException, NamingException {

		StringBuilder sql = new StringBuilder("delete from " + this.tableName + "where id = ?");
		try {

			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, policyModel.getId());

			sql(statement.toString());
			statement.executeUpdate();

		} catch (Exception e) {
			error(e.getMessage());
		} finally {
			close(connection, statement);
		}
	}

}
