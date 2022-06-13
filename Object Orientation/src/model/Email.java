package model;

public class Email 
{
	private String stringaEmail;
	
	private enum tipoEmail{Principale,Secondario};
	
	private tipoEmail tipo;
	
	
	public Email(String stringaEmail,String tipo) 
	{
		setStringaEmail(stringaEmail);
		setTipo(tipo);
	}
	
	public void setStringaEmail(String stringaEmail) 
	{
		this.stringaEmail = stringaEmail;
	}

	public void setTipo(String tipo) 
	{
		//la stringa s è convertita in un intero enum, e quindi assegnata a tipo
		this.tipo = tipoEmail.valueOf(tipo);
	}
	
	public String getStringaEmail() 
	{
		return stringaEmail;
	}
	
	public String getTipo() 
	{
		return tipo.toString();
	}

	
}
