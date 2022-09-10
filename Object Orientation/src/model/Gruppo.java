package model;

import java.util.ArrayList;

/** Gestisce l'oggetto Gruppo e la sua interazione con il controller. */
public class Gruppo{
	
	/** nome del gruppo. */
	private String nome;
	
	/** Identificativo (id) del gruppo. */
	private int id;
	
	/** ArrayList dei contatti appartenenti al gruppo. */
	private ArrayList<Contatto> contatti;
	
	/**
	 * Instanzia un nuovo gruppo tramite ArrayList di contatti.
	 */
	public Gruppo ()
	{
		contatti = new ArrayList<Contatto>();
	}
	
	/**
	 * Instanzia un nuovo gruppo salvandone il nome e l'identificativo per il DB.
	 *
	 * @param nome nome del gruppo
	 * @param id identificativo del gruppo
	 */
	public Gruppo (String nome, int id) {
		setNome(nome);
		setId(id);
		contatti = new ArrayList<Contatto>();
	}
	
	/**
	 * Instanzia un nuovo gruppo con il suo nome e i contatti che contiene.
	 *
	 * @param nome nome del gruppo
	 * @param contatti contatti appartententi al gruppo
	 */
	public Gruppo (String nome, ArrayList<Contatto> contatti) {
		setNome(nome);
		this.contatti = contatti;
	}
	
	/**
	 * Ritorna il nome del gruppo impostato localmente.
	 *
	 * @return nome del gruppo
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Imposta il valore del nome del gruppo localmente.
	 *
	 * @param nome nome del gruppo da impostare localmente
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Ritorna l'identificativo/id del gruppo impostato localmente.
	 *
	 * @return id del gruppo
	 */
	public int getId() {
		return id;
	}

	/**
	 * Imposta il valore dell'id del gruppo localmente.
	 *
	 * @param id identificativo del gruppo
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Ritorna l'ArrayList di contatti del gruppo.
	 *
	 * @return ArrayList contatti
	 */
	public ArrayList<Contatto> getContatti() {
		return contatti;
	}

	/**
	 * Imposta il valore dell'ArrayList contatti localmente.
	 *
	 * @param contatti ArrayList contatti
	 */
	public void setContatti(ArrayList<Contatto> contatti) {
		this.contatti = contatti;
	}
	
}
