package gui;

import java.awt.EventQueue;

import controller.Controller;
// TODO: Auto-generated Javadoc

/**
 * Classe di avvio dell'interfaccia del programma.
 */
public class MainEntryPoint {
	
	/**
	 * Launch the application.
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
