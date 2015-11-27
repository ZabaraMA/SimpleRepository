package QuizLogic.dao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

import QuizLogic.quiz.User;

public class UserDao extends Dao<User> implements DaoImpl<User> {

	public static final String SELECT_ALL       = "SELECT id as user_id, login as user_login, password as user_password, name as user_name FROM users";
	public static final String SELECT_BY_ID     = "SELECT id as user_id, login as user_login, password as user_password, name as user_name FROM users WHERE id = ?";
	public static final String DELETE_BY_ID     = "DELETE FROM users WHERE id = ?";
	public static final String UPDATE_BY_ID     = "UPDATE users SET login = ?, password = ?, name = ? WHERE id = ?";
	public static final String INSERT_ONE       = "INSERT INTO users (login, password, name) VALUES (?, ?, ?)";
	public static final String CHECK_INSERT_ONE = "SELECT id as user_id FROM users WHERE login = ? OR name = ?";
	public static final String CHECK_UPDATE_ONE = "SELECT id as user_id FROM users WHERE (login = ? OR name = ?) AND id <> ?";
	
	@Override
	protected User getObject(ResultSet rs) throws SQLException {
		
		User user      = new User(); 
		user.id        = rs.getInt("user_id");
		user.name      = rs.getString("user_name");
		user.login     = rs.getString("user_login");
		user.password  = rs.getString("user_password");
		
		return user;
	}

	@Override
	public User selectOneByID(int id) {
		return selectOneByID(SELECT_BY_ID, id);
	}

	@Override
	public List<User> selectAll() {
		return selectAll(SELECT_ALL);
	}

	@Override
	public int deleteByID(int id) {
		return deleteByID(DELETE_BY_ID, id);
	}

	@Override
	public int insertByID(User inserted) throws DaoInsertException {

		int value = 0;

		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement    = connection.prepareStatement(CHECK_INSERT_ONE))				
			{	

			statement.setString(1, inserted.login);
			statement.setString(2, inserted.name);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) 
				{connection.rollback();
				connection.close();
				throw new  DaoInsertException("User with such login (" + inserted.login + ") or name (" + inserted.name + ")  exists");};

			List<Object> parameterList = new ArrayList<Object>(3);

			parameterList.add(inserted.login);
			parameterList.add(inserted.password);
			parameterList.add(inserted.name);

			value = insert(connection, INSERT_ONE, parameterList);
			inserted.id = value;

			connection.commit();

			} catch (SQLException | IOException | PropertyVetoException e) {
				e.printStackTrace();
			}

		return value; 


	}

	@Override
	public int updateByID(User updated) throws DaoUpdateException {
		
		int value = 0;

		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement    = connection.prepareStatement(CHECK_UPDATE_ONE))				
			{	

			statement.setString(1, updated.login);
			statement.setString(2, updated.name);
			statement.setInt(3, updated.id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) 
				{connection.rollback();
				connection.close();
				throw new  DaoUpdateException("User with such login (" + updated.login + ") or name (" + updated.name + ")  exists");};

			List<Object> parameterList = new ArrayList<Object>(4);

			parameterList.add(updated.login);
			parameterList.add(updated.password);
			parameterList.add(updated.name);
			parameterList.add(updated.id);

			value = updateOrDelete(connection, UPDATE_BY_ID, parameterList);
			
			connection.commit();

			} catch (SQLException | IOException | PropertyVetoException e) {
				e.printStackTrace();
			}

		return value; 
	}

}
