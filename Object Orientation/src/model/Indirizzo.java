package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Indirizzo.
 */
public class Indirizzo 
{
	
	/**
	 * The Enum tipoIndirizzo.
	 */
	public enum tipoIndirizzo{
/** The Principale. */
Principale, 
 /** The Secondario. */
 Secondario};
	
	/** The via. */
	private String via;
	
	/** The citta. */
	private String citta;
	
	/** The nazione. */
	private String nazione;
	
	/** The cap. */
	private String cap;
	
	/** The tipo. */
	private tipoIndirizzo tipo;
	
	/**
	 * Instantiates a new indirizzo.
	 *
	 * @param via the via
	 * @param citta the citta
	 * @param nazione the nazione
	 * @param cap the cap
	 * @param tipo the tipo
	 */
	public Indirizzo(String via, String citta, String nazione, String cap, tipoIndirizzo tipo) {
		setVia(via);
		setCitta(citta);
		setNazione(nazione);
		setCap(cap);
		setTipo(tipo);
	}
	
	/**
	 * Sets the via.
	 *
	 * @param via the new via
	 */
	public void setVia(String via) {
		this.via = via;
	}
	
	/**
	 * Sets the citta.
	 *
	 * @param citta the new citta
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	/**
	 * Sets the nazione.
	 *
	 * @param nazione the new nazione
	 */
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
	/**
	 * Sets the cap.
	 *
	 * @param cap the new cap
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(tipoIndirizzo tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Gets the via.
	 *
	 * @return the via
	 */
	public String getVia() {
		return via;
	}
	
	/**
	 * Gets the citta.
	 *
	 * @return the citta
	 */
	public String getCitta() {
		return citta;
	}
	
	/**
	 * Gets the nazione.
	 *
	 * @return the nazione
	 */
	public String getNazione() {
		return nazione;
	}
	
	/**
	 * Gets the cap.
	 *
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}
	
	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public tipoIndirizzo getTipo() {
		return tipo;
	}
}
