package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Telefono.
 */
public class Telefono {
	
	/** The numero. */
	private String numero;
	
	/** The tipo. */
	private String tipo;
	
	/**
	 * Instantiates a new telefono.
	 *
	 * @param numero the numero
	 * @param tipo the tipo
	 */
	public Telefono(String numero,String tipo) {
		setNumero(numero);
		setTipo(tipo);
	}
	
	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
}
