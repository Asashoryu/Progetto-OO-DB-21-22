package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;

public class AddEmail extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JTextField textFieldDescrizione;

	
	public AddEmail() {
		frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 170);
		setLocation(750,100);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(10, 55, 137, 19);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldDescrizione = new JTextField();
		textFieldDescrizione.setBounds(201, 55, 112, 19);
		contentPane.add(textFieldDescrizione);
		textFieldDescrizione.setColumns(10);
		
		JLabel lblEmail = new JLabel("Indirizzo Email");
		lblEmail.setBounds(10, 26, 91, 19);
		contentPane.add(lblEmail);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setBounds(197, 29, 75, 13);
		contentPane.add(lblDescrizione);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(10, 102, 85, 21);
		contentPane.add(btnAnnulla);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setBounds(228, 102, 85, 21);
		contentPane.add(btnAggiungi);
	}

}
