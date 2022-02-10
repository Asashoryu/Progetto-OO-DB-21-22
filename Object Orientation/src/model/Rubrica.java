package model;

import java.util.ArrayList;

public class Rubrica {
	
	private String nome;
	
	private ArrayList<Contatto> contatti;
	
	private ArrayList<Gruppo> gruppi;
	
	public Rubrica(String nome) {
		setNome(nome);
	}
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void addContatto(String primonome, String secondonome, String cognome,
						    ArrayList<Telefono> telefoni, ArrayList<Email> email,
						    ArrayList<Indirizzo> indirizzi) {
		Contatto contatto = new Contatto(primonome, secondonome,cognome);
		for(Telefono t: telefoni) {
			contatto.addNumero(t.getNumero(), t.getTipo());
		}
		
	}
}	
