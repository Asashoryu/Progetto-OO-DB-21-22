package database;

import java.sql.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnessioneDatabase.
 */
public class ConnessioneDatabase {
	
	/** The instance. */
	private static ConnessioneDatabase instance;
	
	/** The connection. */
	private Connection connection = null;
	
	/** The nomeutente. */
	private String nomeutente = "postgres";
	
	/** The password. */
	private String password = "1234";
	
	/** The url. */
	private String url = "jdbc:postgresql://localhost:5432/Rubrica";
	
	/** The driver. */
	private String driver = "org.postgresql.Driver";
	
	/**
	 * Instantiates a new connessione database.
	 *
	 * @throws SQLException the SQL exception
	 */
	public ConnessioneDatabase() throws SQLException {
		try {
			//Estrae l'oggetto driver dalla stringa
			Class.forName(driver);
			connection = DriverManager.getConnection(url, nomeutente, password);
		} catch (ClassNotFoundException ex) {
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
			return connection;
	}
	
	/**
	 * Gets the single instance of ConnessioneDatabase.
	 *
	 * @return single instance of ConnessioneDatabase
	 * @throws SQLException the SQL exception
	 */
	public static ConnessioneDatabase getInstance() throws SQLException {
		if (instance == null) {
			//Se la connessione non è stata aperta, la apre
			instance = new ConnessioneDatabase();
		} else if (instance.getConnection().isClosed()) {
			//Se la connessione è stata chiusa, la riapre
			instance = new ConnessioneDatabase();
		}
		return instance;
	}
}
