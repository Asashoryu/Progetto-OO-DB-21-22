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
	
	public void addContatto(String primonome, String secondonome, String cognome,
						    ArrayList<Telefono> telefoni, ArrayList<Email> email,
						    ArrayList<Indirizzo> indirizzi) 
	{
		Contatto contatto = new Contatto(primonome, secondonome,cognome);
		try {
			for(Telefono t : telefoni) 
			{
				contatto.addNumero(t.getNumero(), t.getTipo());
			}
			
			for(Email em : email) 
			{
				contatto.addEmail(em.getStringaEmail(),em.getTipo());
			}
			
			for(Indirizzo i : indirizzi) 
			{
				contatto.addIndirizzo(i.getVia(), i.getCitta(), i.getNazione(), i.getCap(), i.getTipo());
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			throw e;
		}
	}
}	
