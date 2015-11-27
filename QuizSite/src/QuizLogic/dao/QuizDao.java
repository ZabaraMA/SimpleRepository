package QuizLogic.dao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import QuizLogic.quiz.Quiz;

public class QuizDao extends Dao<Quiz> implements DaoImpl<Quiz> {
	
	public static final String SELECT_ALL         = "SELECT id as quiz_id, name as quiz_name, topic_id as topic_id FROM quizes";
	public static final String SELECT_BY_ID       = "SELECT id as quiz_id, name as quiz_name, topic_id as topic_id FROM quizes WHERE id = ?";
	public static final String SELECT_BY_TOP_ID   = "SELECT id as quiz_id, name as quiz_name, topic_id as topic_id FROM quizes WHERE topic_id = ?";
	public static final String DELETE_BY_ID       = "DELETE FROM quizes WHERE id = ?";
	public static final String UPDATE_BY_ID       = "UPDATE quizes SET name = ?, topic_id = ? WHERE id = ?";
	public static final String INSERT_ONE         = "INSERT INTO quizes (name, topic_id) VALUES (?, ?)";
	public static final String CHECK_INSERT_ONE   = "SELECT id as quiz_id FROM quizes WHERE name = ?";
	public static final String CHECK_UPDATE_ONE   = "SELECT id as quiz_id FROM quizes WHERE name = ? AND id <> ?";
	public static final String CHECK_DELETEE_ONE  = "SELECT id as question_id FROM questions WHERE quiz_id = ?";
	
	@Override
	public Quiz selectOneByID(int id) {
		return selectOneByID(SELECT_BY_ID, id);
	}

	@Override
	public List<Quiz> selectAll() {
		return selectAll(SELECT_ALL);
	}
	
	public List<Quiz> selectByTopicId(int id) {
		return selectList(SELECT_BY_TOP_ID, Arrays.asList((Object) id));
	}
	
	@Override
	public int deleteByID(int id) throws DaoDeleteException {
		int value = 0;

		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement    = connection.prepareStatement(CHECK_DELETEE_ONE))				
			{	

			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) 
				{connection.rollback();
				connection.close();
				throw new  DaoDeleteException("Questions of this quiz are exists.");};

			List<Object> parameterList = new ArrayList<Object>(1);
			parameterList.add(id);

			value = updateOrDelete(connection, DELETE_BY_ID, parameterList);
			
			connection.commit();

			} catch (SQLException | IOException | PropertyVetoException e) {
				e.printStackTrace();
			}

		return value; 
	}

	@Override
	public int insertByID(Quiz inserted) throws DaoInsertException {
		int value = 0;

		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement    = connection.prepareStatement(CHECK_INSERT_ONE))				
			{	

			statement.setString(1, inserted.name);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) 
				{connection.rollback();
				connection.close();
				throw new  DaoInsertException("Question with such name (" + inserted.name + ")  exists");};

			List<Object> parameterList = new ArrayList<Object>(2);
			parameterList.add(inserted.name);
			if (inserted.topic != null) {
				parameterList.add(inserted.topic.id);}
			else {parameterList.add(0);};
						
			value = insert(connection, INSERT_ONE, parameterList);
			inserted.id = value;

			connection.commit();

			} catch (SQLException | IOException | PropertyVetoException e) {
				e.printStackTrace();
			}

		return value; 
	}

	@Override
	public int updateByID(Quiz updated) throws DaoUpdateException {
		int value = 0;

		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement    = connection.prepareStatement(CHECK_UPDATE_ONE))				
			{	

			statement.setString(1, updated.name);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) 
				{connection.rollback();
				connection.close();
				throw new  DaoUpdateException("Question with such name (" + updated.name + ")  exists");};

			List<Object> parameterList = new ArrayList<Object>(2);

			parameterList.add(updated.name);
			if (updated.topic != null) {
				parameterList.add(updated.topic.id);}
			else {parameterList.add(0);};
			
			value = updateOrDelete(connection, UPDATE_BY_ID, parameterList);
			
			connection.commit();

			} catch (SQLException | IOException | PropertyVetoException e) {
				e.printStackTrace();
			}

		return value; 
	}
	

	@Override
	protected Quiz getObject(ResultSet rs) throws SQLException {
		
		Quiz quiz      = new Quiz(); 
		quiz.id        = rs.getInt("quiz_id");
		quiz.name      = rs.getString("quiz_name");
						
		return quiz;
	}

}
