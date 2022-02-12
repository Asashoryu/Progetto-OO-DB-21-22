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
		setNome(primonome, secondonome, cognome);
	}
	
	public void setNome(String primonome, String secondonome, String cognome) {
		// TODO Auto-generated method stub
		this.primonome=primonome;
		this.secondonome=secondonome;
		this.cognome=cognome;
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
