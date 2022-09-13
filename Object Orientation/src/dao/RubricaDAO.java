package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Contatto;
import model.Gruppo;

public interface RubricaDAO {
	public void loadContatti(String nomeRubrica, ArrayList<Contatto> contatti);
	public void loadGruppi(String nomeRubrica, ArrayList<Contatto> contatti, ArrayList<Gruppo> gruppi);
	public Connection apriConnessione();
	public Connection getConnessione();
	public int generaContattoID(Connection connTransazione) throws SQLException;
	public void addInfoContatto(String nomeRubrica, String nome, String secondonome, String cognome, String numMobile,
			String numFisso, String via, String citta, String nazione, String cap, int id, Connection connessione) throws SQLException;
	public void changeInfoContatto(String nomeRubrica, String nome, String secondonome, String cognome,
			String numMobile, String numFisso, String via, String citta, String nazione, String cap,
			int vecchioContattoId, Connection connessione) throws SQLException;
	public void addImmagine(String pathImmagine, int id, Connection connTransazione) throws SQLException;
	public void addIndirizzo(String via, String città, String nazione, String cap, String descrizione, int id, Connection connessione) throws SQLException;
	public void addTelefono(String numero, String descrizione, int id_contatto, Connection connessione) throws SQLException;
	public void addEmail(String indirizzoEmail, String descrizione, int id_contatto, Connection connessione)
			throws SQLException;
	public void loadAccountContatto(Contatto contatto, Connection connTransazione) throws SQLException;
	public void deleteContatto(int codiceContatto, Connection connessione) throws SQLException;
	public void addInfoGruppo(String nomeRubrica, Gruppo nuovoGruppo, Connection connessione) throws Exception;
	public void deleteGruppo(int codiceGruppo, Connection connessione) throws SQLException;
	public void changeInfoGruppo (String nomeRubrica, Gruppo nuovoGruppo, Connection connessione) throws SQLException;
}
