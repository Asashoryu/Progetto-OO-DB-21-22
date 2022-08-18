package gui;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;


public class FinestraChiama extends JFrame {

	private JPanel contentPane;

	

	public FinestraChiama(Controller c, String NumeroChiamato, String NumeroRiserva) {
		setResizable(false);
		JFrame frame = this;

		Controller controller = c;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 219);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChiamando = new JLabel("Sto chiamando " + controller.getContattoSelezionato().getNome() + " al numero " + NumeroChiamato + "...");
		lblChiamando.setForeground(new Color(204, 255, 255));
		lblChiamando.setFont(new Font("Arial", Font.PLAIN, 16));
		lblChiamando.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiamando.setBounds(10, 10, 416, 47);
		contentPane.add(lblChiamando);
		
		
		
		
		
		
		
		//Timer che dopo 3 secondi fa azione: elimina precedente label e ne aggiunge un altra con un bottone.
		new Timer(3_000, (e) -> { 
		lblChiamando.setVisible(false);
		contentPane.repaint();
		JLabel lblReindirizzamento = new JLabel("Nessuna risposta.. desideri chiamare il numero di riserva?");
		lblReindirizzamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblReindirizzamento.setFont(new Font("Arial", Font.PLAIN, 16));
		lblReindirizzamento.setForeground(new Color(204, 255, 255));
		lblReindirizzamento.setBounds(10, 10, 416, 27);
		contentPane.add(lblReindirizzamento);
		
		JButton btnChiama = new JButton("Chiama"); 
		btnChiama.setFont(new Font("Arial", Font.PLAIN, 11));
		btnChiama.setBackground(new Color(204,255,255));
		btnChiama.setForeground(new Color(102,102,153));
		btnChiama.setFocusPainted(false);
		btnChiama.setBounds(95, 72, 225, 39);
		contentPane.add(btnChiama);
		btnChiama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JLabel lblReindirizzato = new JLabel("Chiamando il numero di riserva " + NumeroRiserva + "...");
			lblReindirizzato.setHorizontalAlignment(SwingConstants.CENTER);
			lblReindirizzato.setFont(new Font("Arial", Font.PLAIN, 16));
			lblReindirizzato.setBounds(10, 10, 416, 27);
			FinestraChiamaRiserva FinestraCR = new FinestraChiamaRiserva(NumeroRiserva);
			FinestraCR.setVisible(true);
			frame.dispose();
			}
		});
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAnnulla.setForeground(new Color(102,102,153));
		btnAnnulla.setBackground(new Color(204,255,255));
		btnAnnulla.setFocusPainted(false);
		btnAnnulla.setBounds(95, 120, 225, 39);
		contentPane.add(btnAnnulla);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		}).start();
	
	

}
}