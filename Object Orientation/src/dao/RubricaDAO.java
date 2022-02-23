package dao;

import java.util.ArrayList;

import model.Contatto;
import model.Rubrica;

public interface RubricaDAO {
	
	public ArrayList<Contatto> loadContatti(String nomeRubrica);
}
