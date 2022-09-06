package model;

/** Gestisce l'oggetto Indirizzo e la sua interazione con il controller.  */
public class Indirizzo 
{
	
	/** Enumerazione di tipoIndirizzo per impostare il tipo come primario oppure secondario. */
	public enum tipoIndirizzo{
/** Tipo principale di indirizzo. */
Principale, 
 /** Tipo secondario di indirizzo. */
 Secondario};
	
	/** via dell'indirizzo. */
	private String via;
	
	/** città dell'indirizzo. */
	private String citta;
	
	/** nazione dell'indirizzo. */
	private String nazione;
	
	/** CAP (codice di avviamento postale) dell'indirizzo. */
	private String cap;
	
	/** tipo dell'indirizzo (principale o secondario). */
	private tipoIndirizzo tipo;
	
	/**
	 * Instanzia un nuovo indirizzo con le relative informazioni.
	 *
	 * @param via via dell'indirizzo da inserire
	 * @param citta città dell'indirizzo da inserire
	 * @param nazione nazione dell'indirizzo da inserire
	 * @param cap cap dell'indirizzo da inserire
	 * @param tipo tipo dell'indirizzo da inserire
	 */
	public Indirizzo(String via, String citta, String nazione, String cap, tipoIndirizzo tipo) {
		setVia(via);
		setCitta(citta);
		setNazione(nazione);
		setCap(cap);
		setTipo(tipo);
	}
	
	/**
	 * Imposta il valore della via localmente.
	 *
	 * @param via via dell'indirizzo
	 */
	public void setVia(String via) {
		this.via = via;
	}
	
	/**
	 * Imposta il valore della città localmente.
	 *
	 * @param citta citta dell'indirizzo
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	/**
	 * Imposta il valore della nazione localmente.
	 *
	 * @param nazione nazione dell'indirizzo
	 */
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
	/**
	 * Imposta il valore del CAP localmente.
	 *
	 * @param cap CAP dell'indirizzo
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * Imposta il valore del tipo localmente.
	 *
	 * @param tipo tipo dell'indirizzo
	 */
	public void setTipo(tipoIndirizzo tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Ritorna la via impostata localmente.
	 *
	 * @return via
	 */
	public String getVia() {
		return via;
	}
	
	/**
	 * Ritorna la città impostata localmente.
	 *
	 * @return citta
	 */
	public String getCitta() {
		return citta;
	}
	
	/**
	 * Ritorna la nazione impostata localmente.
	 *
	 * @return nazione
	 */
	public String getNazione() {
		return nazione;
	}
	
	/**
	 * Ritorna il CAP impostato localmente.
	 *
	 * @return cap
	 */
	public String getCap() {
		return cap;
	}
	
	/**
	 * Ritorna il tipo dell'indirizzo impostato localmente.
	 *
	 * @return tipo
	 */
	public tipoIndirizzo getTipo() {
		return tipo;
	}
}
