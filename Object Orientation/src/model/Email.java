package model;

import java.util.ArrayList;

public class Email 
{
	private String stringaEmail;
	
	private String tipo;
	
	private ArrayList<Account> account;
	
	public Email(String stringaEmail,String tipo) 
	{
		setStringaEmail(stringaEmail);
		setTipo(tipo);
		account = new ArrayList<Account>();
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
		return tipo;
	}

	public ArrayList<Account> getAccount() 
	{
		return account;
	}

	public void setAccount(ArrayList<Account> account) 
	{
		this.account = account;
	}
	
	public void addAccount(String fornitore, String fraseStato, String nickname)
	{
		Account nuovoAccount = new Account(fornitore, fraseStato, nickname, getStringaEmail());
		account.add(nuovoAccount);
	}
	
}
