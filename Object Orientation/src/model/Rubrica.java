package model;

import java.util.ArrayList;

/** Gestisce l'oggetto Rubrica. */
public class Rubrica {
	
	/** nome della rubrica (inteso come il nome dell'utente della rubrica). */
	private String nome;
	
	/** contatti della rubrica. */
	private ArrayList<Contatto> contatti;
	
	/** gruppi della rubrica. */
	private ArrayList<Gruppo> gruppi;
	
	/**
	 * Costruttore di una Rubrica.
	 *
	 * @param nome the nome
	 */
	public Rubrica(String nome) {
		setNome(nome);
	}
	
	/**
	 * Imposta il valore del nome.
	 *
	 * @param nome nome della rubrica
	 */
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	/**
	 * Ritorna il nome.
	 *
	 * @return nome della rubrica
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Ritorna i contatti salvati in rubrica.
	 *
	 * @return ArrayList di contatti
	 */
	public ArrayList<Contatto> getContatti() {
		return contatti;
	}
	
	/**
	 * Imposta i contatti della rubrica.
	 *
	 * @param contatti contatti
	 */
	public void setContatti(ArrayList<Contatto> contatti) {
		this.contatti = contatti;
	}
	
	/**
	 * Ritorna i gruppi.
	 *
	 * @return ArrayList di gruppi
	 */
	public ArrayList<Gruppo> getGruppi() {
		return gruppi;
	}

	/**
	 * Imposta i gruppi .
	 *
	 * @param gruppi gruppi
	 */
	public void setGruppi(ArrayList<Gruppo> gruppi) {
		this.gruppi = gruppi;
	}
	
	/**
	 * Aggiungi un nuovo contatto nella rubrica.
	 *
	 * @param primonome nome del contatto
	 * @param secondonome secondo nome del contatto
	 * @param cognome cognome del contatto
	 * @param numMobile numero mobile del contatto
	 * @param numFisso numero fisso del contatto
	 * @param via via dell'indirizzo del contatto
	 * @param citta città dell'indirizzo del contatto
	 * @param nazione nazione dell'indirizzo del contatto
	 * @param cap cap dell'indirizzo del contatto
	 * @param id identificativo del contatto
	 * @return contatto
	 */
	public Contatto aggiungiContatto(String primonome, String secondonome, String cognome,
                                 String numMobile, String numFisso, String via, String citta, String nazione, String cap, int id) 
	{
		Contatto contatto;
		try 
		{
			/** creo il contatto */
			contatto = new Contatto(primonome, secondonome,cognome, numMobile, numFisso, via, citta, nazione, cap, id);
			/** aggiungo il contatto alla rubrica */
			contatti.add(contatto);
		} 
		catch (NullPointerException e) 
		{
			throw e;
		}
		
		return contatto;
	}
	
	/**
	 * Cerca contatti per nome.
	 *
	 * @param text stringa o sottostringa da cercare come nome
	 * @return ArrayList dei contatti trovati
	 */
	public ArrayList<Contatto> cercaPerNome(String text) 
	{
		ArrayList<Contatto> contattiTrovati = new ArrayList<Contatto>();
		for (Contatto contatto : contatti)
		{
			if (contatto.getNome().matches("(?i).*"+text+".*"))
			{
				contattiTrovati.add(contatto);
			}
		}
		return contattiTrovati;
	}
	
	/**
	 * Cerca contatti per email.
	 *
	 * @param text stringa o sottostringa da cercare come email
	 * @return ArrayList dei contatti trovati 
	 */
	public ArrayList<Contatto> cercaPerEmail(String text) {
		ArrayList<Contatto> contattiTrovati = new ArrayList<Contatto>();
		Boolean match;
		for (Contatto contatto : contatti)
		{
			match = false;
			for(Email email : contatto.getEmail())
			{
				if (email.getStringaEmail().matches("(?i).*"+text+".*"))
				{
					match = true;
				}
			}
			if (match == true)
			{
				contattiTrovati.add(contatto);
			}
		}
		return contattiTrovati;
	}
	
	/**
	 * Cerca contatti per account.
	 *
	 * @param text stringa o sottostringa da cercare come account
	 * @return ArrayList dei contatti trovati 
	 */
	public ArrayList<Contatto> cercaPerAccount(String text) {
		ArrayList<Contatto> contattiTrovati = new ArrayList<Contatto>();
		Boolean match;
		for (Contatto contatto : contatti)
		{
			match = false;
			for (Email email : contatto.getEmail())
			{
				for (Account account : email.getAccount())
				{
					if (account.getNickname().matches("(?i).*"+text+".*"))
					{
						match = true;
					}
				}
			}
			if (match == true)
			{
				contattiTrovati.add(contatto);
			}
		}
		return contattiTrovati;
	}
	
	/**
	 * Cerca contatti per numero.
	 *
	 * @param text stringa contenente il numero da cercare
	 * @return ArrayList dei contatti trovati 
	 */
	public ArrayList<Contatto> cercaPerNumero(String text) {
		ArrayList<Contatto> contattiTrovati = new ArrayList<Contatto>();
		Boolean match;
		for (Contatto contatto : contatti)
		{
			match = false;
			for(Telefono telefono : contatto.getTelefoni())
			{
				if (telefono.getNumero().matches("(?i).*"+text+".*"))
				{
					match = true;
				}
			}
			if (match == true)
			{
				contattiTrovati.add(contatto);
			}
		}
		return contattiTrovati;
	}
}	
