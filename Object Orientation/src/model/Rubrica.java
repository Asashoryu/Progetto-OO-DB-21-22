package model;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Rubrica.
 */
public class Rubrica {
	
	/** The nome. */
	private String nome;
	
	/** The contatti. */
	private ArrayList<Contatto> contatti;
	
	/** The gruppi. */
	private ArrayList<Gruppo> gruppi;
	
	/**
	 *  Alla creazione di una rubrica vengono caricati tutti i suoi contatti.
	 *
	 * @param nome the nome
	 */
	public Rubrica(String nome) {
		setNome(nome);
	}
	
	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Gets the contatti.
	 *
	 * @return the contatti
	 */
	public ArrayList<Contatto> getContatti() {
		return contatti;
	}
	
	/**
	 * Sets the contatti.
	 *
	 * @param contatti the new contatti
	 */
	public void setContatti(ArrayList<Contatto> contatti) {
		this.contatti = contatti;
	}
	
	/**
	 * Gets the gruppi.
	 *
	 * @return the gruppi
	 */
	public ArrayList<Gruppo> getGruppi() {
		return gruppi;
	}

	/**
	 * Sets the gruppi.
	 *
	 * @param gruppi the new gruppi
	 */
	public void setGruppi(ArrayList<Gruppo> gruppi) {
		this.gruppi = gruppi;
	}
	
	/**
	 * Aggiungi contatto.
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
	 * @return the contatto
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
	 * Cerca per nome.
	 *
	 * @param text the text
	 * @return the array list
	 */
	public ArrayList<Contatto> cercaPerNome(String text) 
	{
		ArrayList<Contatto> contattiTrovati = new ArrayList<Contatto>();
		for (Contatto contatto : contatti)
		{
			if (contatto.getNome().matches("(?i).*"+text+".*"))
			{
				System.out.println(contatto.getNome());
				contattiTrovati.add(contatto);
			}
		}
		return contattiTrovati;
	}
	
	/**
	 * Cerca per email.
	 *
	 * @param text the text
	 * @return the array list
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
					System.out.println(contatto.getNome()+" : "+email.getStringaEmail());
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
	 * Cerca per account.
	 *
	 * @param text the text
	 * @return the array list
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
						System.out.println(contatto.getNome()+" : "+account.getNickname());
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
	 * Cerca per numero.
	 *
	 * @param text the text
	 * @return the array list
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
					System.out.println(contatto.getNome()+" : "+telefono.getNumero());
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
