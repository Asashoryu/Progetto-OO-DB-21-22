package model;

public class Email 
{
	private String stringaEmail;
	
	private String tipo;
	
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
		this.tipo = tipo;
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
