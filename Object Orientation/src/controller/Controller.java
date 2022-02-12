package controller;

import java.util.ArrayList;

import model.Rubrica;
import model.Sistema;

public class Controller {
	
	private Sistema sistema;
	
	private Rubrica rubricaSelezionata;
	
	// Alla creazione del controller, sono caricate le Rubriche dal DB
	public Controller() {
		sistema = new Sistema();
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
	
	public void updateRubrica(String nuovoNome) {
		sistema.updateRubrica(rubricaSelezionata, nuovoNome);
	}
}
