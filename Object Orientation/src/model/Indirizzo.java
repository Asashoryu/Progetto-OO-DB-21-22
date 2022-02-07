package model;

public class Indirizzo {
	
	private enum tipoIndirizzo{Mobile,Fisso};
	
	private tipoIndirizzo tipo;
	
	public Indirizzo(String s,String t) {
		setTipo(t);
	}
	

	private void setTipo(String s) {
		//la stringa s è convertita in un intero enum, e quindi assegnata a tipo
		tipo=tipoIndirizzo.valueOf(s);
	}
	
	private String getTipo() {
		return tipo.toString();
	}
}
