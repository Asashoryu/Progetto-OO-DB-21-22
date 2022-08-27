package model;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Email.
 */
public class Email 
{
	
	/** The stringa email. */
	private String stringaEmail;
	
	/** The tipo. */
	private String tipo;
	
	/** The account. */
	private ArrayList<Account> account;
	
	/**
	 * Instantiates a new email.
	 *
	 * @param stringaEmail the stringa email
	 * @param tipo the tipo
	 */
	public Email(String stringaEmail,String tipo) 
	{
		setStringaEmail(stringaEmail);
		setTipo(tipo);
		account = new ArrayList<Account>();
	}
	
	/**
	 * Sets the stringa email.
	 *
	 * @param stringaEmail the new stringa email
	 */
	public void setStringaEmail(String stringaEmail) 
	{
		this.stringaEmail = stringaEmail;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(String tipo) 
	{
		//la stringa s è convertita in un intero enum, e quindi assegnata a tipo
		this.tipo = tipo;
	}
	
	/**
	 * Gets the stringa email.
	 *
	 * @return the stringa email
	 */
	public String getStringaEmail()
	{
		return stringaEmail;
	}
	
	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	public ArrayList<Account> getAccount() 
	{
		return account;
	}

	/**
	 * Sets the account.
	 *
	 * @param account the new account
	 */
	public void setAccount(ArrayList<Account> account) 
	{
		this.account = account;
	}
	
	/**
	 * Adds the account.
	 *
	 * @param fornitore the fornitore
	 * @param fraseStato the frase stato
	 * @param nickname the nickname
	 */
	public void addAccount(String fornitore, String fraseStato, String nickname)
	{
		Account nuovoAccount = new Account(fornitore, fraseStato, nickname, getStringaEmail());
		account.add(nuovoAccount);
	}
	
}
