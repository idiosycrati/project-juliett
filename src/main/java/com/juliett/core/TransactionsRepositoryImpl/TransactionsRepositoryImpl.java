package com.juliett.core.TransactionsRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import com.juliett.core.Transactions.model.TransactionsModel;
import com.juliett.core.TransactionsRepository.TransactionsRepository;
import com.juliett.core.model.enums.Status;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;

public class TransactionsRepositoryImpl extends AbstractRepositoryImpl<TransactionsModel>
		implements TransactionsRepository {

	private String tableName;
	private TransactionsModel transactionsModel;
	Connection connection = null;
	PreparedStatement statement = null;

	public TransactionsRepositoryImpl(DatabaseManager databaseManager) {
		super(TransactionsModel.class, databaseManager);
		this.tableName = "transactions";

	}

	public TransactionsModel insert(TransactionsModel transactionsModel) {

		StringBuilder sql = new StringBuilder("insert into " + this.tableName
				+ "(application_form_id, subscription_date, total_amount_paid, remaining_balance,subscription_date_end,status,due_date_payment,due_date_termination,currency_coin_qty,users_id) VALUES (?,?,?,?,?,?,?,?,?,?)");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString(), new String[] { "id" });
			statement.setLong(1, transactionsModel.getApplicationFormId());
			statement.setString(2, transactionsModel.getSubscriptionDate());
			statement.setDouble(3, transactionsModel.getTotalAmountPaid());
			statement.setDouble(4, transactionsModel.getRemainingBalance());
			statement.setString(5, transactionsModel.getSubscriptionDateEnd());
			statement.setString(6, transactionsModel.getStatus().getTitle());
			statement.setString(7, transactionsModel.getDueDatePayment());
			statement.setString(8, transactionsModel.getDueDateTermination());
			statement.setDouble(9, transactionsModel.getCurrencyCoinQty());
			statement.setLong(9, transactionsModel.getUsersId());
			sql(statement.toString());
			statement.executeUpdate();
			ResultSet genkeys = statement.getGeneratedKeys();

			if (genkeys.next()) {
				transactionsModel.setId(genkeys.getLong(1));
			}

		} catch (Exception e) {
			error(e.getMessage());
		} finally {
			close(connection, statement);
		}

		return transactionsModel;
	}

	@Override
	public void update(TransactionsModel transactionsModel)
			throws ClassNotFoundException, SQLException, NamingException {
		// TODO Auto-generated method stub

		StringBuilder sql = new StringBuilder("update " + this.tableName
				+ " set total_amount_paid = coalesce(?, total_amount_paid), remaining_balance = coalesce(? , remaining_balance), subscription_date = coalesce(?, subscription_date), due_date_payment = coalesce(?,due_date_payment), due_date_termination = coalesce(?, due_date_termination) where id =?;");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());

			statement.setDouble(1, transactionsModel.getTotalAmountPaid());
			statement.setDouble(2, transactionsModel.getRemainingBalance());
			statement.setString(3, transactionsModel.getSubscriptionDate());
			statement.setString(4, transactionsModel.getDueDatePayment());
			statement.setString(5, transactionsModel.getDueDateTermination());

			statement.setLong(6, transactionsModel.getId());
			sql(statement.toString());
			statement.executeUpdate();

		} catch (Exception e) {
			error(e.getMessage());

		} finally {
			close(connection, statement);
		}
	}

	public void updateClaims(TransactionsModel transactionsModel) {
		// TODO Auto-generated method stub

		StringBuilder sql = new StringBuilder("update " + this.tableName
				+ " set claims =coalesce(?,claims), claimable_assurance=coalesce(?,claimable_assurance) where id =?;");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());

			statement.setInt(1, transactionsModel.getClaims());
			statement.setDouble(2, transactionsModel.getClaimableAssurance());
			statement.setLong(3, transactionsModel.getId());
			sql(statement.toString());
			statement.executeUpdate();

		} catch (Exception e) {
			error(e.getMessage());

		} finally {
			close(connection, statement);
		}
	}

	public void updateClaimAssurance(Double multiplier) {
		// TODO Auto-generated method stub

		StringBuilder sql = new StringBuilder(
				"update " + this.tableName + " set claimable_assurance  = sum_assurance * ? where claims != 0");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setDouble(1, multiplier);
			sql(statement.toString());
			statement.executeUpdate();

		} catch (Exception e) {
			error(e.getMessage());

		} finally {
			close(connection, statement);
		}
	}

	public Collection<TransactionsModel> findTransactionsTerminated() {
		List<TransactionsModel> items = null;
		ResultSet resultSet = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where status = 'terminated'");
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			sql(statement.toString());
			resultSet = statement.executeQuery();

			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {
					TransactionsModel transactionsModel = new TransactionsModel(resultSet.getLong("id"),
							resultSet.getLong("application_form_id"), resultSet.getDouble("total_amount_paid"),
							resultSet.getDouble("remaining_balance"), resultSet.getString("subscription_date"),
							resultSet.getString("subscription_date_end"),
							Status.findString(resultSet.getString("status")), resultSet.getString("due_date_payment"),
							resultSet.getString("due_date_termination"), resultSet.getDouble("currency_coin_qty"),
							resultSet.getDouble("sum_assurance"), resultSet.getInt("claims"),
							resultSet.getDouble("claimable_assurance"), resultSet.getLong("users_id"));
					items.add(transactionsModel);

				}
			}

		} catch (Exception e) {
			error(e.getMessage());
		}
		return items;
	}

	public Collection<TransactionsModel> findTransactionByApplicationId(Long applicationId) {
		ResultSet resultSet = null;
		List<TransactionsModel> items = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where application_form_id= ? ");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, applicationId);
			sql(statement.toString());

			resultSet = statement.executeQuery();

			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {
					TransactionsModel transactionsModel = new TransactionsModel(resultSet.getLong("id"),
							resultSet.getLong("application_form_id"), resultSet.getDouble("total_amount_paid"),
							resultSet.getDouble("remaining_balance"), resultSet.getString("subscription_date"),
							resultSet.getString("subscription_date_end"),
							Status.findString(resultSet.getString("status")), resultSet.getString("due_date_payment"),
							resultSet.getString("due_date_termination"), resultSet.getDouble("currency_coin_qty"),
							resultSet.getDouble("sum_assurance"), resultSet.getInt("claims"),
							resultSet.getDouble("claimable_assurance"));
					items.add(transactionsModel);

				}
			}

		} catch (Exception e) {
			error(e.getMessage());

		}
		return items;
	}

	public Collection<TransactionsModel> findTransactionsWithClaims() {
		ResultSet resultSet = null;
		List<TransactionsModel> items = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where claims !=0  ");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());

			sql(statement.toString());

			resultSet = statement.executeQuery();

			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {
					TransactionsModel transactionsModel = new TransactionsModel(resultSet.getLong("id"),
							resultSet.getLong("application_form_id"), resultSet.getDouble("total_amount_paid"),
							resultSet.getDouble("remaining_balance"), resultSet.getString("subscription_date"),
							resultSet.getString("subscription_date_end"),
							Status.findString(resultSet.getString("status")), resultSet.getString("due_date_payment"),
							resultSet.getString("due_date_termination"), resultSet.getDouble("currency_coin_qty"),
							resultSet.getDouble("sum_assurance"), resultSet.getInt("claims"),
							resultSet.getDouble("claimable_assurance"));
					items.add(transactionsModel);

				}
			}

		} catch (Exception e) {
			error(e.getMessage());

		}
		return items;
	}

	public Collection<TransactionsModel> getUsersName() {
		List<TransactionsModel> items = null;

		ResultSet resultSet = null;

		StringBuilder sql = new StringBuilder(
				"select u.first_name, u.last_name, u.email from transactions as  t inner join public.users  as u on t.users_id  = u.id ;");
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			sql(statement.toString());
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {
					TransactionsModel transactionsModel = new TransactionsModel(resultSet.getString("first_name"),
							resultSet.getString("last_name"), resultSet.getString("email"));
					items.add(transactionsModel);

				}
			}

		} catch (Exception e) {
			error(e.getMessage());
		} finally {
			close(connection, statement);
		}
		return items;
	}

	public Collection<TransactionsModel> getUsersInfo(Long id) {
		List<TransactionsModel> items = null;

		ResultSet resultSet = null;

		StringBuilder sql = new StringBuilder(
				"select u.first_name, u.last_name, u.email from transactions as t inner join public.users as u on t.users_id  = u.id where t.id = ?");
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, id);
			sql(statement.toString());
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {
					TransactionsModel transactionsModel = new TransactionsModel(resultSet.getString("first_name"),
							resultSet.getString("last_name"), resultSet.getString("email"));
					items.add(transactionsModel);

				}
			}

		} catch (Exception e) {
			error(e.getMessage());
		} finally {
			close(connection, statement);
		}
		return items;
	}

	public Collection<TransactionsModel> getJsonPolicy() {
		List<TransactionsModel> items = null;

		ResultSet resultSet = null;
		StringBuilder sql = new StringBuilder("select p.json_policy from " + this.tableName
				+ " as  t inner join public.policy as p on t.plans_category = p.plans_category where t.claims  !=0");
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			sql(statement.toString());

			resultSet = statement.executeQuery();

			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {
					TransactionsModel transactionsModel = new TransactionsModel(resultSet.getString("json_policy"));
					items.add(transactionsModel);
				}
			}
		} catch (Exception e) {

			error(e.getMessage());
		} finally {
			close(connection, statement);
		}
		return items;
	}

	public Collection<TransactionsModel> findTransactionsById(Long id) {
		ResultSet resultSet = null;
		List<TransactionsModel> items = null;

		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where id= ? ");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, id);
			sql(statement.toString());

			resultSet = statement.executeQuery();

			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {
					TransactionsModel transactionsModel = new TransactionsModel(resultSet.getLong("id"),
							resultSet.getLong("application_form_id"), resultSet.getDouble("total_amount_paid"),
							resultSet.getDouble("remaining_balance"), resultSet.getString("subscription_date"),
							resultSet.getString("subscription_date_end"),
							Status.findString(resultSet.getString("status")), resultSet.getString("due_date_payment"),
							resultSet.getString("due_date_termination"), resultSet.getDouble("currency_coin_qty"),
							resultSet.getDouble("sum_assurance"), resultSet.getInt("claims"),
							resultSet.getDouble("claimable_assurance"), resultSet.getLong("users_id"));
					items.add(transactionsModel);

				}
			}

		} catch (Exception e) {
			error(e.getMessage());

		}
		return items;
	}

	public Collection<TransactionsModel> findTransactionsActive() {
		ResultSet resultSet = null;
		List<TransactionsModel> items = null;
		String status = "Active";
		StringBuilder sql = new StringBuilder("select * from " + this.tableName + " where status = ? ");

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, status);
			sql(statement.toString());

			resultSet = statement.executeQuery();

			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {
					TransactionsModel transactionsModel = new TransactionsModel(resultSet.getLong("id"),
							resultSet.getLong("application_form_id"), resultSet.getDouble("total_amount_paid"),
							resultSet.getDouble("remaining_balance"), resultSet.getString("subscription_date"),
							resultSet.getString("subscription_date_end"),
							Status.findString(resultSet.getString("status")), resultSet.getString("due_date_payment"),
							resultSet.getString("due_date_termination"), resultSet.getDouble("currency_coin_qty"),
							resultSet.getDouble("sum_assurance"), resultSet.getInt("claims"),
							resultSet.getDouble("claimable_assurance"), resultSet.getLong("users_id"));
					items.add(transactionsModel);

				}
			}

		} catch (Exception e) {
			error(e.getMessage());

		}
		return items;
	}

	public Collection<TransactionsModel> list() {
		ResultSet resultSet = null;
		List<TransactionsModel> items = null;
		Connection connection = null;
		PreparedStatement statement = null;
		StringBuilder sql = new StringBuilder("select * from " + this.tableName);

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			sql(statement.toString());
			resultSet = statement.executeQuery();

			if (resultSet != null) {
				items = new ArrayList<>();
				while (resultSet.next()) {
					TransactionsModel transactionsModel = new TransactionsModel(resultSet.getLong("id"),
							resultSet.getLong("application_form_id"), resultSet.getDouble("total_amount_paid"),
							resultSet.getDouble("remaining_balance"), resultSet.getString("subscription_date"),
							resultSet.getString("subscription_date_end"),
							Status.findString(resultSet.getString("status")), resultSet.getString("due_date_payment"),
							resultSet.getString("due_date_termination"), resultSet.getDouble("currency_coin_qty"),
							resultSet.getDouble("sum_assurance"), resultSet.getInt("claims"),
							resultSet.getDouble("claimable_assurance"), resultSet.getLong("users_id"));
					items.add(transactionsModel);

				}
			}

		} catch (Exception e) {
			error(e.getMessage());

		}
		return items;
	}

	public void checkTermination() {

		Connection connection = null;
		PreparedStatement statement = null;
		StringBuilder sql = new StringBuilder("update " + this.tableName
				+ " set status = ? where  date_part('year', due_date_termination ::date) <= date_part('year', current_timestamp::date ) and date_part('month', due_date_termination::date) + 2 <= date_part('month', current_timestamp::date)  and date_part('day',due_date_termination::date) <= date_part('day', current_timestamp::date)");
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, Status.TERMINATED.getTitle());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}
	}

	public void updateSumAssurance(Double multiplier) {
		Connection connection = null;
		PreparedStatement statement = null;
		StringBuilder sql = new StringBuilder("update " + this.tableName + " set sum_assurance= currency_coin_qty * ?");
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setDouble(1, multiplier);

			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, statement);
		}
	}

}
