package model;

public class Telefono {
	
	private String numero;
	
	private String tipo;
	
	public Telefono(String numero,String tipo) {
		setNumero(numero);
		setTipo(tipo);
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public String getTipo() {
		return tipo;
	}
}
