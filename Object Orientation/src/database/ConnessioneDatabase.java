package database;

import java.sql.*;

/**
 * Gestisce la connessione al DB Postgres
 */
public class ConnessioneDatabase {
	
	/** Instance. */
	private static ConnessioneDatabase instance;
	
	/** Connection. */
	private Connection connection = null;
	
	/** Nome utente. */
	private String nomeutente = "postgres";
	
	/** Password. */
	private String password = "1234";
	
	/** Url. */
	private String url = "jdbc:postgresql://localhost:5432/Rubrica";
	
	/** Driver. */
	private String driver = "org.postgresql.Driver";
	
	/**
	 * Costruttore che istanzia una nuova connessione al DB.
	 *
	 * @throws SQLException SQL exception
	 */
	public ConnessioneDatabase() throws SQLException {
		try {
			//Estrae l'oggetto driver dalla stringa
			Class.forName(driver);
			connection = DriverManager.getConnection(url, nomeutente, password);
		} catch (ClassNotFoundException ex) {
			System.out.println("Connessione al DB fallita : " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Ritorna la connessione al DB.
	 *
	 * @return connection
	 */
	public Connection getConnection() {
			return connection;
	}
	
	/**
	 * Ritorna una nuova connessione al DB con accesso statico.
	 *
	 * @return Singola istanza di ConnessioneDatabase
	 * @throws SQLException SQL exception
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
