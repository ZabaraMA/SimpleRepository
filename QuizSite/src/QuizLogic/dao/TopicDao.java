package QuizLogic.dao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import QuizLogic.quiz.Topic;

public class TopicDao extends Dao<Topic> implements DaoImpl<Topic>{
	
	public static final String SELECT_ALL       = "SELECT id as topic_id, name as topic_name FROM topics";
	public static final String SELECT_BY_ID     = "SELECT id as topic_id, name as topic_name FROM topics WHERE id = ?";
	public static final String DELETE_BY_ID     = "DELETE FROM topics WHERE id = ?";
	public static final String UPDATE_BY_ID     = "UPDATE topics SET name = ? WHERE id = ?";
	public static final String INSERT_ONE       = "INSERT INTO topics (name) VALUES (?)";
	public static final String CHECK_INSERT_ONE = "SELECT id as topic_id FROM topics WHERE name = ?";
	public static final String CHECK_UPDATE_ONE = "SELECT id as topic_id FROM topics WHERE name = ? AND id <> ?";
	public static final String CHECK_DELETE_ONE = "SELECT id as topic_id FROM quizes WHERE topic_id = ?";
	

	@Override
	public Topic selectOneByID(int id) {
		return selectOneByID(SELECT_BY_ID, id);
	}

	@Override
	public List<Topic> selectAll() {
		return selectAll(SELECT_ALL);
	}

	@Override
	public int deleteByID(int id) throws DaoDeleteException {
		int value = 0;

		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement    = connection.prepareStatement(CHECK_DELETE_ONE))				
			{	

			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) 
				{connection.rollback();
				connection.close();
				throw new  DaoDeleteException("Quizes of this topic are exists.");};

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
	public int insertByID(Topic inserted) throws DaoInsertException {
		int value = 0;

		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement    = connection.prepareStatement(CHECK_INSERT_ONE))				
			{	

			statement.setString(1, inserted.name);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) 
				{connection.rollback();
				connection.close();
				throw new  DaoInsertException("Topic with such name (" + inserted.name + ")  exists");};

			List<Object> parameterList = new ArrayList<Object>(1);
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
	public int updateByID(Topic updated) throws DaoUpdateException {
		int value = 0;

		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement    = connection.prepareStatement(CHECK_UPDATE_ONE))				
			{	

			statement.setString(1, updated.name);
			statement.setInt(2, updated.id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) 
				{connection.rollback();
				connection.close();
				throw new  DaoUpdateException("Topic with such name (" + updated.name + ")  exists");};

			List<Object> parameterList = new ArrayList<Object>(2);

			parameterList.add(updated.name);
			parameterList.add(updated.id);

			value = updateOrDelete(connection, UPDATE_BY_ID, parameterList);
			
			connection.commit();

			} catch (SQLException | IOException | PropertyVetoException e) {
				e.printStackTrace();
			}

		return value; 
	}

	@Override
	protected Topic getObject(ResultSet rs) throws SQLException {
		Topic topic      = new Topic(); 
		topic.id        = rs.getInt("topic_id");
		topic.name      = rs.getString("topic_name");
			
		return topic;
	}

}
