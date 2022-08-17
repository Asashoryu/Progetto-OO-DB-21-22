package model;

public class Account {
	
	private String fornitore;
	
	private String fraseStato;

	private String nickname;
	
	public Account(String fornitore, String fraseStato, String nickname)
	{
		setFornitore(fornitore);
		setFraseStato(fraseStato);
		setNickname(nickname);
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
}
