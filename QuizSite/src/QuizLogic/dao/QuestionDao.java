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

import QuizLogic.quiz.Question;

public class QuestionDao extends Dao<Question> implements DaoImpl<Question>{
	
	public static final String SELECT_ALL         = "SELECT id as question_id, description as question_description, quiz_id as question_quiz_id FROM questions";
	public static final String SELECT_BY_ID       = "SELECT id as question_id, description as question_description, quiz_id as question_quiz_id FROM questions WHERE id = ?";
	public static final String SELECT_BY_QUIZ_ID  = "SELECT id as question_id, description as question_description, quiz_id as question_quiz_id FROM questions WHERE quiz_id = ?";
	public static final String DELETE_BY_ID       = "DELETE FROM questions WHERE id = ?";
	public static final String UPDATE_BY_ID       = "UPDATE questions SET description = ?, quiz_id = ? WHERE id = ?";
	public static final String INSERT_ONE         = "INSERT INTO questions (description, quiz_id) VALUES (?, ?)";
	public static final String CHECK_INSERT_ONE   = "SELECT id as question_id FROM questions WHERE description = ? AND quiz_id = ?";
	public static final String CHECK_UPDATE_ONE   = "SELECT id as question_id FROM questions WHERE description = ? AND quiz_id = ? AND id <> ?";
	public static final String CHECK_DELETEE_ONE  = "SELECT id as answer_id FROM answers WHERE question_id = ?";

	@Override
	public Question selectOneByID(int id) {
		return selectOneByID(SELECT_BY_ID, id);
	}

	@Override
	public List<Question> selectAll() {
		return selectAll(SELECT_ALL);
	}

	public List<Question> selectByQuestionId(int id) {
		return selectList(SELECT_BY_QUIZ_ID, Arrays.asList((Object) id));
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
				throw new  DaoDeleteException("Answers of this question are exists.");};

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
	public int insertByID(Question inserted) throws DaoInsertException {
		int value = 0;

		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement    = connection.prepareStatement(CHECK_INSERT_ONE))				
			{	

			statement.setString(1, inserted.description);
			statement.setInt(2, inserted.quiz_id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) 
				{connection.rollback();
				connection.close();
				throw new  DaoInsertException("Question with such description (" + inserted.description + ")  exists");};

			List<Object> parameterList = new ArrayList<Object>(2);
			parameterList.add(inserted.description);
			parameterList.add(inserted.quiz_id);
			
			value = insert(connection, INSERT_ONE, parameterList);
			inserted.id = value;

			connection.commit();

			} catch (SQLException | IOException | PropertyVetoException e) {
				e.printStackTrace();
			}

		return value; 
	}

	@Override
	public int updateByID(Question updated) throws DaoUpdateException {
		int value = 0;

		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement    = connection.prepareStatement(CHECK_UPDATE_ONE))				
			{	

			statement.setString(1, updated.description);
			statement.setInt(2, updated.quiz_id);
			statement.setInt(3, updated.id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) 
				{connection.rollback();
				connection.close();
				throw new  DaoUpdateException("Answer with such description (" + updated.description + ")  exists");};

			List<Object> parameterList = new ArrayList<Object>(2);

			parameterList.add(updated.description);
			parameterList.add(updated.quiz_id);
			
			value = updateOrDelete(connection, UPDATE_BY_ID, parameterList);
			
			connection.commit();

			} catch (SQLException | IOException | PropertyVetoException e) {
				e.printStackTrace();
			}

		return value; 
	}

	@Override
	protected Question getObject(ResultSet rs) throws SQLException {
		
		Question question     = new Question(); 
		question.id           = rs.getInt("question_id");
		question.description  = rs.getString("question_description");
		question.quiz_id  = rs.getInt("question_quiz_id");
				
		return question;
	}

}
