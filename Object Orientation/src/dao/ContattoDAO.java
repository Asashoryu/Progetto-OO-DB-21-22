package dao;

import java.sql.SQLException;

public interface ContattoDAO {
	public void addIndirizzo(String via, String citt�, String nazione, String cap, String descrizione, int ido) throws SQLException;
	public void addTelefono(String numero, String descrizione, int id_contatto) throws SQLException;
	public void addEmail(String indirizzoEmail, String descrizione, int id_contatto) throws SQLException;
}
