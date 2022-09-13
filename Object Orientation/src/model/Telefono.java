package model;

/** Gestisce l'oggetto Telefono.
 */
public class Telefono {
	
	/** Numero telefonico. */
	private String numero;
	
	/** Descrizione del numero telefonico. */
	private String tipo;
	
	/**
	 * Costruttore di un oggetto Telefono, con set di numero e descrizione
	 *
	 * @param numero numero telefonico
	 * @param tipo descrizione del numero telefonico
	 */
	public Telefono(String numero,String tipo) {
		setNumero(numero);
		setTipo(tipo);
	}
	
	/**
	 * Imposta il numero.
	 *
	 * @param numero numero telefonico da impostare
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Imposta la desrizione del numero.
	 *
	 * @param tipo tipo da impostare
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Ritorna il numero telefonico.
	 *
	 * @return numero
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * Ritorna la descrizione del numero.
	 *
	 * @return tipo
	 */
	public String getTipo() {
		return tipo;
	}
}
