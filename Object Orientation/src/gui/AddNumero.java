package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class AddNumero extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField textFieldNumero;
	private JTextField textFieldDescrizioneNumero;

	
	public AddNumero() {
		frame = this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 201);
		setLocation(750,100);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setBounds(10, 34, 45, 13);
		contentPane.add(lblNumero);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(10, 57, 96, 19);
		contentPane.add(textFieldNumero);
		textFieldNumero.setColumns(10);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setBounds(161, 34, 96, 13);
		contentPane.add(lblDescrizione);
		
		textFieldDescrizioneNumero = new JTextField();
		textFieldDescrizioneNumero.setBounds(161, 57, 96, 19);
		contentPane.add(textFieldDescrizioneNumero);
		textFieldDescrizioneNumero.setColumns(10);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(10, 120, 85, 21);
		contentPane.add(btnAnnulla);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setBounds(161, 120, 85, 21);
		contentPane.add(btnAggiungi);
	}

}
