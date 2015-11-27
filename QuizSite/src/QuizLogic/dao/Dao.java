package QuizLogic.dao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract  class Dao <T> {
	
	public static Logger log = LogManager.getLogger("DAO");
	
	protected void setParameters(PreparedStatement statement, List<Object> parameterList) throws SQLException {
		
		int i = 0;
		for (Object parameter : parameterList) statement.setObject(++i, parameter);
					
	}
	
	protected int updateOrDelete(Connection connection, String stringSQL, List<Object> parameterList) {
		
		int value = 0;
		try (
			PreparedStatement statement = connection.prepareStatement(stringSQL))				
			{
			
				setParameters(statement, parameterList);
				value = statement.executeUpdate();
				log.debug("Update Or Delete {}", stringSQL);
											
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		return value;
	}
	
	protected int insert(Connection connection, String stringSQL, List<Object> parameterList) {
		
		int value = 0;
		try (
			PreparedStatement statement = connection.prepareStatement(stringSQL, Statement.RETURN_GENERATED_KEYS))				
			{
				setParameters(statement, parameterList);
				value = statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {value = rs.getInt(1);}
				log.debug("INSERT INTO users (login, password, name) VALUES ({}, {}, {})", parameterList.toArray());			
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		return value;
	}

	protected abstract T getObject(ResultSet rs) throws SQLException;
		
	protected List<T> getListObject(ResultSet rs) throws SQLException  {
	
		List<T> list = new ArrayList<T>();
		while (rs.next()) {list.add(getObject(rs));};
		return list;	
	}

		
	
	protected T selectOne(String stringSQL, List<Object> parameterList) {
		
		T value = null;
		
		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(stringSQL))				
			{	

			setParameters(statement, parameterList);
			ResultSet rs = statement.executeQuery();
			if (!rs.next()) 
				{connection.commit();
				return null;};
			value = getObject(rs);
			connection.commit();
			log.trace(stringSQL, parameterList);

			} catch (SQLException | IOException | PropertyVetoException e) {

				e.printStackTrace();
			}
		
		return value; 
			
	}
	
	protected List<T> selectList(String stringSQL, List<Object> parameterList) {
		
		List<T> value = null;
		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(stringSQL))				
			{	
				setParameters(statement, parameterList);
				value = getListObject(statement.executeQuery());
				connection.commit();
							
			} catch (SQLException | IOException | PropertyVetoException e) {
				
				e.printStackTrace();
			}
		
		return value; 
			
	}
	
	protected int update(String stringSQL, List<Object> parameterList) {
		
		int value = 0;
		try (Connection connection = DataSource.getConnection())				
			{
				value = updateOrDelete(connection, stringSQL, parameterList);
				connection.commit();
							
			} catch (SQLException | IOException | PropertyVetoException e) {
				
				e.printStackTrace();
			}
		return value;
	}
	
	protected int delete(String stringSQL, List<Object> parameterList) {
		
		int value = 0;
		try (Connection connection = DataSource.getConnection())				
			{
				value = updateOrDelete(connection, stringSQL, parameterList);
				connection.commit();
							
			} catch (SQLException | IOException | PropertyVetoException e) {
				
				e.printStackTrace();
			}
		return value;
	}
	
	protected int insert(String stringSQL, List<Object> parameterList) {

		int key = 0;
		try (Connection connection = DataSource.getConnection())				
			{
				
				key  = updateOrDelete(connection, stringSQL, parameterList);
				connection.commit();
						
							
			} catch (SQLException | IOException | PropertyVetoException e) {
				
				e.printStackTrace();
			}
		
		return key;
	}
	
	
	
	public T selectOneByID(String stringSQL, final int id) { 
		return selectOne(stringSQL, Arrays.asList((Object) id));}; 

	public List<T> selectAll(String stringSQL) {
		return selectList(stringSQL, new ArrayList<Object>(0));};

	public int deleteByID(String stringSQL, final int id) {
		return delete(stringSQL, Arrays.asList((Object) id));};
		
		
	
};

interface DaoImpl<T> {
	
	public static final String SELECT_ALL   = "";
	public static final String SELECT_BY_ID = "";
	public static final String DELETE_BY_ID = "";
	public static final String UPDATE_BY_ID = "";
	public static final String INSERT_ONE   = "";
	
	public T selectOneByID(int id); 

	public List<T> selectAll();

	public int deleteByID (int id) throws DaoDeleteException;
	
	public int insertByID (T inserted) throws DaoInsertException ;
	
	public int updateByID(T updated) throws DaoUpdateException ;
	
}

class DaoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public DaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}

class DaoInsertException extends DaoException {
	private static final long serialVersionUID = -6699850628917417928L;
	
	public DaoInsertException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}

class DaoUpdateException extends DaoException {
	private static final long serialVersionUID = 964192562353996435L;
		
	public DaoUpdateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
		
}

class DaoDeleteException extends DaoException {
	private static final long serialVersionUID = 964192562353996436L;
		
	public DaoDeleteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
		
}


	
