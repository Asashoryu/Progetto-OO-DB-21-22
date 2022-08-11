package model;

import java.util.ArrayList;

public class Gruppo{
	
	private String nome;
	
	private int id;
	
	private ArrayList<Contatto> contatti;
	
	public Gruppo (String nome, int id) {
		setNome(nome);
		setId(id);
		contatti = new ArrayList<Contatto>();
	}
	
	public Gruppo (String nome, ArrayList<Contatto> contatti) {
		setNome(nome);
		this.contatti = contatti;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Contatto> getContatti() {
		return contatti;
	}

	public void setContatti(ArrayList<Contatto> contatti) {
		this.contatti = contatti;
	}
	
}
