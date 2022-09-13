package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Rubrica;


public interface SistemaDAO {
	public ArrayList<Rubrica> loadRubriche();
	public void updateRubrica(String vecchiaRubrica, String nuovaRubrica) throws SQLException;
	public void addRubrica(String nomeRubrica) throws SQLException;
	public void deleteRubrica(String nomeRubrica) throws SQLException;
}
