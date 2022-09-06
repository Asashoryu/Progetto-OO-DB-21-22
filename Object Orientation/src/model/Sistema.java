package model;

import java.util.ArrayList;

/** Gestisce l'oggetto Sistema e la sua interazione col controller.
 */
public class Sistema {
	
	/** ArrayList di rubriche (oggetti rubrica). */
	private ArrayList<Rubrica> rubriche;
	
	/**
	 * Instanzia un nuovo oggetto sistema.
	 */
	public Sistema() {
	}
	
	/**
	 * Ritorna l'ArrayList di rubriche impostato localmente.
	 *
	 * @return ArrayList di rubriche
	 */
	public ArrayList<Rubrica> getRubriche() {
		return rubriche;
	}
	
	/**
	 * Imposta il valore dell'ArrayList rubriche localmente.
	 *
	 * @param rubriche the new rubriche
	 */
	public void setRubriche(ArrayList<Rubrica> rubriche) {
		this.rubriche = rubriche;
	}
	
}
