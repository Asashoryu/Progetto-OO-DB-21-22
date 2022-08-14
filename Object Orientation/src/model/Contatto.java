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
	/** percorso assoluto dell'immagine del contatto */
	private String pathImmagine;
	/** ArrayList dei numeri di telefono del contatto */
	private ArrayList<Telefono> telefoni;
	/** ArrayList delle email del contatto */
	private ArrayList<Email> email;
	/** ArrayList degli indiririzzi del contatto */
	private ArrayList<Indirizzo> indirizzi;
	/** Identificativo del contatto */
	private int id;
	
	/** costruttore del contatto con set di nome, secondo nome e cognome per la visualizzazione in listaContatto */
	public Contatto(String primonome, String secondonome, String cognome, String pathImmagine, int id) {
		setNome(primonome);
		setSecondoNome(secondonome);
		setCognome(cognome);
		setPathImmagine(pathImmagine);
		setId(id);
		telefoni  = new ArrayList<Telefono>();
		email     = new ArrayList<Email>();
		indirizzi = new ArrayList<Indirizzo>();
	}
	/** costruttore di Contatto, con le informazioni essenziali indicate nella traccia */
	public Contatto(String primonome, String secondonome, String cognome,
                    String numMobile, String numFisso, String via, String citta, String nazione, String cap, int id)
	{
		setNome(primonome);
		setSecondoNome(secondonome);
		setCognome(cognome);
		setId(id);
		/** allocazione delle informazioni */
		telefoni  = new ArrayList<Telefono>();
		email     = new ArrayList<Email>();
		indirizzi = new ArrayList<Indirizzo>();
		/** aggiungo il numero mobile */
		addTelefono(numMobile, "Mobile");
		/** aggiungo il numero fisso */
		addTelefono(numFisso, "Fisso");
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
	/** set del path dell'immagine profilo */
	public void setPathImmagine(String pathImmagine) {
		this.pathImmagine = pathImmagine;
	}
	/** return del path dell'immagine profilo */
	public String getPathImmagine() {
		return pathImmagine;
	}
	/** set dell'id del contatto */
	public void setId(int id) {
		this.id = id;
	}
	/** return dell'id del contatto */
	public int getId() {
		return id;
	}
	/** return degli indirizzi del contatto */
	public ArrayList<Indirizzo> getIndirizzi()
	{
		return indirizzi;
	}
	/** return dei telefoni del contatto */
	public ArrayList<Telefono> getTelefoni()
	{
		return telefoni;
	}
	/** return delle email del cotnatto */
	public ArrayList<Email> getEmail()
	{
		return email;
	}
	/** metodo per aggiungere un numero di telefono al contatto */
	public void addTelefono(String numero, String tipotelefono) {
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
