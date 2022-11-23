package com.juliett.core.InsuranceTypeRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import com.juliett.core.InsuranceType.model.InsuranceTypeModel;
import com.juliett.core.InsuranceTypeRepository.InsuranceTypeRepository;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;

public class InsuranceTypeRepositoryImpl extends AbstractRepositoryImpl<InsuranceTypeModel>
		implements InsuranceTypeRepository {

	private String tableName;
	private InsuranceTypeModel insuranceTypeModel;
	Connection connection = null;
	PreparedStatement statement = null;
	private InsuranceTypeModel insuranceTypeModel2;

	public InsuranceTypeRepositoryImpl(DatabaseManager databaseManager) {
		super(InsuranceTypeModel.class, databaseManager);
		this.tableName = "type_of_insurance";
	}

	@Override
	public Collection<InsuranceTypeModel> list() throws ClassNotFoundException, SQLException, NamingException {

		List<InsuranceTypeModel> items = null;
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
					InsuranceTypeModel insuranceTypeModel = new InsuranceTypeModel(rs.getLong("id"),
							rs.getString("category"), rs.getInt("acceptance_eligibility"));

					items.add(insuranceTypeModel);

				}

			}
		} catch (Exception e) {

			error(e.getMessage());
		} finally {

			close(connection, statement);
		}
		return items;
	}

	public Collection<InsuranceTypeModel> findTypeById(Long typeId) {

		ResultSet resultSet = null;
		List<InsuranceTypeModel> items = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where id =?;");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, typeId);
			sql(statement.toString());

			resultSet = statement.executeQuery();
			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {
					InsuranceTypeModel insuranceTypeModel = new InsuranceTypeModel(resultSet.getLong("id"),
							resultSet.getString("category"), resultSet.getInt("acceptance_eligibility"));
					items.add(insuranceTypeModel);
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
