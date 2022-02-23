package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.SistemaDAO;
import implementazionedao.SistemaImplementazionePostgresDAO;
import model.Rubrica;
import model.Sistema;

public class Controller {
	
	private Sistema sistema;
	// serve da puntatore alla rubrica che viene modificata nella home
	private Rubrica rubricaSelezionata;
	
	// Alla creazione del controller, sono caricate tutte le Rubriche
	public Controller() {
		sistema = new Sistema();
		loadRubriche();
	}
	
	public void loadRubriche() {
		SistemaDAO sistemaPosgr = new SistemaImplementazionePostgresDAO();
		sistema.setRubriche(sistemaPosgr.loadRubriche());
	}
	
	// Metodo usato dalla ComboBox del main. Ritorna un array di nomi delle
	// rubriche cui si riferiscono, enumerati nello stesso ordine degli oggetti,
	// quindi l'indice di un nome consente di recuperare l'oggetto rubrica
	public String[] getNomiRubriche(){
		String[] nomiRubriche = new String[sistema.getRubriche().size()];
		for(Rubrica r: sistema.getRubriche()) {
			nomiRubriche[sistema.getRubriche().indexOf(r)]=r.getNome();
		}
		return nomiRubriche;
	}
	
	public ArrayList<Rubrica> getRubriche(){
		return sistema.getRubriche();
	}
	
	//fissa la rubrica selezionata nella combobox per le successive operazioni di manipolazione
	public void setRubricaSelezionata(int indice) {
		rubricaSelezionata = sistema.getRubriche().get(indice);
	}
	
	public Rubrica getRubricaSelezionata() {
		return rubricaSelezionata;
	}
	
	public void updateRubrica(String nuovoNome) throws SQLException {
		
		SistemaDAO sistemaPosgr = new SistemaImplementazionePostgresDAO();
		try {
			//update nel DB
			sistemaPosgr.updateRubrica(rubricaSelezionata.getNome(), nuovoNome);
			//update in memoria
			rubricaSelezionata.setNome(nuovoNome);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public void addRubrica(String nomeRubrica) throws SQLException {
		SistemaDAO sistemaPosgr = new SistemaImplementazionePostgresDAO();
		try {
			//add nel DB
			sistemaPosgr.addRubrica(nomeRubrica);
			//add in memoria
			Rubrica rubrica = new Rubrica(nomeRubrica);
			getRubriche().add(rubrica);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public void deleteRubrica() throws SQLException {
		SistemaDAO sistemaPosgr = new SistemaImplementazionePostgresDAO();
		try {
			//remove dal DB, per nome
			sistemaPosgr.deleteRubrica(rubricaSelezionata.getNome());
			//remove dalla memoria, per rubrica selezionata nella UI
			getRubriche().remove(rubricaSelezionata);
		} catch (SQLException e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	/*public String[] getNomiContattiRubrica() {
		
	}*/
}
