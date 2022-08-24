package model;

public class Account {
	
	private String fornitore;
	
	private String fraseStato;

	private String nickname;
	
	private String stringaEmail;
	
	public Account(String fornitore, String fraseStato, String nickname, String stringaEmail)
	{
		setFornitore(fornitore);
		setFraseStato(fraseStato);
		setNickname(nickname);
		setStringaEmail(stringaEmail);
	}

	public String getFornitore() {
		return fornitore;
	}
	
	public void setFornitore(String fornitore) {
		this.fornitore = fornitore;
	}
	
	public String getFraseStato() {
		return fraseStato;
	}
	
	public void setFraseStato(String fraseStato) {
		this.fraseStato = fraseStato;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getStringaEmail() {
		return stringaEmail;
	}

	public void setStringaEmail(String stringaEmail) {
		this.stringaEmail = stringaEmail;
	}
	
}
