package model;

public class Indirizzo 
{
	public enum tipoIndirizzo{Principale, Secondario};
	
	private String via;
	
	private String citta;
	
	private String nazione;
	
	private String cap;
	
	private tipoIndirizzo tipo;
	
	public Indirizzo(String via, String citta, String nazione, String cap, tipoIndirizzo tipo) {
		setVia(via);
		setCitta(citta);
		setNazione(nazione);
		setCap(cap);
		setTipo(tipo);
	}
	
	public void setVia(String via) {
		this.via = via;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
	public void setCap(String cap) {
		this.cap = cap;
	}

	public void setTipo(tipoIndirizzo tipo) {
		this.tipo = tipo;
	}
	
	public String getVia() {
		return via;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public String getNazione() {
		return nazione;
	}
	
	public String getCap() {
		return cap;
	}
	
	public tipoIndirizzo getTipo() {
		return tipo;
	}
}
