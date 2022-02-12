package model;

public class Indirizzo {
	
	private String via;
	
	private String citta;
	
	private String nazione;
	
	private String cap;
	
	private enum tipoIndirizzo{Mobile,Fisso};
	
	private tipoIndirizzo tipo;
	
	public Indirizzo(String via, String citta, String nazione, String cap, String tipo) {
		setVia(via);
		setCitta(citta);
		setNazione(nazione);
		setCap(cap);
		setTipo(tipo);
	}
	
	public void setVia(String via) {
		this.via=via;
	}
	
	public void setCitta(String citta) {
		this.citta=citta;
	}
	
	public void setNazione(String nazione) {
		this.nazione=nazione;
	}
	
	public void setCap(String cap) {
		this.cap=cap;
	}

	public void setTipo(String tipo) {
		//la stringa s è convertita in un intero enum, e quindi assegnata a tipo
		this.tipo=tipoIndirizzo.valueOf(tipo);
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
	
	public String getTipo() {
		return tipo.toString();
	}
}
