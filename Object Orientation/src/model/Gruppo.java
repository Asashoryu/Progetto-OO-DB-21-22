package model;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Gruppo.
 */
public class Gruppo{
	
	/** The nome. */
	private String nome;
	
	/** The id. */
	private int id;
	
	/** The contatti. */
	private ArrayList<Contatto> contatti;
	
	/**
	 * Instantiates a new gruppo.
	 */
	public Gruppo ()
	{
		contatti = new ArrayList<Contatto>();
	}
	
	/**
	 * Instantiates a new gruppo.
	 *
	 * @param nome the nome
	 * @param id the id
	 */
	public Gruppo (String nome, int id) {
		setNome(nome);
		setId(id);
		contatti = new ArrayList<Contatto>();
	}
	
	/**
	 * Instantiates a new gruppo.
	 *
	 * @param nome the nome
	 * @param contatti the contatti
	 */
	public Gruppo (String nome, ArrayList<Contatto> contatti) {
		setNome(nome);
		this.contatti = contatti;
	}
	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the contatti.
	 *
	 * @return the contatti
	 */
	public ArrayList<Contatto> getContatti() {
		return contatti;
	}

	/**
	 * Sets the contatti.
	 *
	 * @param contatti the new contatti
	 */
	public void setContatti(ArrayList<Contatto> contatti) {
		this.contatti = contatti;
	}
	
}
