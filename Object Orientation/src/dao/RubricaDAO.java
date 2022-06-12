package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Contatto;
import model.Rubrica;

public interface RubricaDAO {
	
	public ArrayList<Contatto> loadContatti(String nomeRubrica);
	public void addContatto(String nomeRubrica, String nome, String secondonome, String cognome,
            String numMobile, String numFisso, String via, String citta, String nazione, String cap) throws SQLException;
}
