package model;

/** Gestisce l'oggetto Account. */
public class Account {
	
	/** Nome del fornitore dell'account. */
	private String fornitore;
	
	/** Frase stato indicata nell'account. */
	private String fraseStato;

	/** Nickname dell'account. */
	private String nickname;
	
	/** Email a cui è associato l'account. */
	private String stringaEmail;
	
	/**
	 * Costruttore di un Account.
	 *
	 * @param fornitore fornitore
	 * @param fraseStato frase stato
	 * @param nickname nickname
	 * @param stringaEmail email
	 */
	public Account(String fornitore, String fraseStato, String nickname, String stringaEmail)
	{
		setFornitore(fornitore);
		setFraseStato(fraseStato);
		setNickname(nickname);
		setStringaEmail(stringaEmail);
	}

	/**
	 * Ritorna il nome del fornitore.
	 *
	 * @return fornitore dell'account
	 */
	public String getFornitore() {
		return fornitore;
	}
	
	/**
	 * Imposta il valore del fornitore.
	 *
	 * @param fornitore fornitore
	 */
	public void setFornitore(String fornitore) {
		this.fornitore = fornitore;
	}
	
	/**
	 * Ritorna la frase stato.
	 *
	 * @return frase stato
	 */
	public String getFraseStato() {
		return fraseStato;
	}
	
	/**
	 * Imposta la frase stato.
	 *
	 * @param fraseStato frase stato
	 */
	public void setFraseStato(String fraseStato) {
		this.fraseStato = fraseStato;
	}
	
	/**
	 * Ritorna il nickname.
	 *
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * Imposta il nickname.
	 *
	 * @param nickname nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Ritorna la stringa email.
	 *
	 * @returnstringa email
	 */
	public String getStringaEmail() {
		return stringaEmail;
	}

	/**
	 * Imposta la stringa email.
	 *
	 * @param stringaEmail stringa email
	 */
	public void setStringaEmail(String stringaEmail) {
		this.stringaEmail = stringaEmail;
	}
	
}
