package model;

import java.util.ArrayList;

/** Gestisce l'oggetto Gruppo. */
public class Gruppo{
	
	/** nome del gruppo. */
	private String nome;
	
	/** Identificativo (id) del gruppo. */
	private int id;
	
	/** ArrayList dei contatti appartenenti al gruppo. */
	private ArrayList<Contatto> contatti;
	
	/**
	 * Costruttore di un Gruppo.
	 */
	public Gruppo ()
	{
		contatti = new ArrayList<Contatto>();
	}
	
	/**
	 * Costruttore di un Gruppo, con impostazione del suo nome e del suo identificativo.
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
	 * Costruttore di un Gruppo, con impostazione del suo nome e dei contatti che contiene.
	 *
	 * @param nome nome del gruppo
	 * @param contatti ArrayList di contatti appartententi al gruppo
	 */
	public Gruppo (String nome, ArrayList<Contatto> contatti) {
		setNome(nome);
		this.contatti = contatti;
	}
	
	/**
	 * Ritorna il nome del gruppo.
	 *
	 * @return nome del gruppo
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Imposta il nome del gruppo.
	 *
	 * @param nome nome del gruppo 
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Ritorna l'identificativo/id del gruppo.
	 *
	 * @return id del gruppo
	 */
	public int getId() {
		return id;
	}

	/**
	 * Imposta l'id del gruppo.
	 *
	 * @param id identificativo del gruppo
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Ritorna i contatti del gruppo.
	 *
	 * @return ArrayList contatti
	 */
	public ArrayList<Contatto> getContatti() {
		return contatti;
	}

	/**
	 * Imposta i contatti del gruppo.
	 *
	 * @param contatti ArrayList contatti
	 */
	public void setContatti(ArrayList<Contatto> contatti) {
		this.contatti = contatti;
	}
	
}
