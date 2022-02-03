package model;

public class Gruppo {
	private String nome;
	
	public Gruppo (String name) {
		this.nome=name;
	}
	public void setNome (String name) {
		this.nome=name;
		return;
	}
	
	public String getNome() {
		return nome;
	}
}
