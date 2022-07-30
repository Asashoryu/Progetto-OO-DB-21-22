package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Contatto;
import model.Rubrica;

public interface RubricaDAO {
	
	void loadContatti(String nomeRubrica, ArrayList<Contatto> contatti);
	public Connection apriConnessione();
	public Connection getConnessione();
	public int startTransazione(Connection connTransazione) throws SQLException;
	void addContatto(String nomeRubrica, String nome, String secondonome, String cognome, String numMobile,
			String numFisso, String via, String citta, String nazione, String cap, String indirizzoEmail,
			String descrEmail, int id, Connection connessione) throws SQLException;
	void addIndirizzo(String via, String città, String nazione, String cap, String descrizione, int id, Connection connessione) throws SQLException;
	void addTelefono(String numero, String descrizione, int id_contatto, Connection connessione) throws SQLException;
	void addEmail(String indirizzoEmail, String descrizione, int id_contatto, Connection connessione)
			throws SQLException;
}
