package com.juliett.core.UsersRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;

import com.juliett.core.Users.model.UsersModel;
import com.juliett.core.UsersRepository.UsersRepository;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;

public class UsersRepositoryImpl extends AbstractRepositoryImpl<UsersModel> implements UsersRepository {
	private String tableName;
	private UsersModel usersModel;
	Connection connection = null;
	PreparedStatement statement = null;

	/**
	 * @param databaseManager
	 */
	public UsersRepositoryImpl(DatabaseManager databaseManager) {

		super(UsersModel.class, databaseManager);
		this.tableName = "users";

	}

	@Override
	public Boolean emailIsAlreadyTaken(String input_email) {

		StringBuilder sql = new StringBuilder("Select * from users where email = ?;");

		try {

			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, input_email);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}

		return false;
	}

	public Collection<UsersModel> findAccountByToken(String token) {

		StringBuilder sql = new StringBuilder("SELECT * from " + tableName + " where tokens= ?");

		List<UsersModel> items = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, token);
			sql(statement.toString());

			resultSet = statement.executeQuery();

			if (resultSet != null) {

				items = new ArrayList<>();
				while (resultSet.next()) {
					UsersModel usersModel = new UsersModel(resultSet.getLong("id"), resultSet.getString("first_name"),
							resultSet.getString("last_name"), resultSet.getString("email"),
							resultSet.getString("password"), resultSet.getString("contact"),
							resultSet.getString("date_of_birth"), resultSet.getString("address"),
							resultSet.getBoolean("is_admin"));
					items.add(usersModel);
					return items;

				}
			}
		} catch (Exception e) {
			error(e.getMessage());

		} finally {
			close(connection, statement);
		}

		return null;

	}

	public UsersModel checkUser(UsersModel usersModel) {

		StringBuilder sql = new StringBuilder("SELECT * from " + tableName + " where email= ?;");

		try {

			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, usersModel.getEmail());

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				usersModel.setId(rs.getLong("id"));
				usersModel.setFirstName(rs.getString("first_name"));
				usersModel.setLastName(rs.getString("last_name"));
				usersModel.setPassword(rs.getString("password"));
				usersModel.setContact(rs.getString("contact"));
				usersModel.setAddress(rs.getString("address"));
				usersModel.setDateOfBirth(rs.getString("date_of_birth"));
				usersModel.setIsAdmin(rs.getBoolean("is_admin"));

				return usersModel;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}

		return null;

	}

	public Boolean isAdmin(String token) {

		StringBuilder sql = new StringBuilder("SELECT * from " + tableName + " where tokens= ?;");

		try {

			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, token);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				Boolean isAdmin = rs.getBoolean("is_admin");
				return isAdmin;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}

		return false;

	}

	public Boolean foundAccount(String token) {

		StringBuilder sql = new StringBuilder("SELECT * from " + tableName + " where tokens= ?;");

		try {

			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, token);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {

				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}

		return false;

	}

