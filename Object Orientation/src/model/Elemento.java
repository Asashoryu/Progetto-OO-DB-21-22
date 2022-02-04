package model;

public class Elemento {
	protected String nome;
	
	public Elemento (String name) {
		setNome(name);
	}
	public void setNome (String name) {
		this.nome=name;
		return;
	}
	
	public String getNome() {
		return nome;
	}
}
