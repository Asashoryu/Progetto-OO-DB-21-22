package database;

import java.sql.*;

public class ConnessioneDatabase {
	
	private static ConnessioneDatabase instance;
	private Connection conn = null;
	private String nomeutente = "postgres";
	private String password = "admin";
	private String url = "jdbc:postgresql://localhost:5432/Rubrica";
	private String driver = "org.postgresql.Driver";
	
	class Connessione {
		public static void main(String args[]) throws Exception {
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, nomeutente, password);
				System.out.println("Connessione OK \n");
				conn.close();
			}
			catch (ClassNotFoundException e) {
				System.out.println("DB driver not found \n");
				System.out.println(e);
			}
			catch(SqlException e) {
				System.out.println("Connessione Fallita \n");
				System.out.println(e);
			}
		}
	}
}
