package maintest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.ConnessioneDatabase;

//Classe Main di test per il corretto funzionamento della connessione App - DB
public class Main {
	private static Connection connection;
	public static void main(String[] args) {
		System.out.println("Connessione aperta...");
		// TODO Auto-generated method stub
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement comandodajava = connection.prepareStatement(
					"INSERT INTO RUBRICA(utente_id) VALUES ('Utente_ciao')");
			System.out.println("Query preparata...");
			//Operazione DML 
			// Ciao da commit
			// Hey, ciao da GitHub
			comandodajava.executeUpdate();
			System.out.println("Query eseguita...");
			connection.close();
			System.out.println("Connessione chiusa!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
