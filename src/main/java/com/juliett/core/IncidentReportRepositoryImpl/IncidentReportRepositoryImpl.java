package com.juliett.core.IncidentReportRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import com.juliett.core.IncidentReportModel.IncidentReportModel;
import com.juliett.core.IncidentReportRepository.IncidentReportRepository;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;

public class IncidentReportRepositoryImpl extends AbstractRepositoryImpl<IncidentReportModel>
		implements IncidentReportRepository {

	private String tableName;
	private IncidentReportModel incidentReportModel;
	Connection connection = null;
	PreparedStatement statement = null;

	public IncidentReportRepositoryImpl(DatabaseManager databaseManager) {
		super(IncidentReportModel.class, databaseManager);
		this.tableName = "incident_report";
	}

	public Collection<IncidentReportModel> findReportById(Long id) {
		List<IncidentReportModel> items = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where id = ?");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, id);
			sql(statement.toString());
			rs = statement.executeQuery();
			if (rs != null) {
				items = new ArrayList<>();
				while (rs.next()) {
					IncidentReportModel incidentReportModel = new IncidentReportModel(rs.getLong("id"),
							rs.getLong("transactions_id"), rs.getLong("users_id"), rs.getString("date_report"),
							rs.getString("date_incident"), rs.getString("type_of_incident"),
							rs.getBoolean("is_approved"), rs.getString("description_of_incident"),
							rs.getString("approved_by"), rs.getString("date_approved"));
					items.add(incidentReportModel);
		
				}
			}
		} catch (Exception e) {
			error(e.getMessage());
		} finally {
			close(connection, statement);
		}
		return items;
	}

	public Collection<IncidentReportModel> list() {
		List<IncidentReportModel> items = null;
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
					IncidentReportModel incidentReportModel = new IncidentReportModel(rs.getLong("id"),
							rs.getLong("transactions_id"), rs.getLong("users_id"), rs.getString("date_report"),
							rs.getString("date_incident"), rs.getString("type_of_incident"),
							rs.getBoolean("is_approved"), rs.getString("description_of_incident"),
							rs.getString("approved_by"), rs.getString("date_approved"));
					items.add(incidentReportModel);
					
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
	public IncidentReportModel insert(IncidentReportModel incidentReportModel) {
		StringBuilder sql = new StringBuilder("insert into " + this.tableName
				+ " ( transactions_id, users_id , date_report  ,date_incident, type_of_incident, description_of_incident ) VALUES (?,?,?,?,?,?)");
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString(), new String[] { "id" });
			statement.setLong(1, incidentReportModel.getTransactionsId());
			statement.setLong(2, incidentReportModel.getUsersId());
			statement.setString(3, incidentReportModel.getDateReport());
			statement.setString(4, incidentReportModel.getDateIncident());
			statement.setString(5, incidentReportModel.getTypeOfIncident());
			statement.setString(6, incidentReportModel.getDescriptionOfIncident());
			sql(statement.toString());
			statement.executeUpdate();
			ResultSet genkeys = statement.getGeneratedKeys();

			if (genkeys.next()) {
				incidentReportModel.setId(genkeys.getLong(1));
			}

		} catch (Exception e) {
			error(e.getMessage());
		} finally {
			close(connection, statement);
		}
		return incidentReportModel;
	}

	@Override
	public void update(IncidentReportModel incidentReportModel) {
		StringBuilder sql = new StringBuilder(" update " + this.tableName
				+ " set approved_by = coalesce(?,approved_by), date_approved= coalesce(?,date_approved),is_approved = coalesce(?, is_approved) where id =?");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, incidentReportModel.getApprovedBy());
			statement.setString(2, incidentReportModel.getDateApproved());
			statement.setBoolean(3, incidentReportModel.getIsApproved());
			statement.setLong(4, incidentReportModel.getId());

			sql(statement.toString());
			statement.executeUpdate();
		} catch (Exception e) {
			error(e.getMessage());
		} finally {
			close(connection, statement);
		}

	}
}
