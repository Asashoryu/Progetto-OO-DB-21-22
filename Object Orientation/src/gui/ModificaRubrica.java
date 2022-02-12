package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.Controller;
import model.Rubrica;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificaRubrica extends JDia {
	
	private Controller controller;
	private JFrame frameChiamante;

	private JFrame frame;
	private JTextField textNuovoUtente;
	private JPanel panel_1;
	private JButton btnInserisci;
	private JPanel panel_2;

	
	/**
	 * Create the application.
	 */
	public ModificaRubrica(Controller c, JFrame frameChiamante) {
		controller=c;
		this.frameChiamante=frameChiamante;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblLabel = new JLabel();
		// "Ridenomina la rubrica selezionata"
		String stringaLabel = "Ridenomina ".concat(controller.getRubricaSelezionata().getNome());
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lblLabel.setText(stringaLabel);
		lblLabel.setEnabled(false);
		panel.add(lblLabel);
		
		textNuovoUtente = new JTextField();
		panel.add(textNuovoUtente);
		textNuovoUtente.setColumns(10);
		
		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		btnInserisci = new JButton("Inserisci");
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.updateRubrica(textNuovoUtente.getText());
				frameChiamante.dispose();
				Home home = new Home(controller);
				frame.dispose();
			}
		});
		panel_1.add(btnInserisci);
	}

}
