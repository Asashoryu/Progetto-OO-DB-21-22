package model;

/** Gestisce l'oggetto Indirizzo. */
public class Indirizzo 
{
	
	/** Enumerazione dei tipi di indirizzo che possono essere creati. */
	public enum tipoIndirizzo{
		/** Tipo indirizzo principale. */
		Principale, 
		 /** Tipo indirizzo secondario. */
		 Secondario
	};
	
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
	 * Costruttore di un Indirizzo.
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
	 * Imposta il nome della via.
	 *
	 * @param via via dell'indirizzo
	 */
	public void setVia(String via) {
		this.via = via;
	}
	
	/**
	 * Imposta il nome della città.
	 *
	 * @param citta citta dell'indirizzo
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	/**
	 * Imposta il nome della nazione.
	 *
	 * @param nazione nazione dell'indirizzo
	 */
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
	/**
	 * Imposta il CAP.
	 *
	 * @param cap CAP dell'indirizzo
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * Imposta il il tipo dell'indirizzo.
	 *
	 * @param tipo tipo dell'indirizzo
	 */
	public void setTipo(tipoIndirizzo tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Ritorna la via.
	 *
	 * @return via
	 */
	public String getVia() {
		return via;
	}
	
	/**
	 * Ritorna la città .
	 *
	 * @return citta
	 */
	public String getCitta() {
		return citta;
	}
	
	/**
	 * Ritorna la nazione .
	 *
	 * @return nazione
	 */
	public String getNazione() {
		return nazione;
	}
	
	/**
	 * Ritorna il CAP .
	 *
	 * @return cap
	 */
	public String getCap() {
		return cap;
	}
	
	/**
	 * Ritorna il tipo dell'indirizzo .
	 *
	 * @return tipo
	 */
	public tipoIndirizzo getTipo() {
		return tipo;
	}
}
