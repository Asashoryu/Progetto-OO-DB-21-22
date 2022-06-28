package model;

import java.util.ArrayList;

import model.Indirizzo.tipoIndirizzo;

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
	/** costruttore del contatto con set di nome, secondo nome e cognome per la visualizzazione in listaContatto */
	public Contatto(String primonome, String secondonome, String cognome) {
		setNome(primonome);
		setSecondoNome(secondonome);
		setCognome(cognome);
		telefoni  = new ArrayList<Telefono>();
		email     = new ArrayList<Email>();
		indirizzi = new ArrayList<Indirizzo>();
	}
	/** costruttore di Contatto, con le informazioni essenziali indicate nella traccia */
	public Contatto(String primonome, String secondonome, String cognome,
                    String numMobile, String numFisso, String via, String citta, String nazione, String cap)
	{
		setNome(primonome);
		setSecondoNome(secondonome);
		setCognome(cognome);
		/** allocazione delle informazioni */
		telefoni  = new ArrayList<Telefono>();
		email     = new ArrayList<Email>();
		indirizzi = new ArrayList<Indirizzo>();
		/** aggiungo il numero mobile */
		addNumero(numMobile, "Mobile");
		/** aggiungo il numero fisso */
		addNumero(numFisso, "Fisso");
		/** aggiungo l'indirizzo principale */
		addIndirizzo(via, citta, nazione, cap, Indirizzo.tipoIndirizzo.Principale);
	}
	/** set del nome del contatto */
	public void setNome(String primonome) 
	{
		this.primonome = primonome;
	}
	/** return del nome del contatto */
	public String getNome() {
		return primonome;
	}
	/** set del secondo nome del contatto */
	public void setSecondoNome(String secondonome) 
	{
		this.secondonome = secondonome;
	}
	/** return del secondo nome del contatto */
	public String getSecondoNome() 
	{
		return secondonome;
	}
	/** set del cognome del contatto */
	public void setCognome(String cognome) 
	{
		this.cognome = cognome;
	}
	/** return del cognome del contatto */
	public String getCognome() 
	{
		return cognome;
	}

	/** metodo per aggiungere un numero di telefono al contatto */
	public void addNumero(String numero, String tipotelefono) {
		Telefono nuovoTelefono = new Telefono(numero, tipotelefono);
		telefoni.add(nuovoTelefono);
	}
	/** metodo per aggiungere una email al contatto */
	public void addEmail(String indirizzoemail, String tipoemail) {
		Email nuovaEmail = new Email(indirizzoemail, tipoemail);
		email.add(nuovaEmail);
	}
	/** metodo per aggiungere un indirizzo al contatto */
	public void addIndirizzo(String via, String citta, String nazione, String cap, tipoIndirizzo tipo) {
		Indirizzo nuovoIndirizzo = new Indirizzo(via, citta, nazione, cap, tipo);
		indirizzi.add(nuovoIndirizzo);
	}
	
}
