package model;

import java.util.ArrayList;

import dao.SistemaDAO;
import implementazionedao.SistemaImplementazionePostgresDAO;

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
}	
