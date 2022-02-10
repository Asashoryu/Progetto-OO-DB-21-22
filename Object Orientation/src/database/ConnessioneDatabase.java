package database;

import java.sql.*;

public class ConnessioneDatabase {
	
	private static ConnessioneDatabase instance;
	private Connection connection = null;
	private String nomeutente = "postgres";
	private String password = "1234";
	private String url = "jdbc:postgresql://localhost:5432/Rubrica";
	private String driver = "org.postgresql.Driver";
	
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

	public Connection getConnection() {
			return connection;
	}
	
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
