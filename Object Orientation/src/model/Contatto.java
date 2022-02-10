package model;

import java.util.ArrayList;

public class Contatto{
	
	private String primonome;
	
	private String secondonome;
	
	private String cognome;
	
	private ArrayList<Telefono> telefoni;
	
	private ArrayList<Email> email;
	
	private ArrayList<Indirizzo> indirizzi;
	
	public Contatto(String primonome, String secondonome, String cognome) {
		setNome(primonome, secondonome, cognome);
	}
	
	public void setNome(String p, String s, String c) {
		// TODO Auto-generated method stub
		primonome=p;
		secondonome=s;
		cognome=c;
	}

	public void addNumero(String numero,String tipotelefono) {
		Telefono NuovoNumero = new Telefono(numero,tipotelefono);
		telefoni.add(NuovoNumero);
	}
	
	public void addEmail(String indirizzoemail,String tipoemail) {
		Email NuovaEmail = new Email(indirizzoemail,tipoemail);
		email.add(NuovaEmail);
	}
	
}
