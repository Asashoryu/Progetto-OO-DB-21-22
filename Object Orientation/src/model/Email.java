package model;

import java.util.ArrayList;

/** Gestisce l'oggetto Email. */
public class Email 
{
	/** indirizzo email. */
	private String stringaEmail;
	
	/** descrizione dell'indirizzo email. */
	private String tipo;
	
	/** ArrayList degli account recuperati tramite questa email. */
	private ArrayList<Account> account;
	
	/**
	 * Costruttore di una Email.
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
	 * Imposta il nome dell'email.
	 *
	 * @param stringaEmail stringa email di cui impostare il valore
	 */
	public void setStringaEmail(String stringaEmail) 
	{
		this.stringaEmail = stringaEmail;
	}

	/**
	 * Imposta la descrizione.
	 *
	 * @param tipo tipo/descrizione dell'email
	 */
	public void setTipo(String tipo) 
	{
		//la stringa s è convertita in un intero enum, e quindi assegnata a tipo
		this.tipo = tipo;
	}
	
	/**
	 * Ritorna l'email.
	 *
	 * @return email
	 */
	public String getStringaEmail()
	{
		return stringaEmail;
	}
	
	/**
	 * Ritorna la descrizione .
	 *
	 * @return descrizione dell'email
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * Ritorna gli account associati alla data email.
	 *
	 * @return account come ArrayList
	 */
	public ArrayList<Account> getAccount() 
	{
		return account;
	}

	/**
	 * Imposta il valore dell'account.
	 *
	 * @param account account da impostare
	 */
	public void setAccount(ArrayList<Account> account) 
	{
		this.account = account;
	}
	
	/**
	 * Aggiunge gli account.
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
