package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.SistemaDAO;
import implementazionedao.SistemaImplementazionePostgresDAO;

public class Sistema {
	
	private ArrayList<Rubrica> rubriche;
	
	// Costruttore di sistema che carica tutte le rubriche dal DataBase in automatico
	public Sistema() {
		SistemaDAO sistemaPosgr = new SistemaImplementazionePostgresDAO();
		rubriche=sistemaPosgr.loadRubriche();
	}
	
	public ArrayList<Rubrica> getRubriche() {
		return rubriche;
	}
	
	public void updateRubrica(Rubrica rubricaSelezionata, String nuovoNome) throws SQLException {
		SistemaDAO sistemaPosgr = new SistemaImplementazionePostgresDAO();
		try {
			sistemaPosgr.updateRubrica(rubricaSelezionata.getNome(), nuovoNome);
			rubricaSelezionata.setNome(nuovoNome);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public Rubrica addRubrica(String nomeRubrica) throws SQLException {
		SistemaDAO sistemaPosgr = new SistemaImplementazionePostgresDAO();
		try {
			sistemaPosgr.addRubrica(nomeRubrica);
			Rubrica rubrica = new Rubrica(nomeRubrica);
			return rubrica;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public void deleteRubrica(Rubrica rubricaSelezionata) throws SQLException {
		SistemaDAO sistemaPosgr = new SistemaImplementazionePostgresDAO();
		try {
			sistemaPosgr.deleteRubrica(rubricaSelezionata.getNome());
			getRubriche().remove(rubricaSelezionata);
		} catch (SQLException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
}
