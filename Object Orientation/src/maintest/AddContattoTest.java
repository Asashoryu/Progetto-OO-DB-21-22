package maintest;

import javax.swing.JFrame;

import controller.Controller;
import gui.AddContatto;

public class AddContattoTest {
	private static Controller c      = null;
	private static String nomeUtente = "Utente Test";
	
	public static void main(String[] args) {
		avviaFrame();
		
	}
	public AddContattoTest() {
		// TODO Auto-generated constructor stub
	}
	
	private static void avviaFrame() {
		JFrame frameContatto = new AddContatto(c, nomeUtente);
//		JFrame frameContatto = new JframeExample();
		frameContatto.setVisible(true);
	}
	
	

}
