package model;

import java.util.ArrayList;

/** Gestisce l'oggetto Sistema.
 */
public class Sistema {
	
	/** ArrayList di rubriche contenute nel sistema. */
	private ArrayList<Rubrica> rubriche;
	
	/**
	 * Costruttore del Sistema.
	 */
	public Sistema() {
	}
	
	/**
	 * Ritorna le rubriche trovate nel Sistema.
	 *
	 * @return ArrayList di rubriche
	 */
	public ArrayList<Rubrica> getRubriche() {
		return rubriche;
	}
	
	/**
	 * Aggiunge le rubriche nel Sistema.
	 *
	 * @param rubriche the new rubriche
	 */
	public void setRubriche(ArrayList<Rubrica> rubriche) {
		this.rubriche = rubriche;
	}
	
}
