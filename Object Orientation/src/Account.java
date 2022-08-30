package model;

/** Gestisce l'oggetto Account e la sua interazione con il controller. */
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
	 * Instanzia un nuovo account con le varie informazioni necessarie.
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
	 * Ritorna il fornitore salvato localmente.
	 *
	 * @return fornitore dell'account
	 */
	public String getFornitore() {
		return fornitore;
	}
	
	/**
	 * Imposta il valore del fornitore localmente.
	 *
	 * @param fornitore fornitore
	 */
	public void setFornitore(String fornitore) {
		this.fornitore = fornitore;
	}
	
	/**
	 * Ritorna la frase stato salvata localmente.
	 *
	 * @return frase stato
	 */
	public String getFraseStato() {
		return fraseStato;
	}
	
	/**
	 * Imposta il valore della frase stato localmente.
	 *
	 * @param fraseStato frase stato
	 */
	public void setFraseStato(String fraseStato) {
		this.fraseStato = fraseStato;
	}
	
	/**
	 * Ritorna il nickname salvato localmente.
	 *
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * Imposta il valore del nickname localmente.
	 *
	 * @param nickname nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Ritorna la stringa email salvata localmente.
	 *
	 * @returnstringa email
	 */
	public String getStringaEmail() {
		return stringaEmail;
	}

	/**
	 * Imposta la stringa email localmente.
	 *
	 * @param stringaEmail stringa email
	 */
	public void setStringaEmail(String stringaEmail) {
		this.stringaEmail = stringaEmail;
	}
	
}
