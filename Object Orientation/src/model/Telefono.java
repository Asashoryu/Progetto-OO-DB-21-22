package model;

public class Telefono {
	
	private String numero;
	
	private enum tipoTelefono{Mobile,Fisso};
	
	private tipoTelefono tipo;
	
	public Telefono(String numero,String tipo) {
		setNumero(numero);
		setTipo(tipo);
	}
	
	public void setNumero(String numero) {
		this.numero=numero;
	}

	public void setTipo(String tipo) {
		//la stringa s è convertita in un intero enum, e quindi assegnata a tipo
		this.tipo=tipoTelefono.valueOf(tipo);
	}
	
	public String getNumero() {
		return numero;
	}
	
	public String getTipo() {
		return tipo.toString();
	}
}
