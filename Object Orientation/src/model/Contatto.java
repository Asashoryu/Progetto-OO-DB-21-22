package model;

import java.util.ArrayList;

import model.Indirizzo.tipoIndirizzo;

/** Gestisce l'oggetto Contatto. */
public class Contatto{
	
	/**  nome del contatto. */
	private String primonome;
	
	/**  secondo nome  del contatto. */
	private String secondonome;
	
	/**  cognome del contatto. */
	private String cognome;
	
	/**  percorso assoluto dell'immagine del contatto. */
	private String pathImmagine;
	
	/**  ArrayList dei numeri di telefono del contatto. */
	private ArrayList<Telefono> telefoni;
	
	/**  ArrayList delle email del contatto. */
	private ArrayList<Email> email;
	
	/**  ArrayList degli indiririzzi del contatto. */
	private ArrayList<Indirizzo> indirizzi;
	
	/**  Identificativo del contatto. */
	private int id;
	
	/**
	 * Costruttore di un Contatto con set di nome, secondo nome, cognome e immagine.
	 *
	 * @param primonome primo nome del contatto
	 * @param secondonome secondo nome del contatto
	 * @param cognome cognome del contatto
	 * @param pathImmagine path/percorso dell'immagine
	 * @param id identificativo del contatto nel DB
	 */
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
	
	/**
	 *  Costruttore di un Contatto, con set di nome, secondo nome e cognome.
	 *
	 * @param primonome primo nome del contatto
	 * @param secondonome secondo nome del contatto
	 * @param cognome cognome del contatto
	 * @param numMobile numero mobile del contatto
	 * @param numFisso numero fisso del contatto
	 * @param via via dell'indirizzo del contatto
	 * @param citta città dell'indirizzo del contatto
	 * @param nazione nazione dell'indirizzo del contatto
	 * @param cap cap dell'indirizzo del cap
	 * @param id identificativo del contatto nel DB
	 */
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
	
	/**
	 * Imposta il nome del contatto.
	 *
	 * @param primonome nome del contatto
	 */
	public void setNome(String primonome) 
	{
		this.primonome = primonome;
	}
	
	/**
	 *  Ritorna il nome del contatto.
	 *
	 * @return nome
	 */
	public String getNome() {
		return primonome;
	}
	
	/**
	 *  Imposta il del secondo nome del contatto.
	 *
	 * @param secondonome secondo nome del contatto
	 */
	public void setSecondoNome(String secondonome) 
	{
		this.secondonome = secondonome;
	}
	
	/**
	 *  Ritorna il secondo nome del contatto.
	 *
	 * @return secondo nome
	 */
	public String getSecondoNome() 
	{
		return secondonome;
	}
	
	/**
	 *  Imposta il cognome del contatto.
	 *
	 * @param cognome cognome del contatto
	 */
	public void setCognome(String cognome) 
	{
		this.cognome = cognome;
	}
	
	/**
	 *  Ritorna il cognome del contatto.
	 *
	 * @return cognome
	 */
	public String getCognome() 
	{
		return cognome;
	}
	
	/**
	 *  Imposta il path/percorso dell'immagine profilo del contatto.
	 *
	 * @param pathImmagine path dell'immagine 
	 */
	public void setPathImmagine(String pathImmagine) {
		this.pathImmagine = pathImmagine;
	}
	
	/**
	 *  Ritorna il path (percorso) dell'immagine profilo.
	 *
	 * @return path immagine
	 */
	public String getPathImmagine() {
		return pathImmagine;
	}
	
	/**
	 *  Fissa l'id (identificativo) del contatto.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 *  Ritorna l'id del contatto.
	 *
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 *  Ritorna gli indirizzi del contatto.
	 *
	 * @return indirizzi come ArrayList
	 */
	public ArrayList<Indirizzo> getIndirizzi()
	{
		return indirizzi;
	}
	
	/**
	 *  Ritorna i numeri telefonici del contatto.
	 *
	 * @return telefoni come ArrayList
	 */
	public ArrayList<Telefono> getTelefoni()
	{
		return telefoni;
	}
	
	/**
	 *  Ritorna le email del contatto.
	 *
	 * @return email come ArrayList
	 */
	public ArrayList<Email> getEmail()
	{
		return email;
	}
	
	/**
	 *  Aggiunge un numero di telefono al contatto.
	 *
	 * @param numero il numero di telefono
	 * @param tipotelefono Descrizione del numero di telefono
	 */
	public void addTelefono(String numero, String tipotelefono) {
		Telefono nuovoTelefono = new Telefono(numero, tipotelefono);
		telefoni.add(nuovoTelefono);
	}
	
	/**
	 *  Aggiunge una email al contatto.
	 *
	 * @param indirizzoemail indirizzo email
	 * @param tipoemail tipo/descrizione dell'email
	 */
	public void addEmail(String indirizzoemail, String tipoemail) {
		Email nuovaEmail = new Email(indirizzoemail, tipoemail);
		email.add(nuovaEmail);
	}
	
	/**
	 *  Aggiunge un indirizzo al contatto.
	 *
	 * @param via via dell'indirizzo
	 * @param citta città dell'indirizzo
	 * @param nazione nazione dell'indirizzo
	 * @param cap CAP dell'indirizzo
	 * @param tipo tipo/descrizione dell'indirizzo
	 */
	public void addIndirizzo(String via, String citta, String nazione, String cap, tipoIndirizzo tipo) {
		Indirizzo nuovoIndirizzo = new Indirizzo(via, citta, nazione, cap, tipo);
		indirizzi.add(nuovoIndirizzo);
	}
	
	/**
	 * Reindirizza la chiamata eseguita da un numero del contatto verso un altro numero disponibile.
	 *
	 * @param numeroDaReindirizzare numero chiamato
	 * 
	 * @return numero reindirizzato
	 */
	public String reindirizza(String numeroDaReindirizzare) {
		Telefono telefonoTrovato = null;
		Telefono telefonoMobile  = null;
		Telefono telefonoFisso   = null;
		for (Telefono telefono : getTelefoni())
		{
			if (telefono.getNumero().equals(numeroDaReindirizzare))
			{
				telefonoTrovato = telefono;
			}
			else 
			{
				if (telefono.getTipo().equals("Mobile") && telefonoMobile == null)
				{
					telefonoMobile = telefono;
				}
				if (telefono.getTipo().equals("Fisso") && telefonoFisso == null)
				{
					telefonoFisso = telefono;
				}
			}
		}
		if (telefonoTrovato != null)
		{
			if (telefonoTrovato.getTipo().equals("Mobile") && telefonoFisso != null)
			{
				return telefonoFisso.getNumero();
			}
			if (telefonoTrovato.getTipo().equals("Fisso") && telefonoMobile != null)
			{
				return telefonoMobile.getNumero();
			}
		}
		return null;
	}
}
