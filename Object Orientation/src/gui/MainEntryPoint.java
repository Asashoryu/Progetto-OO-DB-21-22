package gui;

import java.awt.EventQueue;

import controller.Controller;

/**
 * Gestisce l'avvio dell'applicazione.
 */
public class MainEntryPoint {
	
	/**
	 * Avvia l'applicazione.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller c = new Controller();
					new Home(c);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
