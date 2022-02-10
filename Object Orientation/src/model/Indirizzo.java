package model;

public class Indirizzo {
	
	private String via;
	
	private String citta;
	
	private String nazione;
	
	private String CAP;
	
	private enum tipoIndirizzo{Mobile,Fisso};
	
	private tipoIndirizzo tipo;
	
	public Indirizzo(String tipo) {
		setTipo(tipo);
	}

	private void setTipo(String tipo) {
		//la stringa s è convertita in un intero enum, e quindi assegnata a tipo
		this.tipo=tipoIndirizzo.valueOf(tipo);
	}
	
	private String getTipo() {
		return tipo.toString();
	}
}
