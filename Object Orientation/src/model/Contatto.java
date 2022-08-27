package model;

import java.util.ArrayList;

import model.Indirizzo.tipoIndirizzo;

// TODO: Auto-generated Javadoc
/**
 * The Class Contatto.
 */
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
	 *  costruttore del contatto con set di nome, secondo nome e cognome per la visualizzazione in listaContatto.
	 *
	 * @param primonome the primonome
	 * @param secondonome the secondonome
	 * @param cognome the cognome
	 * @param pathImmagine the path immagine
	 * @param id the id
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
	 *  costruttore di Contatto, con le informazioni essenziali indicate nella traccia.
	 *
	 * @param primonome the primonome
	 * @param secondonome the secondonome
	 * @param cognome the cognome
	 * @param numMobile the num mobile
	 * @param numFisso the num fisso
	 * @param via the via
	 * @param citta the citta
	 * @param nazione the nazione
	 * @param cap the cap
	 * @param id the id
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
	 *  set del nome del contatto.
	 *
	 * @param primonome the new nome
	 */
	public void setNome(String primonome) 
	{
		this.primonome = primonome;
	}
	
	/**
	 *  return del nome del contatto.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return primonome;
	}
	
	/**
	 *  set del secondo nome del contatto.
	 *
	 * @param secondonome the new secondo nome
	 */
	public void setSecondoNome(String secondonome) 
	{
		this.secondonome = secondonome;
	}
	
	/**
	 *  return del secondo nome del contatto.
	 *
	 * @return the secondo nome
	 */
	public String getSecondoNome() 
	{
		return secondonome;
	}
	
	/**
	 *  set del cognome del contatto.
	 *
	 * @param cognome the new cognome
	 */
	public void setCognome(String cognome) 
	{
		this.cognome = cognome;
	}
	
	/**
	 *  return del cognome del contatto.
	 *
	 * @return the cognome
	 */
	public String getCognome() 
	{
		return cognome;
	}
	
	/**
	 *  set del path dell'immagine profilo.
	 *
	 * @param pathImmagine the new path immagine
	 */
	public void setPathImmagine(String pathImmagine) {
		this.pathImmagine = pathImmagine;
	}
	
	/**
	 *  return del path dell'immagine profilo.
	 *
	 * @return the path immagine
	 */
	public String getPathImmagine() {
		return pathImmagine;
	}
	
	/**
	 *  set dell'id del contatto.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 *  return dell'id del contatto.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 *  return degli indirizzi del contatto.
	 *
	 * @return the indirizzi
	 */
	public ArrayList<Indirizzo> getIndirizzi()
	{
		return indirizzi;
	}
	
	/**
	 *  return dei telefoni del contatto.
	 *
	 * @return the telefoni
	 */
	public ArrayList<Telefono> getTelefoni()
	{
		return telefoni;
	}
	
	/**
	 *  return delle email del contatto.
	 *
	 * @return the email
	 */
	public ArrayList<Email> getEmail()
	{
		return email;
	}
	
	/**
	 *  metodo per aggiungere un numero di telefono al contatto.
	 *
	 * @param numero the numero
	 * @param tipotelefono the tipotelefono
	 */
	public void addTelefono(String numero, String tipotelefono) {
		Telefono nuovoTelefono = new Telefono(numero, tipotelefono);
		telefoni.add(nuovoTelefono);
	}
	
	/**
	 *  metodo per aggiungere una email al contatto.
	 *
	 * @param indirizzoemail the indirizzoemail
	 * @param tipoemail the tipoemail
	 */
	public void addEmail(String indirizzoemail, String tipoemail) {
		Email nuovaEmail = new Email(indirizzoemail, tipoemail);
		email.add(nuovaEmail);
	}
	
	/**
	 *  metodo per aggiungere un indirizzo al contatto.
	 *
	 * @param via the via
	 * @param citta the citta
	 * @param nazione the nazione
	 * @param cap the cap
	 * @param tipo the tipo
	 */
	public void addIndirizzo(String via, String citta, String nazione, String cap, tipoIndirizzo tipo) {
		Indirizzo nuovoIndirizzo = new Indirizzo(via, citta, nazione, cap, tipo);
		indirizzi.add(nuovoIndirizzo);
	}
	
	/**
	 * Reindirizza.
	 *
	 * @param numeroDaReindirizzare the numero da reindirizzare
	 * @return the string
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
				System.out.println("\nDebug: Trovato il numero chiamato in model "+ telefono.getTipo());
			}
			else 
			{
				System.out.println("D:" + telefono.getNumero()+ " di tipo " +telefono.getTipo());
				if (telefono.getTipo().equals("Mobile") && telefonoMobile == null)
				{
					System.out.println("Debug: Trovato mobile");
					telefonoMobile = telefono;
				}
				if (telefono.getTipo().equals("Fisso") && telefonoFisso == null)
				{
					System.out.println("Debug: Trovato fisso");
					telefonoFisso = telefono;
				}
			}
		}
		if (telefonoTrovato != null)
		{
			if (telefonoTrovato.getTipo().equals("Mobile") && telefonoFisso != null)
			{
				System.out.println("Debug: Ritorna num fisso");
				return telefonoFisso.getNumero();
			}
			if (telefonoTrovato.getTipo().equals("Fisso") && telefonoMobile != null)
			{
				System.out.println("Debug: Ritorna num mobile");
				return telefonoMobile.getNumero();
			}
		}
		return null;
	}
}
