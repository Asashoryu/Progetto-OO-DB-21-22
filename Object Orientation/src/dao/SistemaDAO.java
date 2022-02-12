package dao;

import java.util.ArrayList;

import model.Rubrica;

public interface SistemaDAO {
	
	public ArrayList<Rubrica> loadRubriche();
	
	public void updateRubrica(String vecchiaRubrica, String nuovaRubrica);
}
