package model;

public class Telefono {
	private String numero;
	
	private enum tipoTelefono{Mobile,Fisso};
	
	private tipoTelefono tipo;
	
	public Telefono(String s,String t) {
		setNumero(s);
		setTipo(t);
	}
	
	private void setNumero(String t) {
		numero=t;
	}

	private void setTipo(String s) {
		//la stringa s è convertita in un intero enum, e quindi assegnata a tipo
		tipo=tipoTelefono.valueOf(s);
	}
	
	private String getNumero() {
		return numero;
	}
	
	private String getTipo() {
		return tipo.toString();
	}
}
