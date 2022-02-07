package model;

import java.util.ArrayList;

public class Contatto extends Elemento{
	
	private ArrayList<Numero> numeri;
	
	private ArrayList<Email> email;
	
	private ArrayList<Indirizzo> indirizzi;
	
	public Contatto(String name) {
		super(name);
	}
	
	void addNumero(String s,String tipoindirizzo) {
		Numero numbNumero = new Numero(s,tipoindirizzo);
		numeri.add(numbNumero);
	}
	
}
