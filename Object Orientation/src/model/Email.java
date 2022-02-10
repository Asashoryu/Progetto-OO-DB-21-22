package model;

public class Email {
	
	private String stringaEmail;
	
	private enum tipoEmail{Principale,Secondario};
	
	private tipoEmail tipo;
	
	
	public Email(String stringaEmail,String tipo) {
		setStringaEmail(stringaEmail);
		setTipo(tipo);
	}
	
	private void setStringaEmail(String stringaEmail) {
		this.stringaEmail=stringaEmail;
	}

	private void setTipo(String tipo) {
		//la stringa s è convertita in un intero enum, e quindi assegnata a tipo
		this.tipo=tipoEmail.valueOf(tipo);
	}
	
	private String getStringaEmail() {
		return stringaEmail;
	}
	
	private String getTipo() {
		return tipo.toString();
	}

	
}
