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

import QuizLogic.quiz.Answer;

public class AnswerDao extends Dao<Answer> implements DaoImpl<Answer>{
	
	public static final String SELECT_ALL         = "SELECT id as answer_id, description as answer_description, question_id as question_id, isRight as answer_isRight FROM answers";
	public static final String SELECT_BY_ID       = "SELECT id as answer_id, description as answer_description, question_id as question_id, isRight as answer_isRight FROM answers WHERE id = ?";
	public static final String SELECT_BY_QUEST_ID = "SELECT id as answer_id, description as answer_description, question_id as question_id, isRight as answer_isRight FROM answers WHERE question_id = ?";
	public static final String DELETE_BY_ID       = "DELETE FROM answers WHERE id = ?";
	public static final String UPDATE_BY_ID       = "UPDATE answers SET description = ?, question_id = ?, isRight = ? WHERE id = ?";
	public static final String INSERT_ONE         = "INSERT INTO answers (description, question_id, isRight) VALUES (?, ?, ?)";
	public static final String CHECK_INSERT_ONE   = "SELECT id as answer_id FROM answers WHERE description = ? AND question_id = ?";
	public static final String CHECK_UPDATE_ONE   = "SELECT id as answer_id FROM answers WHERE description = ? AND question_id = ? AND id <> ?";
	//public static final String CHECK_DELETE_ONE = "SELECT id as answer_id FROM answers WHERE answer_id = ?";
	
	@Override
	public Answer selectOneByID(int id) {
		return selectOneByID(SELECT_BY_ID, id);
	}

	@Override
	public List<Answer> selectAll() {
		return selectAll(SELECT_ALL);
	}
	
	public List<Answer> selectByQuestionId(int id) {
		return selectList(SELECT_BY_QUEST_ID, Arrays.asList((Object) id));
	}

	@Override
	public int deleteByID(int id) throws DaoDeleteException {
		return deleteByID(DELETE_BY_ID, id);
	}

	@Override
	public int insertByID(Answer inserted) throws DaoInsertException {
		int value = 0;

		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement    = connection.prepareStatement(CHECK_INSERT_ONE))				
			{	

			statement.setString(1, inserted.description);
			statement.setInt(2, inserted.question_id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) 
				{connection.rollback();
				connection.close();
				throw new  DaoInsertException("Answer with such description (" + inserted.description + ")  exists");};

			List<Object> parameterList = new ArrayList<Object>(3);
			parameterList.add(inserted.description);
			parameterList.add(inserted.question_id);
			parameterList.add(inserted.isRight);

			value = insert(connection, INSERT_ONE, parameterList);
			inserted.id = value;

			connection.commit();

			} catch (SQLException | IOException | PropertyVetoException e) {
				e.printStackTrace();
			}

		return value; 
	}

	@Override
	public int updateByID(Answer updated) throws DaoUpdateException {
		int value = 0;

		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement    = connection.prepareStatement(CHECK_UPDATE_ONE))				
			{	

			statement.setString(1, updated.description);
			statement.setInt(2, updated.question_id);
			statement.setInt(3, updated.id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) 
				{connection.rollback();
				connection.close();
				throw new  DaoUpdateException("Answer with such description (" + updated.description + ")  exists");};

			List<Object> parameterList = new ArrayList<Object>(3);

			parameterList.add(updated.description);
			parameterList.add(updated.question_id);
			parameterList.add(updated.isRight);

			value = updateOrDelete(connection, UPDATE_BY_ID, parameterList);
			
			connection.commit();

			} catch (SQLException | IOException | PropertyVetoException e) {
				e.printStackTrace();
			}

		return value; 
	}

	@Override
	protected Answer getObject(ResultSet rs) throws SQLException {
		Answer answer       = new Answer(); 
		answer.id           = rs.getInt("answer_id");
		answer.description  = rs.getString("answer_description");
		answer.question_id  = rs.getInt("question_id");
		answer.isRight      = rs.getBoolean("answer_isRight");
		
		return answer;
	}

}
