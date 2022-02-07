package model;

public class Email {
	private String indirizzoEmail;
	
	private enum tipoEmail{Principale,Secondario};
	
	private tipoEmail tipo;
	
	
	public Email(String s,String t) {
		setIndirizzoEmail(s);
		setTipo(t);
	}
	
	private void setTipo(String t) {
		indirizzoEmail=t;
	}

	private void setIndirizzoEmail(String s) {
		//la stringa s è convertita in un intero enum, e quindi assegnata a tipo
		tipo=tipoEmail.valueOf(s);
	}
	
	private String getIndirizzoEmail() {
		return indirizzoEmail;
	}
	
	private String getTipo() {
		return tipo.toString();
	}

	
}
