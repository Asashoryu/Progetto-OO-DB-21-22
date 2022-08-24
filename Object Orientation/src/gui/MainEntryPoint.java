package gui;

import java.awt.EventQueue;

import controller.Controller;
/**
 * Classe di avvio dell'interfaccia del programma
 *
 */
public class MainEntryPoint {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller c = new Controller();
					Home window  = new Home(c);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
