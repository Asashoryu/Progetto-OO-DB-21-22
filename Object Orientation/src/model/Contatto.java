package model;

import java.util.ArrayList;

public class Contatto{
	/** nome del contatto */
	private String primonome;
	/** secondo nome  del contatto */
	private String secondonome;
	/** cognome del contatto */
	private String cognome;
	/** ArrayList dei numeri di telefono del contatto */
	private ArrayList<Telefono> telefoni;
	/** ArrayList delle email del contatto */
	private ArrayList<Email> email;
	/** ArrayList degli indiririzzi del contatto */
	private ArrayList<Indirizzo> indirizzi;
	/** costruttore del contatto con set di nome, secondo nome e cognome */
	public Contatto(String primonome, String secondonome, String cognome) {
		setNome(primonome);
		setSecondoNome(secondonome);
		setCognome(cognome);
	}
	
	public Contatto(String primonome,      String secondonome, String cognome, 
			        String numero,         String tipotelefono,
			        String indirizzoemail, String tipoemail,
			        String via, String citta, String nazione, String cap, String tipo)
	{
		setNome(primonome);
		setSecondoNome(secondonome);
		setCognome(cognome);
		addNumero(numero, tipotelefono);
		addEmail(indirizzoemail, tipoemail);
		addIndirizzo(via, citta, nazione, cap, tipo);
	}
	/** set del nome del contatto */
	public void setNome(String primonome) {
		this.primonome=primonome;
	}
	/** return del nome del contatto */
	public String getNome() {
		return primonome;
	}
	/** set del secondo nome del contatto */
	public void setSecondoNome(String secondonome) {
		this.secondonome=secondonome;
	}
	/** return del secondo nome del contatto */
	public String getSecondoNome() {
		return secondonome;
	}
	/** set del cognome del contatto */
	public void setCognome(String cognome) {
		this.cognome=cognome;
	}
	/** return del cognome del contatto */
	public String getCognome() {
		return cognome;
	}

	/** metodo per aggiungere un numero di telefono al contatto */
	public void addNumero(String numero,String tipotelefono) {
		Telefono NuovoNumero = new Telefono(numero,tipotelefono);
		telefoni.add(NuovoNumero);
	}
	/** metodo per aggiungere una email al contatto */
	public void addEmail(String indirizzoemail,String tipoemail) {
		Email NuovaEmail = new Email(indirizzoemail,tipoemail);
		email.add(NuovaEmail);
	}
	/** metodo per aggiungere un indirizzo al contatto */
	public void addIndirizzo(String via, String citta, String nazione, String cap, String tipo) {
		Indirizzo NuovoIndirizzo = new Indirizzo(via, citta, nazione, cap, tipo);
		indirizzi.add(NuovoIndirizzo);
	}
	
}
