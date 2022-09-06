package model;

/** Gestisce l'oggetto Telefono e la sua interazione con il controller.
 */
public class Telefono {
	
	/** Numero telefonico. */
	private String numero;
	
	/** Tipo del numero telefonico. */
	private String tipo;
	
	/**
	 * Instanzia un nuovo oggetto telefono.
	 *
	 * @param numero numero telefonico
	 * @param tipo tipo del numero telefonico
	 */
	public Telefono(String numero,String tipo) {
		setNumero(numero);
		setTipo(tipo);
	}
	
	/**
	 * Imposta il valore del numero localmente.
	 *
	 * @param numero numero telefonico da impostare
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Imposta il valore del tipo del numero localmente.
	 *
	 * @param tipo tipo da impostare
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Ritorna il numero telefonico impostato localmente.
	 *
	 * @return numero
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * Ritorna il tipo impostato localmente.
	 *
	 * @return tipo
	 */
	public String getTipo() {
		return tipo;
	}
}
