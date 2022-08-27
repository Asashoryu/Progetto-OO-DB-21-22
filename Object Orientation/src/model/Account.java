package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Account.
 */
public class Account {
	
	/** The fornitore. */
	private String fornitore;
	
	/** The frase stato. */
	private String fraseStato;

	/** The nickname. */
	private String nickname;
	
	/** The stringa email. */
	private String stringaEmail;
	
	/**
	 * Instantiates a new account.
	 *
	 * @param fornitore the fornitore
	 * @param fraseStato the frase stato
	 * @param nickname the nickname
	 * @param stringaEmail the stringa email
	 */
	public Account(String fornitore, String fraseStato, String nickname, String stringaEmail)
	{
		setFornitore(fornitore);
		setFraseStato(fraseStato);
		setNickname(nickname);
		setStringaEmail(stringaEmail);
	}

	/**
	 * Gets the fornitore.
	 *
	 * @return the fornitore
	 */
	public String getFornitore() {
		return fornitore;
	}
	
	/**
	 * Sets the fornitore.
	 *
	 * @param fornitore the new fornitore
	 */
	public void setFornitore(String fornitore) {
		this.fornitore = fornitore;
	}
	
	/**
	 * Gets the frase stato.
	 *
	 * @return the frase stato
	 */
	public String getFraseStato() {
		return fraseStato;
	}
	
	/**
	 * Sets the frase stato.
	 *
	 * @param fraseStato the new frase stato
	 */
	public void setFraseStato(String fraseStato) {
		this.fraseStato = fraseStato;
	}
	
	/**
	 * Gets the nickname.
	 *
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * Sets the nickname.
	 *
	 * @param nickname the new nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Gets the stringa email.
	 *
	 * @return the stringa email
	 */
	public String getStringaEmail() {
		return stringaEmail;
	}

	/**
	 * Sets the stringa email.
	 *
	 * @param stringaEmail the new stringa email
	 */
	public void setStringaEmail(String stringaEmail) {
		this.stringaEmail = stringaEmail;
	}
	
}
