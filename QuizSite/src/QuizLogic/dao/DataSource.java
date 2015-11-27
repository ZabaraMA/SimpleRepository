package QuizLogic.dao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataSource {

    private static DataSource datasource;
    private BasicDataSource ds;
    private static ThreadLocal<Connection> localConnection = new ThreadLocal<Connection>();
    public static Logger log = LogManager.getLogger(DataSource.class);

    private DataSource() throws IOException, SQLException, PropertyVetoException {
        ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("443484");
        ds.setUrl("jdbc:mysql://localhost/test");
       
        // the settings below are optional -- dbcp can work with defaults
        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(180);
    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getPoolConnection(boolean autoCommit) throws SQLException {
    	Connection conn = this.ds.getConnection();
    	conn.setAutoCommit(autoCommit);
    	conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return conn;
    }
    
    public static Connection getConnection() throws SQLException, IOException, PropertyVetoException {
    	
    	if (localConnection.get() == null) {
    		localConnection.set(getInstance().getPoolConnection(false));
    		log.info("Connect is created!");
    	}
    	
    	return localConnection.get();
    	
    }

}
