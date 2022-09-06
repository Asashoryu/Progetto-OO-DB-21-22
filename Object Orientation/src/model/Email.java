package model;

import java.util.ArrayList;

/** Gestisce l'oggetto Email e la sua interazione con il controller. */
public class Email 
{
	
	/** indirizzo email. */
	private String stringaEmail;
	
	/** tipo dell'indirizzo email. */
	private String tipo;
	
	/** ArrayList degli account. */
	private ArrayList<Account> account;
	
	/**
	 * Instanza una nuova email.
	 *
	 * @param stringaEmail email sottoforma di String
	 * @param tipo tipo/descrizione dell'email
	 */
	public Email(String stringaEmail,String tipo) 
	{
		setStringaEmail(stringaEmail);
		setTipo(tipo);
		account = new ArrayList<Account>();
	}
	
	/**
	 * Imposta il valore dell'email localmente.
	 *
	 * @param stringaEmail stringa email di cui impostare il valore
	 */
	public void setStringaEmail(String stringaEmail) 
	{
		this.stringaEmail = stringaEmail;
	}

	/**
	 * Imposta il valore del tipo localmente.
	 *
	 * @param tipo tipo/descrizione dell'email
	 */
	public void setTipo(String tipo) 
	{
		//la stringa s è convertita in un intero enum, e quindi assegnata a tipo
		this.tipo = tipo;
	}
	
	/**
	 * Ritorna l'email impostata localmente.
	 *
	 * @return email
	 */
	public String getStringaEmail()
	{
		return stringaEmail;
	}
	
	/**
	 * Ritorna il tipo impostato localmente.
	 *
	 * @return tipo
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * Ritorna l'account impostato localmente.
	 *
	 * @return account come ArrayList
	 */
	public ArrayList<Account> getAccount() 
	{
		return account;
	}

	/**
	 *Imposta il valore dell'account localmente.
	 *
	 * @param account account da impostare
	 */
	public void setAccount(ArrayList<Account> account) 
	{
		this.account = account;
	}
	
	/**
	 * Metodo per aggiungere nuovi account.
	 *
	 * @param fornitore fornitore dell'account
	 * @param fraseStato frase stato relativa all'account
	 * @param nickname nickname dell'account
	 */
	public void addAccount(String fornitore, String fraseStato, String nickname)
	{
		Account nuovoAccount = new Account(fornitore, fraseStato, nickname, getStringaEmail());
		account.add(nuovoAccount);
	}
	
}
