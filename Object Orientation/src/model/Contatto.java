package model;

import java.util.ArrayList;

public class Contatto{
	
	private String primonome;
	
	private String secondonome;
	
	private String cognome;
	
	private ArrayList<Telefono> telefoni;
	
	private ArrayList<Email> email;
	
	private ArrayList<Indirizzo> indirizzi;
	
	public Contatto(String primonome, String secondonome, String cognome) {
		setNome(primonome);
		setSecondoNome(secondonome);
		setCognome(cognome);
	}
	
	public void setNome(String primonome) {
		this.primonome=primonome;
	}
	
	public String getNome() {
		return primonome;
	}
	
	public void setSecondoNome(String secondonome) {
		this.secondonome=secondonome;
	}
	
	public String getSecondoNome() {
		return secondonome;
	}
	
	public void setCognome(String cognome) {
		this.cognome=cognome;
	}
	
	public String getCognome() {
		return cognome;
	}

	public void addNumero(String numero,String tipotelefono) {
		Telefono NuovoNumero = new Telefono(numero,tipotelefono);
		telefoni.add(NuovoNumero);
	}
	
	public void addEmail(String indirizzoemail,String tipoemail) {
		Email NuovaEmail = new Email(indirizzoemail,tipoemail);
		email.add(NuovaEmail);
	}
	
	public void addIndirizzo(String via, String citta, String nazione, String cap, String tipo) {
		Indirizzo NuovoIndirizzo = new Indirizzo(via, citta, nazione, cap, tipo);
		indirizzi.add(NuovoIndirizzo);
	}
	
}
