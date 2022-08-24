package model;

import java.util.ArrayList;

public class Rubrica {
	
	private String nome;
	
	private ArrayList<Contatto> contatti;
	
	private ArrayList<Gruppo> gruppi;
	
	/** Alla creazione di una rubrica vengono caricati tutti i suoi contatti */
	public Rubrica(String nome) {
		setNome(nome);
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public ArrayList<Contatto> getContatti() {
		return contatti;
	}
	
	public void setContatti(ArrayList<Contatto> contatti) {
		this.contatti = contatti;
	}
	
	public ArrayList<Gruppo> getGruppi() {
		return gruppi;
	}

	public void setGruppi(ArrayList<Gruppo> gruppi) {
		this.gruppi = gruppi;
	}
	
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
	
	public Gruppo cercaPerNome(String text) 
	{
		Gruppo gruppoRicerca = new Gruppo();
		for (Contatto contatto : contatti)
		{
			if (contatto.getNome().matches("(?i).*"+text+".*"))
			{
				System.out.println(contatto.getNome());
				gruppoRicerca.getContatti().add(contatto);
			}
		}
		return gruppoRicerca;
	}
	
	public Gruppo cercaPerEmail(String text) {
		Gruppo gruppoRicerca = new Gruppo();
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
				gruppoRicerca.getContatti().add(contatto);
			}
		}
		return gruppoRicerca;
	}
	
	public Gruppo cercaPerAccount(String text) {
		Gruppo gruppoRicerca = new Gruppo();
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
				gruppoRicerca.getContatti().add(contatto);
			}
		}
		return gruppoRicerca;
	}
	
	public Gruppo cercaPerNumero(String text) {
		Gruppo gruppoRicerca = new Gruppo();
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
				gruppoRicerca.getContatti().add(contatto);
			}
		}
		return gruppoRicerca;
	}
}	
