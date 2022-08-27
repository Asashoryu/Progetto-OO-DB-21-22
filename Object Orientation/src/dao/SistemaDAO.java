package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Rubrica;

// TODO: Auto-generated Javadoc
/**
 * The Interface SistemaDAO.
 */
public interface SistemaDAO {
	
	/**
	 * Load rubriche.
	 *
	 * @return the array list
	 */
	public ArrayList<Rubrica> loadRubriche();
	
	/**
	 * Update rubrica.
	 *
	 * @param vecchiaRubrica the vecchia rubrica
	 * @param nuovaRubrica the nuova rubrica
	 * @throws SQLException the SQL exception
	 */
	public void updateRubrica(String vecchiaRubrica, String nuovaRubrica) throws SQLException;
	
	/**
	 * Adds the rubrica.
	 *
	 * @param nomeRubrica the nome rubrica
	 * @throws SQLException the SQL exception
	 */
	public void addRubrica(String nomeRubrica) throws SQLException;
	
	/**
	 * Delete rubrica.
	 *
	 * @param nomeRubrica the nome rubrica
	 * @throws SQLException the SQL exception
	 */
	public void deleteRubrica(String nomeRubrica) throws SQLException;
}