//	public UsersModel findAccountById(Integer id) {
//
//		Connection connection = null;
//		PreparedStatement statement = null;
//
//		StringBuilder sql = new StringBuilder("Select * from " + tableName + " where id= ?;");
//
//		try {
//
//			connection = getConnection();
//			statement = connection.prepareStatement(sql.toString());
//			statement.setInt(1, id);
//			System.out.println("Running impl");
//			ResultSet rs = statement.executeQuery();
//
//			if (rs.next()) {
//
//				usersModel = null;
//
//				usersModel.setFirstName(rs.getString("first_name"));
//				usersModel.setLastName(rs.getString("last_name"));
//				usersModel.setEmail(rs.getString("email"));
//				usersModel.setDateOfBirth(rs.getString("date_of_birth"));
//				usersModel.setContact(rs.getString("contact"));
//				usersModel.setAddress(rs.getString("address"));
//				usersModel.setIsAdmin(rs.getBoolean("is_admin"));
//				usersModel.setTokens(rs.getString("tokens"));
//
//				return usersModel;
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(connection, statement);
//		}
//
//		return null;
//	}

	public void updateToken(UsersModel usersModel) {
		Connection connection = null;
		PreparedStatement statement = null;
		System.out.println("token testing");
		StringBuilder sql = new StringBuilder("update " + this.tableName + " set tokens = ? where id = ?");

		try {

			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, usersModel.getTokens());
			statement.setLong(2, usersModel.getId());
			sql(statement.toString());

			statement.executeUpdate();
		} catch (Exception e) {
			error(e.getMessage());
			System.out.println(e.getMessage());
		} finally {

			close(connection, statement);
		}

	}

	public Collection<UsersModel> findUserById(Integer id) {

		Connection connection = null;
		PreparedStatement statement = null;
		List<UsersModel> items = null;
		ResultSet resultSet = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where id=?;");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setInt(1, id);
			sql(statement.toString());

			resultSet = statement.executeQuery();

			if (resultSet != null) {

				items = new ArrayList<>();
				while (resultSet.next()) {
					UsersModel usersModel = new UsersModel(resultSet.getLong("id"), resultSet.getString("first_name"),
							resultSet.getString("last_name"), resultSet.getString("email"),
							resultSet.getString("password"), resultSet.getString("contact"),
							resultSet.getString("date_of_birth"), resultSet.getString("address"),
							resultSet.getBoolean("is_admin"));
					items.add(usersModel);
					return items;

				}
			}
		} catch (Exception e) {
			error(e.getMessage());

		} finally {
			close(connection, statement);
		}

		return null;

	}

	public void updateUsers(UsersModel usersModel) {
		Connection connection = null;
		PreparedStatement statement = null;

		StringBuilder sql = new StringBuilder("update " + this.tableName
				+ " set first_name = coalesce(?, first_name),  last_name = coalesce(?, last_name),  password = coalesce(?, password),  contact = coalesce(?, contact) ,"
				+ "  address = coalesce(?, address) where tokens = ?");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());

			statement.setString(1, usersModel.getFirstName());
			statement.setString(2, usersModel.getLastName());
			statement.setString(3, usersModel.getPassword());
			statement.setString(4, usersModel.getContact());
			statement.setString(5, usersModel.getAddress());
			statement.setString(6, usersModel.getTokens());
			sql(statement.toString());

			statement.executeUpdate();

		} catch (Exception e) {
			error(e.getMessage());
			System.out.println(e.getMessage());

		} finally {
			close(connection, statement);
		}
	}

	public void updateIsAdmin(UsersModel usersModel) {
		Connection connection = null;
		PreparedStatement statement = null;

		StringBuilder sql = new StringBuilder("update " + this.tableName + " set is_admin = ? where id = ?");

		try {

			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());

			statement.setBoolean(1, usersModel.getIsAdmin());
			statement.setLong(2, usersModel.getId());
			sql(statement.toString());
			statement.executeUpdate();
		} catch (Exception e) {
			error(e.getMessage());
			System.out.println(e.getMessage());
		} finally {

			close(connection, statement);
		}

	}

	public UsersModel insert(UsersModel usersModel) {

		Connection connection = null;
		PreparedStatement statement = null;

		StringBuilder sql = new StringBuilder("INSERT INTO " + this.tableName
				+ " (first_name,last_name,email,password,date_of_birth,contact,address,tokens,is_admin) VALUES (?,?,?,?,?,?,?,?,?);");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString(), new String[] { "id" });

			statement.setString(1, usersModel.getFirstName());
			statement.setString(2, usersModel.getLastName());
			statement.setString(3, usersModel.getEmail());
			statement.setString(4, usersModel.getPassword());
			statement.setString(5, usersModel.getDateOfBirth());
			statement.setString(6, usersModel.getContact());
			statement.setString(7, usersModel.getAddress());
			statement.setString(8, usersModel.getTokens());
			statement.setBoolean(9, usersModel.getIsAdmin());
			sql(statement.toString());

			statement.executeUpdate();
			ResultSet genkeys = statement.getGeneratedKeys();

			if (genkeys.next()) {
				usersModel.setId(genkeys.getLong(1));
			}

		} catch (Exception e) {

			error(e.getMessage());
		} finally {

			close(connection, statement);
		}

		return usersModel;

	}

	public Collection<UsersModel> list() throws ClassNotFoundException, SQLException, NamingException {

		Connection connection = null;
		PreparedStatement statement = null;
		List<UsersModel> items = null;
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
					UsersModel usersModel = new UsersModel(resultSet.getLong("id"), resultSet.getString("first_name"),
							resultSet.getString("last_name"), resultSet.getString("email"),
							resultSet.getString("password"), resultSet.getString("contact"),
							resultSet.getString("date_of_birth"), resultSet.getString("address"),
							resultSet.getBoolean("is_admin"));
					items.add(usersModel);

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
