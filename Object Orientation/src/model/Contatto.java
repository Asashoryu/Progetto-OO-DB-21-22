package model;

public class Contatto {
	private String nome;
	
	public Contatto(String name) {
		this.nome=name;
	}
	public void setNome(String name) {
		this.nome=name;
		return;
	}
	
	public String getNome() {
		return nome;
	}
}
