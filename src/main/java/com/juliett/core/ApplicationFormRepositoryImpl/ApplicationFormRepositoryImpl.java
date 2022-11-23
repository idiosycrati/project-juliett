package com.juliett.core.ApplicationFormRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import com.juliett.core.ApplicationForm.model.ApplicationFormModel;
import com.juliett.core.ApplicationFormRepository.ApplicationFormRepository;

import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;

public class ApplicationFormRepositoryImpl extends AbstractRepositoryImpl<ApplicationFormModel>
		implements ApplicationFormRepository {

	private String tableName;
	private ApplicationFormModel applicationFormModel;
	Connection connection = null;
	PreparedStatement statement = null;

	public ApplicationFormRepositoryImpl(DatabaseManager databaseManager) {

		super(ApplicationFormModel.class, databaseManager);
		this.tableName = "application_form";

	}

	@Override
	public ApplicationFormModel insert(ApplicationFormModel applicationFormModel)
			throws ClassNotFoundException, SQLException, NamingException {

		StringBuilder sql = new StringBuilder("insert into " + this.tableName
				+ " (users_id, insurance_entity_id, insurance_type_id, product_id,"
				+ " plan_id, e_signature, item_health, is_approved, approved_by, date_applied, date_approved) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

		try {

			connection = getConnection();
			statement = connection.prepareStatement(sql.toString(), new String[] { "id" });

			statement.setLong(1, applicationFormModel.getUsersId());
			statement.setLong(2, applicationFormModel.getInsuranceEntityId());
			statement.setLong(3, applicationFormModel.getInsuranceTypeId());
			statement.setLong(4, applicationFormModel.getProductId());
			statement.setLong(5, applicationFormModel.getPlanId());
			statement.setString(6, applicationFormModel.geteSignature());
			statement.setInt(7, applicationFormModel.getItemHealth());
			statement.setBoolean(8, applicationFormModel.getIsApproved());
			statement.setString(9, applicationFormModel.getApprovedBy());
			statement.setString(10, applicationFormModel.getDateApplied());
			statement.setString(11, applicationFormModel.getDateApproved());
			sql(statement.toString());
			statement.executeUpdate();
			ResultSet genkeys = statement.getGeneratedKeys();
			if (genkeys.next()) {
				applicationFormModel.setId(genkeys.getLong(1));
			}
		} catch (Exception e) {
			error(e.getMessage());

		} finally {
			close(connection, statement);
		}
		return applicationFormModel;

	}

	public void update(ApplicationFormModel applicationFormModel) {

		StringBuilder sql = new StringBuilder("update " + this.tableName
				+ " set is_approved = coalesce(?, is_approved), approved_by = coalesce(?, approved_by), date_approved = coalesce(?, date_approved) where id=?;");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setBoolean(1, applicationFormModel.getIsApproved());
			statement.setString(2, applicationFormModel.getApprovedBy());
			statement.setString(3, applicationFormModel.getDateApproved());
			statement.setLong(4, applicationFormModel.getId());
			sql(statement.toString());
			statement.executeUpdate();
		} catch (Exception e) {
			error(e.getMessage());
		} finally {
			close(connection, statement);
		}
	}

	public Collection<ApplicationFormModel> findApplicationByUsersId(Long usersId) {
		ResultSet resultSet = null;
		List<ApplicationFormModel> items = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where users_id=?;");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, usersId);
			sql(statement.toString());

			resultSet = statement.executeQuery();

			if (resultSet != null) {

				items = new ArrayList<>();
				while (resultSet.next()) {
					ApplicationFormModel applicationFormModel = new ApplicationFormModel(resultSet.getLong("id"),
							resultSet.getLong("users_id"), resultSet.getLong("insurance_entity_id"),
							resultSet.getLong("insurance_type_id"), resultSet.getLong("product_id"),
							resultSet.getLong("plan_id"), resultSet.getString("e_signature"),
							resultSet.getInt("item_health"), resultSet.getBoolean("is_approved"),
							resultSet.getString("approved_by"), resultSet.getString("date_applied"),
							resultSet.getString("date_approved"));
					items.add(applicationFormModel);
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

	public Collection<ApplicationFormModel> findApplicationById(Long id) {
		ResultSet resultSet = null;
		List<ApplicationFormModel> items = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where id=?;");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, id);
			sql(statement.toString());

			resultSet = statement.executeQuery();

			if (resultSet != null) {

				items = new ArrayList<>();
				while (resultSet.next()) {
					ApplicationFormModel applicationFormModel = new ApplicationFormModel(resultSet.getLong("id"),
							resultSet.getLong("users_id"), resultSet.getLong("insurance_entity_id"),
							resultSet.getLong("insurance_type_id"), resultSet.getLong("product_id"),
							resultSet.getLong("plan_id"), resultSet.getString("e_signature"),
							resultSet.getInt("item_health"), resultSet.getBoolean("is_approved"),
							resultSet.getString("approved_by"), resultSet.getString("date_applied"),
							resultSet.getString("date_approved"));
					items.add(applicationFormModel);
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
	public Collection<ApplicationFormModel> list() throws ClassNotFoundException, SQLException, NamingException {
		// TODO Auto-generated method stub
		List<ApplicationFormModel> items = null;
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
					ApplicationFormModel applicationFormModel = new ApplicationFormModel(resultSet.getLong("id"),
							resultSet.getLong("users_id"), resultSet.getLong("insurance_entity_id"),
							resultSet.getLong("insurance_type_id"), resultSet.getLong("product_id"),
							resultSet.getLong("plan_id"), resultSet.getString("e_signature"),
							resultSet.getInt("item_health"), resultSet.getBoolean("is_approved"),
							resultSet.getString("approved_by"), resultSet.getString("date_applied"),
							resultSet.getString("date_approved"));
					items.add(applicationFormModel);
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
